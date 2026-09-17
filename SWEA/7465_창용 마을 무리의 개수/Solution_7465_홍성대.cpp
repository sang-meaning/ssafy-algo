#include <iostream>
#include <cstring>
#include <vector>
#include <queue>

using namespace std;
vector<int> adj[1005];
bool visited[1005];
int main() {
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; ++tc) {
		memset(visited, false, sizeof(visited));
		int n, m;
		cin >> n >> m;
		int count = 0;
		for (int i = 1; i <= n; i++) {
			adj[i].clear();
			visited[i] = false;
		}
		for (int i = 0; i < m; i++) {
			int v, e;
			cin >> v >> e;
			adj[v].push_back(e);
			adj[e].push_back(v);
		}
		queue<int> q;

		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				count++;
				visited[i] = true;
				q.push(i);

				while (!q.empty()) {
					int curr = q.front();
					q.pop();
					for (int next : adj[curr]) {
						if (!visited[next]) {
							visited[next] = true;
							q.push(next);
						}
					}
				}
			}
		}
		cout << "#" << tc << " " << count << "\n";
	}
}