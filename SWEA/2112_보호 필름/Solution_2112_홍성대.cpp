#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int D, W, K;
int film[20][20];
int modified[20]; // -1: 미약품, 0: A약품 투입, 1: B약품 투입
int ans;

// 모든 열이 합격 기준(연속 K개 동일 특성)을 통과하는지 검사
bool isValid() {
    if (K == 1) return true;

    for (int c = 0; c < W; c++) {
        int max_cnt = 1;
        int cur_cnt = 1;

        for (int r = 1; r < D; r++) {
            int prev = (modified[r - 1] != -1) ? modified[r - 1] : film[r - 1][c];
            int curr = (modified[r] != -1) ? modified[r] : film[r][c];

            if (prev == curr) {
                cur_cnt++;
                if (cur_cnt > max_cnt) max_cnt = cur_cnt;
            } else {
                cur_cnt = 1;
            }
        }

        if (max_cnt < K) return false;
    }
    return true;
}

// row: 현재 확인 중인 행, cnt: 약품 투입 횟수
void dfs(int row, int cnt) {
    // 가지치기: 현재 투입 횟수가 이미 구한 최소 투입 횟수 이상이면 중단
    if (cnt >= ans) return;

    // 모든 행을 결정했거나 끝까지 탐색한 경우
    if (row == D) {
        if (isValid()) {
            ans = min(ans, cnt);
        }
        return;
    }

    // 1. 약품 투입 안 함
    modified[row] = -1;
    dfs(row + 1, cnt);

    // 2. A 약품 투입 (0)
    modified[row] = 0;
    dfs(row + 1, cnt + 1);

    // 3. B 약품 투입 (1)
    modified[row] = 1;
    dfs(row + 1, cnt + 1);

    // 백트래킹 복구
    modified[row] = -1;
}

void solve(int tc) {
    cin >> D >> W >> K;

    for (int i = 0; i < D; i++) {
        for (int j = 0; j < W; j++) {
            cin >> film[i][j];
        }
        modified[i] = -1;
    }

    // K가 1이거나 약품 없이 바로 통과하면 0 출력
    if (K == 1 || isValid()) {
        cout << "#" << tc << " 0\n";
        return;
    }

    ans = K; // 최악의 경우 K개 행을 동일하게 채우면 무조건 통과 가능
    dfs(0, 0);

    cout << "#" << tc << " " << ans << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T;
    cin >> T;
    for (int tc = 1; tc <= T; tc++) {
        solve(tc);
    }

    return 0;
}