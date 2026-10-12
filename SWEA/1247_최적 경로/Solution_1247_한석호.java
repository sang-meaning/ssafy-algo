import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution_1247_한석호 {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static Point company;
    static Point home;
    static Point[] customers;
    static boolean[] visited;
    static int minDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());

            st = new StringTokenizer(br.readLine().trim());

            // 1. 회사 및 집 좌표 입력
            company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            // 2. N명의 고객 좌표 입력
            customers = new Point[N];
            for (int i = 0; i < N; i++) {
                customers[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            visited = new boolean[N];
            minDistance = Integer.MAX_VALUE;

            // 3. 회사 위치에서 DFS 탐색 시작 (방문 수 0, 현재 누적 거리 0)
            dfs(0, company, 0);

            // 4. 결과 출력
            System.out.println("#" + tc + " " + minDistance);
        }
    }

    /**
     * @param count     현재까지 방문한 고객의 수
     * @param current   현재 위치 (Point)
     * @param sumDist   현재까지 누적된 이동 거리
     */
    public static void dfs(int count, Point current, int sumDist) {
        // 이미 구한 최소 거리보다 커지면 탐색 중단 (가지치기)
        if (sumDist >= minDistance) {
            return;
        }

        // 모든 고객을 다 방문한 경우
        if (count == N) {
            // 마지막 고객 집에서 최종 집(home)까지의 거리 합산
            sumDist += getDistance(current, home);
            minDistance = Math.min(minDistance, sumDist);
            return;
        }

        // 다음 고객 방문 순열 생성
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int dist = getDistance(current, customers[i]);
                dfs(count + 1, customers[i], sumDist + dist);
                visited[i] = false; // 원상복구
            }
        }
    }

    // 두 좌표 간의 맨해튼 거리 (|x1 - x2| + |y1 - y2|) 계산 함수
    public static int getDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}