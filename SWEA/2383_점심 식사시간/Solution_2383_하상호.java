package cording_prac;
import java.io.*;
import java.util.*;

public class Solution_2383_하상호 {
    static int peopleCount;
    static int[][] arrival;
    static int[] stairLength = new int[2];
    static int[] choice;
    static int answer;

    static int stairFinishTime(int[] times, int count, int length) {
        if (count == 0) return 0;

        Arrays.sort(times, 0, count);
        int[] finish = new int[count];

        for (int i = 0; i < count; i++) {
            int start = times[i];

            // 계단에는 최대 3명만 진입 가능
            if (i >= 3) {
                start = Math.max(start, finish[i - 3]);
            }

            finish[i] = start + length;
        }

        return finish[count - 1];
    }

    static void dfs(int idx) {
        if (idx == peopleCount) {
            int[][] times = new int[2][peopleCount];
            int[] count = new int[2];

            for (int i = 0; i < peopleCount; i++) {
                int stair = choice[i];
                times[stair][count[stair]++] = arrival[i][stair];
            }

            int time0 = stairFinishTime(times[0], count[0], stairLength[0]);
            int time1 = stairFinishTime(times[1], count[1], stairLength[1]);

            answer = Math.min(answer, Math.max(time0, time1));
            return;
        }

        // 0번 계단 선택
        choice[idx] = 0;
        dfs(idx + 1);

        // 1번 계단 선택
        choice[idx] = 1;
        dfs(idx + 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

            List<int[]> people = new ArrayList<>();
            List<int[]> stairs = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    int cell = Integer.parseInt(st.nextToken());

                    if (cell == 1) {
                        people.add(new int[]{r, c});
                    } else if (cell >= 2) {
                        stairs.add(new int[]{r, c, cell});
                    }
                }
            }

            peopleCount = people.size();
            choice = new int[peopleCount];
            arrival = new int[peopleCount][2];

            // 사람과 각 계단의 거리 계산
            for (int s = 0; s < 2; s++) {
                int[] stair = stairs.get(s);
                stairLength[s] = stair[2];

                for (int i = 0; i < peopleCount; i++) {
                    int[] person = people.get(i);

                    int distance =
                        Math.abs(person[0] - stair[0]) +
                        Math.abs(person[1] - stair[1]);

                    // 계단 도착 후 1분 대기
                    arrival[i][s] = distance + 1;
                }
            }

            answer = Integer.MAX_VALUE;
            dfs(0);

            sb.append("#").append(tc).append(" ")
              .append(answer).append("\n");
        }

        System.out.print(sb);
    }
}