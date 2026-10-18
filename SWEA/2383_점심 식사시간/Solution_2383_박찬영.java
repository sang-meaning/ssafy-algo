import java.io.*;
import java.util.*;

public class Solution {

    static int personCount;
    static int[][] people;
    static int[][] stairs;
    static int[][] distance;
    static int[] selected;
    static int answer;
    static void dfs(int depth) {

        if (depth == personCount) {

            int time0 = calculateTime(0);
            int time1 = calculateTime(1);
            int totalTime = Math.max(time0, time1);

            answer = Math.min(answer, totalTime);
            return;
        }

        selected[depth] = 0;
        dfs(depth + 1);

        selected[depth] = 1;
        dfs(depth + 1);
    }

    static int calculateTime(int stairNumber) {

        int[] arrival = new int[personCount];
        int count = 0;

        for (int i = 0; i < personCount; i++) {
            if (selected[i] == stairNumber) {
                arrival[count] = distance[i][stairNumber];
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        Arrays.sort(arrival, 0, count);

        int length = stairs[stairNumber][2];
        int[] finish = new int[count];

        for (int i = 0; i < count; i++) {

            int start = arrival[i] + 1;

            if (i >= 3) {
                start = Math.max(start, finish[i - 3]);
            }

            finish[i] = start + length;
        }

        return finish[count - 1];
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine().trim());

            people = new int[10][2];
            stairs = new int[2][3];

            personCount = 0;
            int stairCount = 0;

            for (int r = 0; r < N; r++) {

                StringTokenizer st =
                        new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {

                    int value = Integer.parseInt(st.nextToken());

                    if (value == 1) {

                        people[personCount][0] = r;
                        people[personCount][1] = c;
                        personCount++;

                    } else if (value >= 2) {

                        stairs[stairCount][0] = r;
                        stairs[stairCount][1] = c;
                        stairs[stairCount][2] = value;
                        stairCount++;
                    }
                }
            }

            distance = new int[personCount][2];
            selected = new int[personCount];

            for (int i = 0; i < personCount; i++) {
                for (int s = 0; s < 2; s++) {

                    distance[i][s] =
                            Math.abs(people[i][0] - stairs[s][0])
                            + Math.abs(people[i][1] - stairs[s][1]);
                }
            }

            answer = Integer.MAX_VALUE;

            dfs(0);

            sb.append("#").append(tc)
              .append(" ").append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }
}