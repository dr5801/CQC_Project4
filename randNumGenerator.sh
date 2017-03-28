#!/bin/bash

directory="random"
file="random_numbers.txt"

function create_folder()
{
	if [ ! -d $directory/ ]
	then
		mkdir "$directory/"
	fi
}

# generates random numbers from 0-9999 5000 times to random/random_numbers.txt
# then append r to the file of numbers for the error to occur
function generateRandomNumbersToFile()
{
	printf "$(shuf -i 0-9999 -n 5000) \n" > ${directory}/${file}
	printf "r\n" >> ${directory}/${file}
}

create_folder
generateRandomNumbersToFile
