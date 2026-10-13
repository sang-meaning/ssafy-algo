#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; ++tc) {
		int N, K;
		cin >> N >> K;

		vector<int> weight(N + 1);
		vector<int> value(N + 1);

		for (int i = 1; i <= N; ++i) {
			cin >> weight[i] >> value[i];
		}

		vector<vector<int>> dp(N + 1, vector<int>(K + 1, 0));

		for (int i = 1; i <= N; ++i) {
			for (int w = 1; w <= K; ++w) {
				if (weight[i] > w) {
					dp[i][w] = dp[i - 1][w];
				}
				else {
					dp[i][w] = max(dp[i - 1][w], dp[i - 1][w - weight[i]] + value[i]);
				}
			}
		}

		cout << "#" << tc << " " << dp[N][K] << "\n";
	}

	return 0;
}