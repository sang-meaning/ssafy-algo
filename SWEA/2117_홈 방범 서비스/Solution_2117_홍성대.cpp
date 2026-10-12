#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

int N, M;
int map_data[20][20];

// 운영 비용 계산 함수
int getCost(int k) {
	return k * k + (k - 1) * (k - 1);
}


int main() {
	int t;
	cin >> t;
	for (int tc = 1; tc <= t; ++tc) {
		cin >> N >> M;
		vector<pair<int, int>> houses;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cin >> map_data[i][j];
				if (map_data[i][j] == 1) {
					houses.push_back({ i, j });
				}
			}
		}

		int max_houses = 0;

		// K는 1부터 N + 2까지 탐색 (격자 전체를 덮을 수 있는 충분한 크기)
		for (int k = 1; k <= N + 2; k++) {
			int cost = getCost(k);

			// 모든 좌표를 서비스 중심점으로 지정
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int cnt = 0;
					for (auto& house : houses) {
						int dist = abs(house.first - r) + abs(house.second - c);
						if (dist <= k - 1) {
							cnt++;
						}
					}

					// 손해를 보지 않는 경우 (수익 >= 운영 비용)
					if (cnt * M >= cost) {
						max_houses = max(max_houses, cnt);
					}
				}
			}
		}

		cout << "#" << tc << " " << max_houses << "\n";
	}
	return 0;
}