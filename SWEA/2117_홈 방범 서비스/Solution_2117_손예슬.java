
import java.io.*;
import java.util.*;

public class Solution {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;

            // 모든 중심점 탐색
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    // 거리별 집 개수
                    int[] count = new int[N + 1];

                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            if (map[i][j] == 0) continue;

                            int dist = Math.abs(r - i)
                                     + Math.abs(c - j);

                            if (dist <= N) {
                                count[dist]++;
                            }
                        }
                    }

                    int house = 0;

                    // 서비스 영역 크기 K
                    for (int k = 1; k <= N + 1; k++) {
                        house += count[k - 1];

                        int cost = k * k + (k - 1) * (k - 1);
                        int profit = house * M - cost;

                        if (profit >= 0) {
                            answer = Math.max(answer, house);
                        }
                    }
                }
            }

            sb.append("#").append(tc)
              .append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
