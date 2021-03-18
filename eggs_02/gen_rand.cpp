#pragma comment(linker, "/STACK:20000000")
#include "testlib.h"
#include <assert.h>
#include <iostream>
#include <set>

using namespace std;
typedef long long int64;

long c, s;

int main(int argc, char **argv) {
	if (argc < 2) {
		fprintf(stderr, "parameters: [c] [s]\n");
		return 666;
	}
	sscanf(argv[1], "%ld", &c);
	sscanf(argv[2], "%ld", &s);
	registerGen(argc - 1, argv, 1);
	
	printf("%ld %ld\n", c + s, c * 4 + s * 2);
    return 0;
}

