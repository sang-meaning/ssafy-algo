#include <vector>
#include <algorithm>

using namespace std;

int min_ans;
int weak_cnt;
vector<int> w;
vector<int> d;
bool used[10];

void pick_friend(int current_pos_idx, int end_idx, int used_count) {
    if (used_count >= min_ans) return;

    if (current_pos_idx > end_idx) {
        min_ans = min(min_ans, used_count);
        return;
    }

    for (int i = 0; i < d.size(); i++) {
        if (used[i]) continue;

        used[i] = true;

        int reach = w[current_pos_idx] + d[i];

        int next_idx = current_pos_idx;
        while (next_idx <= end_idx && w[next_idx] <= reach) {
            next_idx++;
        }

        pick_friend(next_idx, end_idx, used_count + 1);

        used[i] = false;
    }
}

int solution(int n, vector<int> weak, vector<int> dist) {
    weak_cnt = weak.size();
    min_ans = dist.size() + 1;
    d = dist;

    w = weak;
    for (int i = 0; i < weak_cnt; i++) {
        w.push_back(weak[i] + n);
    }

    for (int i = 0; i < weak_cnt; i++) {
        pick_friend(i, i + weak_cnt - 1, 0);
    }

    return (min_ans > dist.size()) ? -1 : min_ans;
}