#pragma comment(linker, "/STACK:20000000")
#include "testlib.h"
#include <assert.h>
#include <iostream>
#include <set>

using namespace std;
typedef long long int64;

int n, l, maxd, step;

int main(int argc, char **argv) {
	if (argc < 2) {
		fprintf(stderr, "parameters: [n] [maxd] [step] [l]\n");
		return 666;
	}
	sscanf(argv[1], "%d", &n);
	sscanf(argv[2], "%d", &maxd);
	sscanf(argv[3], "%d", &step);
	sscanf(argv[4], "%d", &l);
	registerGen(argc - 1, argv, 1);
	
	printf("%d %d\n", n, l);
	for (int i = 1; i < n; i++) {
		int w = min(step, i);
		printf("%d %d\n", rnd.next(w) + i - w, rnd.next(maxd) + 1);
	}
    return 0;
}

