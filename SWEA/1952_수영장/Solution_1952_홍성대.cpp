#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

int month[12];

int main() {
	int t;
	cin >> t;
	for (int tc = 1; tc <= t; tc++) {
		int d, m, tm, y;
		cin >> d >> m >> tm >> y;
		vector<int> plan(13);
		for (int i = 1; i <= 12; ++i) {
			cin >> plan[i];
		}

		vector<int> dp(13, 0);

		for (int i = 1; i <= 12; ++i) {
			// 1일권 vs 1달권
			dp[i] = dp[i - 1] + min(plan[i] * d, m);

			// 3달권
			if (i >= 3) {
				dp[i] = min(dp[i], dp[i - 3] + tm);
			}
			else {
				dp[i] = min(dp[i], tm);
			}
		}

		// 1년권과 비교
		int ans = min(dp[12], y);

		cout << "#" << tc << " " << ans << "\n";
	}
}