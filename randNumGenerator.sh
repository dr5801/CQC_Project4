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

# generates random numbers from 0-9999 1999 times to random/random_numbers.txt
# then append r to the file of numbers for the error to occurs at the 2000th line
#then append another 2000 lines of random numbers for a total of 4000 lines to the file
function generateRandomNumbersToFile()
{
	printf "$(shuf -i 0-9999 -n 1999) \n" > ${directory}/${file}
	printf "r\n" >> ${directory}/${file}
	printf "$(shuf -i 0-9999 -n 2000) \n" >> ${directory}/${file}
}

create_folder
generateRandomNumbersToFile
