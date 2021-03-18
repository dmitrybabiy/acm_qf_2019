g++ -O2 -std=gnu++0x gen_rand.cpp -o gen_rand
if errorlevel 1 goto ERROR

mkdir tests

copy hand\*.txt tests\

gen_rand 5 5 > tests\input02.txt
gen_rand 8 2 > tests\input03.txt
gen_rand 10 13 > tests\input04.txt
gen_rand 234 76 > tests\input05.txt
gen_rand 555 15 > tests\input06.txt
gen_rand 3555 15 > tests\input07.txt
gen_rand 5455 1215 > tests\input08.txt
gen_rand 5575 215 > tests\input09.txt
gen_rand 2000 8000 > tests\input10.txt
gen_rand 2300 5000 > tests\input11.txt


goto exit
:ERROR
@echo OMG, SOMETHING IS WRONG!!!
:exit
@echo Everything is OK
