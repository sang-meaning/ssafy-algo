#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;

int dp[10001];

int main() {
	int T;
	cin >> T;
	for (int tc = 1; tc <= T; ++tc) {
		int N, L; 
		cin >> N >> L;

		memset(dp, 0, sizeof(dp));

		for (int i = 0; i < N; ++i) {
			int score, cal; 
			cin >> score >> cal;

			for (int j = L; j >= cal; --j) {
				dp[j] = max(dp[j], dp[j - cal] + score);
			}
		}

		cout << "#" << tc << " " << dp[L] << "\n";
	}

	return 0;
}