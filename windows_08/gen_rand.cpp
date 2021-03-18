#pragma comment(linker, "/STACK:20000000")
#include "testlib.h"
#include <assert.h>
#include <iostream>
#include <set>

using namespace std;
typedef long long int64;

int w, h;

int main(int argc, char **argv) {
	if (argc < 2) {
		fprintf(stderr, "parameters: [w] [h] [seed]\n");
		return 666;
	}
	sscanf(argv[1], "%d", &w);
	sscanf(argv[2], "%d", &h);
	registerGen(argc, argv, 1);
	
	printf("%d %d\n", w, h);
	
	int prevwintype = -1;
	int wintype;
	for (int i = 0; i < 2; i++) {
		do { wintype = rnd.next(4); } while (wintype == prevwintype);
		int x1, y1, x2, y2;
		switch (wintype) {
			case 0:
				y1 = 0; y2 = 0;
				x1 = rnd.next(w + 1);
				do { x2 = rnd.next(w + 1); } while (x1 == x2);
				break;
			case 1:
				y1 = h; y2 = h;
				x1 = rnd.next(w + 1);
				do { x2 = rnd.next(w + 1); } while (x1 == x2);
				break;
			case 2:
				x1 = 0; x2 = 0;
                                y1 = rnd.next(h + 1);
				do { y2 = rnd.next(h + 1); } while (y1 == y2);
				break;
			case 3:
				x1 = w; x2 = w;
                                y1 = rnd.next(h + 1);
				do { y2 = rnd.next(h + 1); } while (y1 == y2);
				break;

		}
		printf("%d %d %d %d\n", x1, y1, x2, y2);
		prevwintype = wintype;
	}
    return 0;
}

