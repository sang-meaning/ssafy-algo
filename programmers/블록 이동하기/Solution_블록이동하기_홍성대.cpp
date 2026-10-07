#include <vector>
#include <queue>
#include <set>
#include <algorithm>

using namespace std;

// 1. 점(좌표) 구조체
struct Point {
    int r, c;
    
    // set에 넣기 위한 대소 비교 연산자
    bool operator<(const Point& o) const {
        if (r != o.r) return r < o.r;
        return c < o.c;
    }
    bool operator==(const Point& o) const {
        return r == o.r && c == o.c;
    }
};

// 2. 드론 상태 구조체
struct Robot {
    Point p1, p2;
    int time;

    // p1과 p2 순서 상관없이 일관되게 정렬
    Robot(Point a, Point b, int t = 0) : time(t) {
        if (b < a) { p1 = b; p2 = a; }
        else       { p1 = a; p2 = b; }
    }

    bool operator<(const Robot& o) const {
        if (!(p1 == o.p1)) return p1 < o.p1;
        return p2 < o.p2;
    }
};

int solution(vector<vector<int>> board) {
    int N = board.size();

    // 맵 외벽 1칸 감싸기 (N+2 x N+2)
    vector<vector<int>> map(N + 2, vector<int>(N + 2, 1));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            map[i + 1][j + 1] = board[i][j];
        }
    }

    queue<Robot> q;
    set<Robot> visited;

    // 시작점 (1, 1) ~ (1, 2)
    Robot start({1, 1}, {1, 2}, 0);
    q.push(start);
    visited.insert(start);

    Point target = {N, N};

    // 상, 하, 좌, 우
    int dr[] = {-1, 1, 0, 0};
    int dc[] = {0, 0, -1, 1};

    while (!q.empty()) {
        Robot cur = q.front();
        q.pop();

        // 목적지 도달 체크
        if (cur.p1 == target || cur.p2 == target) {
            return cur.time;
        }

        vector<Robot> next_moves;

        // 1. 평행 이동 (상하좌우 4방향)
        for (int i = 0; i < 4; i++) {
            Point np1 = {cur.p1.r + dr[i], cur.p1.c + dc[i]};
            Point np2 = {cur.p2.r + dr[i], cur.p2.c + dc[i]};

            if (map[np1.r][np1.c] == 0 && map[np2.r][np2.c] == 0) {
                next_moves.push_back(Robot(np1, np2, cur.time + 1));
            }
        }

        // 2. 가로 상태일 때 세로 회전 (위, 아래)
        if (cur.p1.r == cur.p2.r) {
            int check_r[] = {-1, 1};
            for (int d : check_r) {
                int nr = cur.p1.r + d;
                // 위 또는 아래의 두 칸이 모두 비어있는 경우
                if (map[nr][cur.p1.c] == 0 && map[nr][cur.p2.c] == 0) {
                    next_moves.push_back(Robot(cur.p1, {nr, cur.p1.c}, cur.time + 1));
                    next_moves.push_back(Robot(cur.p2, {nr, cur.p2.c}, cur.time + 1));
                }
            }
        }
        // 3. 세로 상태일 때 가로 회전 (좌, 우)
        else if (cur.p1.c == cur.p2.c) {
            int check_c[] = {-1, 1};
            for (int d : check_c) {
                int nc = cur.p1.c + d;
                // 좌 또는 우의 두 칸이 모두 비어있는 경우
                if (map[cur.p1.r][nc] == 0 && map[cur.p2.r][nc] == 0) {
                    next_moves.push_back(Robot(cur.p1, {cur.p1.r, nc}, cur.time + 1));
                    next_moves.push_back(Robot(cur.p2, {cur.p2.r, nc}, cur.time + 1));
                }
            }
        }

        // 방문 검사 및 큐 삽입
        for (const Robot& next : next_moves) {
            if (visited.find(next) == visited.end()) {
                visited.insert(next);
                q.push(next);
            }
        }
    }

    return 0;
}