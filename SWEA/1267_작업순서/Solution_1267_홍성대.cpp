#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

int a[1001];
vector<int> b[1001];
int main() {
	for (int tc = 1; tc <= 10; tc++) {

		int V, E;
		cin >> V >> E;
		for (int i = 1; i <= V; i++) {
			a[i] = 0;
			b[i].clear();
		}
		for (int i = 0; i < E; i++) {
			int v, e;
			cin >> v >> e;
			b[v].push_back(e);
			a[e]++;
		}
		queue<int> q;
		for (int i = 1; i <= V; i++) {
			if (a[i] == 0) {
				q.push(i);
			}
		}
		cout << "#" << tc;
		while (!q.empty()) {
			int curr = q.front();
			q.pop();

			cout << " " << curr;

			for (int next : b[curr]) {
				a[next]--;
				if (a[next] == 0) {
					q.push(next);
				}
			}
		}
		cout << "\n";


	}
}