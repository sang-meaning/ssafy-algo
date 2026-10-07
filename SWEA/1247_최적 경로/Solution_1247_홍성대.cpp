#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

struct point {
	int x, y;
};

int N;
point company, home;
vector<point> customers;
vector<bool> visited;
int min_dist;

int get_distance(point p1, point p2) {
	return abs(p1.x - p2.x) + abs(p1.y - p2.y);
}

void dfs(int count, point c, int dist) {
	if (dist >= min_dist) return;

	if (count == N) {
		int total_dist = dist + get_distance(c, home);
		min_dist = min(min_dist, total_dist);
		return;
	}
	for (int i = 0; i < N; i++) {
		if (visited[i] == false) {
			visited[i] = true;
			int next_dist = dist + get_distance(c, customers[i]);
			dfs(count + 1, customers[i], next_dist);
			visited[i] = false;
		}
	}
		
}

int main() {
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; ++tc) {
		cin >> N;
		cin >> company.x >> company.y;
		cin >> home.x >> home.y;

		customers.resize(N);
		visited.assign(N, false);
		min_dist = 1e9;

		for (int i = 0; i < N; i++) {
			cin >> customers[i].x >> customers[i].y;
		}
		dfs(0, company, 0);

		cout << "#" << tc << " " << min_dist << "\n";
	}
}