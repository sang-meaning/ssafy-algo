#include <iostream>

using namespace std;

const int MAX = 1000005;
int parent[MAX];

int find_root(int x) {
    if (parent[x] == x) return x;
    return parent[x] = find_root(parent[x]); 
}

void union_set(int a, int b) {
    int rootA = find_root(a);
    int rootB = find_root(b);
    if (rootA != rootB) {
        parent[rootB] = rootA;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T;
    if (!(cin >> T)) return 0;

    for (int tc = 1; tc <= T; ++tc) {
        int n, m;
        cin >> n >> m;

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        cout << "#" << tc << " ";

        for (int i = 0; i < m; i++) {
            int cmd, a, b;
            cin >> cmd >> a >> b;

            if (cmd == 0) {
                union_set(a, b);
            } else {
                if (find_root(a) == find_root(b)) {
                    cout << 1;
                } else {
                    cout << 0;
                }
            }
        }
        cout << "\n";
    }

    return 0;
}