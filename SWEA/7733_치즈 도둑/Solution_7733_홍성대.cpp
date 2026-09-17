#include <iostream>
#include <vector>
#include <cstring>

using namespace std;
int a[100][100];
bool visited[100][100];
bool c[100][100];
int dx[] = { 0,0,1,-1 };
int dy[] = { 1,-1,0,0 };
int n;

void check(int x, int y) {
	for (int i = 0; i < 4; i++) { 
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
			if (visited[nx][ny] == false) {
				visited[nx][ny] = true;
				c[nx][ny] = true;
				check(nx, ny);
			}
		}
	}
}
int main() {
	int t;
	cin >> t;
	for (int tc = 1; tc <= t; ++tc) {
		memset(c, false, sizeof(c));
		memset(visited, false, sizeof(visited));
		memset(a, 0, sizeof(a));
		cin >> n;
		int ma = 0;
		int mx = 0;
		int my = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> a[i][j];
			}
		}
		int top = 1;
		int day = 0;
		while (day<100) {
			int count = 0;
			int cnt = 0;
			day++;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (a[i][j] <= day) {
						visited [i][j] = true;
					}
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j]) {
						count++;
						visited[i][j] = true;
						c[i][j] = true;
						check(i, j);
					}
				}
			}
			if (top < count) {
				top = count;
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (c[i][j]) {
						visited[i][j] = false;
					}
				}
			}
			memset(c, false, sizeof(c));
			
		}
		cout << "#" << tc << " " << top << "\n";

	}
}