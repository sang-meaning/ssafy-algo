
import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int P;
    static int answer;

    static ArrayList<int[]> people;
    static ArrayList<int[]> stairs;

    static int[][] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            people = new ArrayList<>();
            stairs = new ArrayList<>();

            for (int r = 0; r < N; r++) {

                StringTokenizer st =
                        new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {

                    int num = Integer.parseInt(st.nextToken());

                    if (num == 1) {
                        people.add(new int[]{r, c});
                    } else if (num >= 2) {
                        stairs.add(new int[]{r, c, num});
                    }
                }
            }

            P = people.size();

            // 사람과 계단 사이의 거리 미리 계산
            dist = new int[P][2];

            for (int i = 0; i < P; i++) {

                int[] person = people.get(i);

                for (int j = 0; j < 2; j++) {

                    int[] stair = stairs.get(j);

                    dist[i][j] =
                            Math.abs(person[0] - stair[0])
                          + Math.abs(person[1] - stair[1]);
                }
            }

            answer = Integer.MAX_VALUE;

            // 각 사람이 이용할 계단 선택
            for (int mask = 0; mask < (1 << P); mask++) {

                ArrayList<Integer> stair0 = new ArrayList<>();
                ArrayList<Integer> stair1 = new ArrayList<>();

                for (int i = 0; i < P; i++) {

                    if ((mask & (1 << i)) == 0) {
                        stair0.add(dist[i][0]);
                    } else {
                        stair1.add(dist[i][1]);
                    }
                }

                int time0 = calculateTime(stair0, stairs.get(0)[2]);
                int time1 = calculateTime(stair1, stairs.get(1)[2]);

                int totalTime = Math.max(time0, time1);

                answer = Math.min(answer, totalTime);
            }

            sb.append("#").append(tc)
              .append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static int calculateTime(ArrayList<Integer> arrivals, int K) {

        if (arrivals.isEmpty()) {
            return 0;
        }

        // 계단에 먼저 도착하는 사람부터 정렬
        Collections.sort(arrivals);

        int size = arrivals.size();

        // 각 사람이 계단을 완전히 내려간 시각
        int[] finish = new int[size];

        for (int i = 0; i < size; i++) {

            // 도착한 뒤 1분 후 계단에 진입 가능
            int start = arrivals.get(i) + 1;

            // 이미 3명이 내려가고 있다면 대기
            if (i >= 3) {
                start = Math.max(start, finish[i - 3]);
            }

            // 계단 길이 K만큼 내려감
            finish[i] = start + K;
        }

        return finish[size - 1];
    }
}
