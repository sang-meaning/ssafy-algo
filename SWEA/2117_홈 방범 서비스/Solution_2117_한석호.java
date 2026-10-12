import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_2117_한석호.java {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N];
            List<Point> houses = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        houses.add(new Point(i, j));
                    }
                }
            }

            int maxHouses = 0;

            // K는 1부터 시작하여 최대 N + 2까지 탐색 (넉넉하게 커버)
            int maxK = N + 2;

            // 1. 모든 좌표(r, c)를 방범 서비스의 중심(기준점)으로 지정
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    
                    // 2. 가능한 모든 K 크기 탐색
                    for (int k = 1; k <= maxK; k++) {
                        int cost = k * k + (k - 1) * (k - 1);
                        int count = 0;

                        // 3. 집들과의 맨해튼 거리가 K 미만인지 확인
                        for (Point house : houses) {
                            int dist = Math.abs(house.r - r) + Math.abs(house.c - c);
                            if (dist < k) {
                                count++;
                            }
                        }

                        // 4. 손해를 보지 않는 경우 (수익 - 비용 >= 0)
                        if ((count * M) >= cost) {
                            if (count > maxHouses) {
                                maxHouses = count;
                            }
                        }
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(maxHouses).append("\n");
        }

        System.out.print(sb);
    }
}