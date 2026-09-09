#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int fix(const vector<int>& weak, const vector<int>& dist, int start, int weakSize) {
    int friendIdx = 0;
    int cover = weak[start] + dist[friendIdx];

    for (int i = start; i < start + weakSize; i++) {
        if (weak[i] > cover) {
            friendIdx++;
            if (friendIdx >= dist.size()) {
                return INT_MAX;
            }
            cover = weak[i] + dist[friendIdx];
        }
    }

    return friendIdx + 1;
}

int solution(int n, vector<int> weak, vector<int> dist) {

    int weakSize = weak.size();
    for (int i = 0; i < weakSize; i++) {
        weak.push_back(weak[i] + n);
    }

    sort(dist.begin(), dist.end());
    int answer = INT_MAX;
    do {
        for (int start = 0; start < weakSize; start++) {
            int used = fix(weak, dist, start, weakSize);
            answer = min(answer, used);
            if (answer == 1) {
                return 1;
            }
        }
    } while (next_permutation(dist.begin(), dist.end()));

    if (answer == INT_MAX) {
        return -1;
    }

    return answer;
}