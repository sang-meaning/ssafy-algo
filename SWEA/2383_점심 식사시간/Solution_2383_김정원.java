import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static List<int[]> people;
    static int[][] stairs;   // 계단의 행, 열, 길이
    static int[][] distance; // 사람별 각 계단까지의 거리
    static int[] selected;   // 사람별 선택한 계단
    static int answer;

    public static void main(String[] args) throws Exception {
        // 로컬에서 input.txt로 실행할 때 주석 해제
        // System.setIn(new java.io.FileInputStream("input.txt"));

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

            people = new ArrayList<>();
            stairs = new int[2][3];
            int stairCount = 0;

            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    int value = Integer.parseInt(st.nextToken());

                    if (value == 1) {
                        people.add(new int[] {r, c});
                    } else if (value > 1) {
                        stairs[stairCount++] =
                                new int[] {r, c, value};
                    }
                }
            }

            int count = people.size();
            distance = new int[count][2];
            selected = new int[count];
            answer = Integer.MAX_VALUE;

            for (int i = 0; i < count; i++) {
                int[] person = people.get(i);

                for (int s = 0; s < 2; s++) {
                    distance[i][s] =
                            Math.abs(person[0] - stairs[s][0])
                            + Math.abs(person[1] - stairs[s][1]);
                }
            }

            dfs(0);

            sb.append('#').append(tc)
              .append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    // 각 사람이 이용할 계단을 선택
    static void dfs(int index) {
        if (index == people.size()) {
            int time0 = calculateTime(0);
            int time1 = calculateTime(1);

            // 두 계단을 동시에 이용하므로 늦게 끝나는 시간
            int totalTime = Math.max(time0, time1);
            answer = Math.min(answer, totalTime);
            return;
        }

        selected[index] = 0;
        dfs(index + 1);

        selected[index] = 1;
        dfs(index + 1);
    }

    // 해당 계단을 이용하는 사람들이 모두 내려가는 시간
    static int calculateTime(int stairIndex) {
        int[] ready = new int[people.size()];
        int count = 0;

        for (int i = 0; i < people.size(); i++) {
            if (selected[i] == stairIndex) {
                // 계단 도착 후 1분이 지나야 내려갈 수 있음
                ready[count++] = distance[i][stairIndex] + 1;
            }
        }

        if (count == 0) {
            return 0;
        }

        Arrays.sort(ready, 0, count);

        int[] finish = new int[count];
        int length = stairs[stairIndex][2];

        for (int i = 0; i < count; i++) {
            int startTime = ready[i];

            if (i >= 3) {
                // 최대 3명: 3명 앞선 사람이 끝나야 진입 가능
                startTime = Math.max(startTime, finish[i - 3]);
            }

            finish[i] = startTime + length;
        }

        return finish[count - 1];
    }
}