#include <iostream>
#include <vector>
#include <algorithm>
#include <array>
#include <cmath>
using namespace std;

long long x[1000], y[1000];

int p[1000];
int find(int x) {
    return p[x] == x ? x : p[x] = find(p[x]);
}

bool isUnion(int u, int v) {
    return find(u) == find(v) ? true : false;
}

void Union(int u, int v) {
    u = find(u);
    v = find(v);
    if (u != v) p[u] = v;
}

int main() {
    cin.tie(0), cout.tie(0);
    ios_base::sync_with_stdio(false);

    int T; cin >> T;
    for (int test=1; test<=T; test++) {
        int N; cin >> N;
        
        for (int i=0; i<N; i++) cin >> x[i];
        for (int i=0; i<N; i++) cin >> y[i];

        double e; cin >> e;

        vector <array<long long, 3>> v;
        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                long long dx = x[i] - x[j];
                long long dy = y[i] - y[j];
                v.push_back({dx*dx + dy*dy, i, j});
            }
        }

        sort(v.begin(), v.end());

        int cnt = 0;
        long long dist = 0;

        for (int i=0; i<N; i++) p[i] = i;

        for (int i=0; i<v.size() && cnt < N-1; i++) {
            auto edge = v[i];

            long long now = edge[0];
            int u = edge[1];
            int v = edge[2];

            if (!isUnion(u, v)) {
                Union(u, v);
                dist += now;
                cnt++;
            }
        }

        cout << '#' << test << ' ' << (llround)(dist * e) << '\n';
    }
}