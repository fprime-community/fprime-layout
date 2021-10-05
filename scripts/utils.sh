#!/bin/sh

# Remove generated files
clean()
{
  echo "cleaning $PWD"
  rm -f num_failed.txt test-output.txt
  for file in `find . -name '*.out.*' -or -name '*.diff.*' -or -name '*~'`
  do
    rm $file
  done
}
