BEGIN {
  FS = "\n"
  RS = ""
  print "digraph {"
}

NR > 1 {
  print "  " $1 " -> " $4
}

END {
  print "}"
}

