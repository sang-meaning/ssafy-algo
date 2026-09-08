#include <iostream>

using namespace std;

int N, X, M;
int q[10][3];
int cage[7];
int ans[7];
int max_hamsters;

void dfs(int depth) {
	if (depth > N) {
		for (int i = 0; i < M; ++i) {
			int current_sum = 0;
			for (int k = q[i][0]; k <= q[i][1]; ++k) {
				current_sum += cage[k];
			}
			if (current_sum != q[i][2]) return;
		}

		int total = 0;
		for (int i = 1; i <= N; ++i) {
			total += cage[i];
		}

		if (total > max_hamsters) {
			max_hamsters = total;
			for (int i = 1; i <= N; ++i) {
				ans[i] = cage[i];
			}
		}
		return;
	}

	for (int count = 0; count <= X; ++count) {
		cage[depth] = count;
		dfs(depth + 1);
	}
}


int main() {

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; ++tc) {
		cin >> N >> X >> M;
		for (int i = 0; i < M; ++i) {
			cin >> q[i][0] >> q[i][1] >> q[i][2];
		}

		max_hamsters = -1;
		dfs(1);

		cout << "#" << tc;
		if (max_hamsters == -1) {
			cout << " -1\n";
		}
		else {
			for (int i = 1; i <= N; ++i) {
				cout << " " << ans[i];
			}
			cout << "\n";
		}
	}

	return 0;
}