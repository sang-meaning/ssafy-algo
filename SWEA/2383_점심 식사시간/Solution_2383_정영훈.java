
import java.io.*;
import java.util.*;

class Solution {
    static int N, answer;
    static List<int[]> people;
    static int[][] stairs;
    static int[] selected;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            people = new ArrayList<>();
            stairs = new int[2][3];
            int stairIdx = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st =
                    new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    int value = Integer.parseInt(st.nextToken());

                    if (value == 1) {
                        people.add(new int[]{i, j});
                    } else if (value >= 2) {
                        stairs[stairIdx++] =
                            new int[]{i, j, value};
                    }
                }
            }

            selected = new int[people.size()];
            answer = Integer.MAX_VALUE;

            dfs(0);

            sb.append("#").append(tc).append(" ")
              .append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int depth) {
        if (depth == people.size()) {
            simulate();
            return;
        }

        selected[depth] = 0;
        dfs(depth + 1);

        selected[depth] = 1;
        dfs(depth + 1);
    }

    static void simulate() {
        List<Integer>[] arrival = new ArrayList[2];
        arrival[0] = new ArrayList<>();
        arrival[1] = new ArrayList<>();

        for (int i = 0; i < people.size(); i++) {
            int stair = selected[i];

            int[] p = people.get(i);
            int[] s = stairs[stair];

            int distance =
                Math.abs(p[0] - s[0]) +
                Math.abs(p[1] - s[1]);

            arrival[stair].add(distance);
        }

        int totalTime = 0;

        for (int s = 0; s < 2; s++) {
            Collections.sort(arrival[s]);

            int K = stairs[s][2];
            int[] finish = new int[arrival[s].size()];

            for (int i = 0; i < finish.length; i++) {
                // 도착한 다음 1분 후 진입 가능
                int start = arrival[s].get(i) + 1;

                // 동시에 3명까지만 이용 가능
                if (i >= 3) {
                    start = Math.max(start, finish[i - 3]);
                }

                finish[i] = start + K;
                totalTime = Math.max(totalTime, finish[i]);
            }
        }

        answer = Math.min(answer, totalTime);
    }
}
