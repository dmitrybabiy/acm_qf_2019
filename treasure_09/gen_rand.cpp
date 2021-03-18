#pragma comment(linker, "/STACK:20000000")
#include "testlib.h"
#include <assert.h>
#include <iostream>
#include <set>

using namespace std;
typedef long long int64;

long w, h, m, n, k;

int main(int argc, char **argv) {
	if (argc < 2) {
		fprintf(stderr, "parameters: [w] [h] [m] [n] [k]\n");
		return 666;
	}
	sscanf(argv[1], "%ld", &w);
	sscanf(argv[2], "%ld", &h);
	sscanf(argv[3], "%ld", &m);
	sscanf(argv[4], "%ld", &n);
	sscanf(argv[5], "%ld", &k);
	registerGen(argc - 1, argv, 1);
	
	printf("%ld %ld %ld %ld %ld\n", w, h, m, n, k);
	set<long> s;
	for (int i = 0; i < k; i++) {
		long x, y;
		do {
			x=rnd.next(w);
			y=rnd.next(h);
		} while (s.find(x*h + y) != s.end());
		s.insert(x*h+y);
		printf("%ld %ld\n", x, y);
	}
    return 0;
}

