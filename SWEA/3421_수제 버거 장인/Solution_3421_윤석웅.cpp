#include <iostream>
#include <vector>
#include <cstdint>

using namespace std;

int comb(vector<uint32_t>& N) {
	int answer = 0;
	for (uint32_t mask = 0; mask < (1u << N.size()); mask += 2) {
		bool valid = true;
		for (int i = 1; i < N.size(); i++) {
			if (mask & 1u << i) {
				if (mask & N[i]) {
					valid = false;
					break;
				}
			}
		}
		if (valid) {
			answer++;
		}
	}

	return answer;
}

int main() {
	int T;
	int N, M;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> N >> M;
		vector<uint32_t> input(N+1,0);

		for (int i = 0; i < M; i++) {
			int c1, c2;
			cin >> c1 >> c2;
			input[c1] |= 1u << c2;
			input[c2] |= 1u << c1;
		}

		cout << "#" << tc <<" " << comb(input) << '\n';
	}

	return 0;
}