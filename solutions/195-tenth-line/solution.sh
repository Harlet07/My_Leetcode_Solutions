# Read from the file file.txt and output the tenth line to stdout.
#!/bin/bash

n=$(wc -l < file.txt)

if [ $n -ge 10 ]; then
    head file.txt | tail -n 1
fi
