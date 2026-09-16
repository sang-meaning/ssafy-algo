#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

struct BC {
	int x, y, c, p;
};

int dx[] = { 0, 0, 1, 0, -1 };
int dy[] = { 0, -1, 0, 1, 0 };

int get_dist(int x1, int y1, int x2, int y2) {
	return abs(x1 - x2) + abs(y1 - y2);
}

int main() {
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		int M, A_cnt;
		cin >> M >> A_cnt;

		vector<int> moveA(M), moveB(M);
		for (int i = 0; i < M; i++) cin >> moveA[i];
		for (int i = 0; i < M; i++) cin >> moveB[i];

		vector<BC> bcs(A_cnt);
		for (int i = 0; i < A_cnt; i++) {
			cin >> bcs[i].x >> bcs[i].y >> bcs[i].c >> bcs[i].p;
		}

		int ax = 1, ay = 1;
		int bx = 10, by = 10;
		int total_charge = 0;

		for (int t = 0; t <= M; t++) {
			vector<int> candA, candB;
			for (int i = 0; i < A_cnt; i++) {
				if (get_dist(ax, ay, bcs[i].x, bcs[i].y) <= bcs[i].c) candA.push_back(i);
				if (get_dist(bx, by, bcs[i].x, bcs[i].y) <= bcs[i].c) candB.push_back(i);
			}

			int max_charge = 0;

			if (candA.empty() && candB.empty()) {
				max_charge = 0;
			}
			else if (candA.empty()) {
				for (int b : candB) max_charge = max(max_charge, bcs[b].p);
			}
			else if (candB.empty()) {
				for (int a : candA) max_charge = max(max_charge, bcs[a].p);
			}
			else {
				for (int a : candA) {
					for (int b : candB) {
						if (a == b) {
							max_charge = max(max_charge, bcs[a].p);
						}
						else {
							max_charge = max(max_charge, bcs[a].p + bcs[b].p);
						}
					}
				}
			}

			total_charge += max_charge;

			if (t < M) {
				ax += dx[moveA[t]];
				ay += dy[moveA[t]];
				bx += dx[moveB[t]];
				by += dy[moveB[t]];
			}
		}

		cout << "#" << tc << " " << total_charge << "\n";
	}
	return 0;
}