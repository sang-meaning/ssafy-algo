#include <iostream>
#include <string>
using namespace std;
char pool[25][25];
char c[101];
int dx[] = { -1,1,0, 0 }; // 상 하 좌 우
int dy[] = {0,0,-1, 1 };
int dir;

void shoot(int x, int y) {
	int nx = x + dx[dir];
	int ny = y + dy[dir];
	
	if (pool[nx][ny] == '.' || pool[nx][ny] == '-') {
		shoot(nx, ny);
	}
	else if (pool[nx][ny] == '*') {
		pool[nx][ny] = '.';
		return;
	}
	else {
		return;
	}
}

void init() {
	for (int i = 0; i < 25; i++) {
		for (int j = 0; j < 25; j++) {
			pool[i][j] = 0;
		}
	}
}
int main() {
	int t;
	cin >> t;
	for (int tc = 1; tc <= t; ++tc) {
		int H, W;
		cin >> H >> W;
		int sa, sb;
		init();
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= W; j++) {
				cin >> pool[i][j];
				if (pool[i][j] == '^') {
					sa = i; 
					sb = j;
					dir = 0;
				}
				else if (pool[i][j] == 'v'){
					sa = i;
					sb = j;
					dir = 1;
				}
				else if (pool[i][j] == '<') {
					sa = i;
					sb = j;
					dir = 2;
				}
				else if (pool[i][j] == '>') {
					sa = i;
					sb = j;
					dir = 3;
				}
			}
		}
		int N;
		cin >> N;
		string order;
		cin >> order;
		for (int i = 0; i < N; i++) {
			if (order[i] == 'S') {
				shoot(sa, sb);
			}
			else if (order[i] == 'U') {
				dir = 0;
				pool[sa][sb] = '^';
				if (pool[sa + dx[dir]][sb + dy[dir]] == '.') {
					pool[sa][sb] = '.';
					sa += dx[dir];
					sb += dy[dir];
					pool[sa][sb] = '^';
				}
				

			}
			else if (order[i] == 'D') {
				dir = 1;
				pool[sa][sb] = 'v';
				if (pool[sa + dx[dir]][sb + dy[dir]] == '.') {
					pool[sa][sb] = '.';
					sa += dx[dir];
					sb += dy[dir];
					pool[sa][sb] = 'v';
				}
				
			}
			else if (order[i] == 'L') {
				dir = 2;
				pool[sa][sb] = '<';
				if (pool[sa + dx[dir]][sb + dy[dir]] == '.') {
					pool[sa][sb] = '.';
					sa += dx[dir];
					sb += dy[dir];
					pool[sa][sb] = '<';
				}
				
			}
			else if (order[i] == 'R') {
				dir = 3;
				pool[sa][sb] = '>';
				if (pool[sa + dx[dir]][sb + dy[dir]] == '.') {
					pool[sa][sb] = '.';
					sa += dx[dir];
					sb += dy[dir];
					pool[sa][sb] = '>';
				}
				
			}
		
		}
		cout << "#" << tc << " ";
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= W; j++) {
				cout << pool[i][j];
			}
			cout << "\n";
		}
	}
}