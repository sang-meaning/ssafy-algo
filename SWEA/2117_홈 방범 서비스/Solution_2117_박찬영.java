import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st =
                    new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            ArrayList<int[]> houses = new ArrayList<>();

            for (int r = 0; r < N; r++) {

                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {

                    int value = Integer.parseInt(st.nextToken());

                    if (value == 1) {
                        houses.add(new int[] {r, c});
                    }
                }
            }

            int answer = 0;

            // 서비스 중심의 행
            for (int r = 0; r < N; r++) {

                // 서비스 중심의 열
                for (int c = 0; c < N; c++) {

                    // 서비스 크기
                    for (int K = 1; K <= 2 * N - 1; K++) {

                        int cost = K * K + (K - 1) * (K - 1);

                        if (cost > houses.size() * M) {
                            break;
                        }

                        int count = 0;

                        for (int[] house : houses) {

                            int distance =
                                    Math.abs(r - house[0])+ Math.abs(c - house[1]);

                            if (distance < K) {
                                count++;
                            }
                        }

                        if (count * M >= cost) {
                            answer = Math.max(answer, count);
                        }
                    }
                }
            }

            sb.append("#").append(tc)
              .append(" ").append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }
}