#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

// 간선 정보 구조체 (u, v: 섬 번호, cost: 환경 부담금 계산용 거리 제곱)
struct Edge {
    int u, v;
    long long cost;
    
    // 오름차순 정렬을 위한 비교 연산자 정의
    bool operator<(const Edge& other) const {
        return cost < other.cost;
    }
};

struct Point {
    long long x, y;
};

// Union-Find (Disjoint Set) 구현
vector<int> parent;

int find_set(int x) {
    if (parent[x] == x) return x;
    return parent[x] = find_set(parent[x]); // 경로 압축
}

bool union_sets(int a, int b) {
    int rootA = find_set(a);
    int rootB = find_set(b);
    if (rootA != rootB) {
        parent[rootB] = rootA;
        return true;
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T;
    cin >> T;
    for (int tc = 1; tc <= T; tc++) {
        int N;
        cin >> N;

        vector<Point> islands(N);
        for (int i = 0; i < N; i++) cin >> islands[i].x;
        for (int i = 0; i < N; i++) cin >> islands[i].y;

        double E; // 환경 부담 세율
        cin >> E;

        // 모든 섬 (N*(N-1)/2개)의 간선 생성
        vector<Edge> edges;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long long dx = islands[i].x - islands[j].x;
                long long dy = islands[i].y - islands[j].y;
                long long dist_sq = dx * dx + dy * dy; // 거리의 제곱 L^2
                edges.push_back({i, j, dist_sq});
            }
        }

        // 간선 비용(거리 제곱) 기준으로 오름차순 정렬
        sort(edges.begin(), edges.end());

        // Union-Find 초기화
        parent.resize(N);
        for (int i = 0; i < N; i++) parent[i] = i;

        // 크루스칼 알고리즘 수행
        long long total_dist_sq = 0; // 거리 제곱의 총합
        int edge_count = 0;

        for (const auto& edge : edges) {
            if (union_sets(edge.u, edge.v)) {
                total_dist_sq += edge.cost;
                edge_count++;
                if (edge_count == N - 1) break; // (N-1)개 간선 연결 시 종료
            }
        }

        // 최종 환경 부담금 계산 및 소수점 반올림
        double ans = E * total_dist_sq;
        
        // round()를 사용하여 반올림 처리 (LL calculations)
        long long final_ans = (long long)round(ans);

        cout << "#" << tc << " " << final_ans << "\n";
        }

        return 0;
}