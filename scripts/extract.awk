$1 == ".START" && $2 == "source" { print "" }

$1 ~ /component=/ {
  instance = $0
  sub("component=", "", instance)
  print instance
}

$1 ~ /port=/ {
  port = $0
  sub("port=", "", port)
  print port
}

$1 ~ /num=/ {
  num = $0
  sub("num=", "", num)
  print num
}
