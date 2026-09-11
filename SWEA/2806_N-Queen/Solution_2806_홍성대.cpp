#include <iostream>
using namespace std;

int N, ans;
int board[15][15];

bool is_safe(int row, int col) {
	for (int r = 0; r < row; r++) {
		if (board[r][col] == 1) return false;
	}
	int r = row - 1, c = col - 1;
	while (r >= 0 && c >= 0) {
		if (board[r][c] == 1) return false;
		r--;
		c--;
	}

	r = row - 1, c = col + 1;
	while (r >= 0 && c < N) {
		if (board[r][c] == 1) return false;
		r--;
		c++;
	}

	return true; 
}

void dfs(int row) {
	if (row == N) {
		ans++;
		return;
	}

	for (int col = 0; col < N; col++) {
		if (is_safe(row, col)) {
			board[row][col] = 1; 

			dfs(row + 1);  

			board[row][col] = 0; 
		}
	}
}

int main() {
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; ++tc) {
		memset(board, 0, sizeof(board));
		cin >> N;
		ans = 0;
		dfs(0);
		
		cout << "#" << tc << " " << ans << '\n';
	}
	return 0;
}