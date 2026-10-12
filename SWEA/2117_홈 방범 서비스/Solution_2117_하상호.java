package cording_prac;

import java.util.*;
import java.io.*;

public class Solution_2117_하상호 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            List<int[]> houses = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    if (Integer.parseInt(st.nextToken()) == 1) {
                        houses.add(new int[]{r, c});
                    }
                }
            }

            int answer = 0;

            // 모든 위치를 서비스 중심으로 탐색
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    // 중심과의 거리별 집 개수 계산
                    int[] distanceCount = new int[N + 1];

                    for (int[] house : houses) {
                        int dist = Math.abs(r - house[0])
                                 + Math.abs(c - house[1]);

                        if (dist <= N) {
                            distanceCount[dist]++;
                        }
                    }

                    int coveredHouses = 0;

                    // 가능한 서비스 영역 크기 탐색
                    for (int K = 1; K <= N + 1; K++) {
                        coveredHouses += distanceCount[K - 1];

                        int operatingCost =
                                K * K + (K - 1) * (K - 1);

                        // 수익이 운영 비용 이상인 경우
                        if (coveredHouses * M >= operatingCost) {
                            answer = Math.max(answer, coveredHouses);
                        }
                    }
                }
            }

            sb.append('#').append(tc).append(' ')
              .append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
