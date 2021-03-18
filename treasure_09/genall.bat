g++ -O2 -std=gnu++0x gen_rand.cpp -o gen_rand
if errorlevel 1 goto ERROR

mkdir tests

copy hand\*.txt tests\

gen_rand 7 8 3 3 5 > tests\input12.txt
gen_rand 7 8 3 3 8 > tests\input13.txt
gen_rand 100 100 20 25 140 > tests\input14.txt
gen_rand 100 100 20 25 145 > tests\input15.txt
gen_rand 100 100 20 20 150 > tests\input16.txt
gen_rand 1000 1000 220 340 50 > tests\input17.txt
gen_rand 1000 1000 220 340 60 > tests\input18.txt
gen_rand 1000 1000 220 340 80 > tests\input19.txt
gen_rand 1000 1000 220 240 100 > tests\input20.txt
gen_rand 10000 10000 1200 3800 100 > tests\input21.txt
gen_rand 10000 10000 1200 3800 150 > tests\input22.txt
gen_rand 10000 10000 1200 2800 180 > tests\input23.txt
gen_rand 10000 10000 4200 800 160 > tests\input24.txt
gen_rand 10000 10000 4200 800 190 > tests\input25.txt
gen_rand 10000 10000 4200 700 200 > tests\input26.txt


goto exit
:ERROR
@echo OMG, SOMETHING IS WRONG!!!
:exit
@echo Everything is OK
