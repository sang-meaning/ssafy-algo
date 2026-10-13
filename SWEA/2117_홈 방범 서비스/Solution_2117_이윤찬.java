import java.io.*;
import java.util.*;

public class Solution_2117_이윤찬 {
    static int N;
    static int M;
    static ArrayList<int[]> houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            houses = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    int value = Integer.parseInt(st.nextToken());

                    if (value == 1) {
                        houses.add(new int[]{r, c});
                    }
                }
            }

            int answer = 0;

            // 도시 안의 모든 칸을 서비스 중심으로 선택
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    // 최대 거리: 2 * (N - 1)
                    int[] distanceCount = new int[2 * N - 1];

                    // 현재 중심을 기준으로 거리별 집의 수 계산
                    for (int[] house : houses) {
                        int distance = Math.abs(r - house[0]) + Math.abs(c - house[1]);
                        distanceCount[distance]++;
                    }
                    int houseCount = 0;
                    for (int k = 1; k <= 2 * N - 1; k++) {
                        houseCount += distanceCount[k - 1];

                        int cost = k * k + (k - 1) * (k - 1);
                        int revenue = houseCount * M;

                        if (revenue >= cost) {
                            answer = Math.max(answer, houseCount);
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }
}