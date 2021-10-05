#/usr/bin/env awk -f

# ----------------------------------------------------------------------
# fpl-extract-xml
# Extract connection groups from F Prime XML
# ----------------------------------------------------------------------

BEGIN {
  INIT = 0
  GROUP = 1
  state = INIT
  num_groups = 0
}

function error(  s) {
  print("fpl-extract-xml: error at line " NR ": " s) > "/dev/stderr"
  exit 1
}

function get_filename(  group_name) {
  if (dir == "")
    return group_name ".xml"
  else
    return dir "/" group_name ".xml"
}

function write_file(  group_name, i) {
  if (num_lines[group_name] > 0) {
    filename = get_filename(group_name)
    print "<fpl>" > filename
    for (i = 1; i <= num_lines[group_name]; ++i)
      print lines[group_name, i] >> filename
    print "</fpl>" >> filename
    close(filename)
  }
}

$2 == "@FPL" && $3 == "START" {
  if ($4 == "" || $4 == "-->") {
    error("empty group name")
  }
  else if (state == INIT) {
    group_name = $4
    if (group_name_set[group_name] != 1) {
      group_name_set[group_name] = 1
      ++num_groups
      group_names[num_groups] = group_name
    }
    state = GROUP
    num_lines[group_name] = 0
    next
  }
  else {
    error("group " $4 " started while in group " group_name)
  }
}

$2 == "@FPL" && $3 == "END" {
  if (state == GROUP) {
    state = INIT
    next
  }
  else {
    error("encountered END while not in a group")
  }
}

state == GROUP { 
  ++num_lines[group_name]
  lines[group_name, num_lines[group_name]] = $0 
}

END {
  if (state == GROUP)
    error("file ended while in group " group_name)
  for (i = 1; i <= num_groups; ++i)
    write_file(group_names[i])
}
