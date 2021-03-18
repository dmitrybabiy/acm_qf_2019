g++ -O2 -std=gnu++0x gen_rand.cpp -o gen_rand
if errorlevel 1 goto ERROR
g++ -O2 -std=gnu++0x gen_line.cpp -o gen_line
if errorlevel 1 goto ERROR
g++ -O2 -std=gnu++0x gen_star.cpp -o gen_star
if errorlevel 1 goto ERROR


mkdir tests

copy hand\*.txt tests\

gen_rand 7 5 7 > tests\input09.txt
gen_rand 7 5 4 > tests\input10.txt
gen_rand 10 5 10 > tests\input10.txt
gen_rand 10 5 20 > tests\input11.txt
gen_rand 10 5 15 > tests\input12.txt
gen_rand 100 10 50 > tests\input13.txt
gen_rand 100 10 70 > tests\input14.txt
gen_rand 100 10 90 > tests\input15.txt
gen_line 50 10 1 100 > tests\input16.txt
gen_line 500 10 5 500 > tests\input17.txt
gen_line 500 10 3 600 > tests\input18.txt
gen_star 50 10 3 30 > tests\input19.txt
gen_star 500 10 5 40 > tests\input20.txt
gen_star 500 10 10 50 > tests\input21.txt
gen_line 1000 50 10 500 > tests\input22.txt
gen_line 1000 50 10 1000 > tests\input23.txt
gen_line 1000 50 10 2000 > tests\input24.txt
gen_line 1000 50 10 3000 > tests\input25.txt
gen_line 1000 50 10 4000 > tests\input26.txt
gen_star 1000 100 8 100 > tests\input27.txt
gen_star 1000 100 8 300 > tests\input28.txt
gen_star 1000 100 15 500 > tests\input29.txt
gen_star 1000 100 15 700 > tests\input30.txt
gen_star 1000 100 1 180 > tests\input31.txt
gen_star 1000 100 1 190 > tests\input32.txt
gen_star 1000 100 1 150 > tests\input33.txt
gen_star 1000 100 2 150 > tests\input34.txt
gen_star 1000 100 2 200 > tests\input35.txt
gen_star 1000 100 2 210 > tests\input36.txt

goto exit
:ERROR
@echo OMG, SOMETHING IS WRONG!!!
:exit
@echo Everything is OK
