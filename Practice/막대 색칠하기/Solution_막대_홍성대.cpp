#include <iostream>
#include <vector>
using namespace std;

int main() {
	int n;
	cin >> n;
	int dp[100];
	dp[1] = 2;
	dp[2] = 5;
	for (int i = 3; i <= 99; i++) {
		dp[i] = 2 * dp[i - 1] + dp[i - 2];
	}
	cout << dp[n];
}