g++ -O2 -std=gnu++0x gen_rand.cpp -o gen_rand
if errorlevel 1 goto ERROR

mkdir tests

copy hand\*.txt tests\

gen_rand 7 5 1 > tests\input05.txt
gen_rand 7 5 2 > tests\input06.txt
gen_rand 7 5 3 > tests\input07.txt
gen_rand 100 1 1 > tests\input08.txt
gen_rand 100 1 2 > tests\input09.txt
gen_rand 100 1 3 > tests\input10.txt
gen_rand 100 1 4 > tests\input11.txt
gen_rand 70 100 1 > tests\input12.txt
gen_rand 70 100 2 > tests\input13.txt
gen_rand 70 100 3 > tests\input14.txt
gen_rand 70 100 4 > tests\input15.txt
gen_rand 100 100 1 > tests\input16.txt
gen_rand 100 100 2 > tests\input17.txt
gen_rand 100 100 3 > tests\input18.txt

goto exit
:ERROR
@echo OMG, SOMETHING IS WRONG!!!
:exit
@echo Everything is OK
