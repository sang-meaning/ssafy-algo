import java.io.*;
import java.util.*;

public class Solution {

    static int peopleCount;
    static int[][] distance; // distance[i][s]: i번 사람 → s번 계단 거리
    static int[] stairLength;
    static int[] selected;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

            List<int[]> people = new ArrayList<>();
            List<int[]> stairs = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    int value = Integer.parseInt(st.nextToken());

                    if (value == 1) {
                        people.add(new int[]{r, c});
                    } else if (value > 1) {
                        stairs.add(new int[]{r, c, value});
                    }
                }
            }

            peopleCount = people.size();
            distance = new int[peopleCount][2];
            stairLength = new int[2];
            selected = new int[peopleCount];
            answer = Integer.MAX_VALUE;

            // 사람마다 두 계단까지의 거리를 미리 계산
            for (int s = 0; s < 2; s++) {
                int[] stair = stairs.get(s);
                stairLength[s] = stair[2];

                for (int i = 0; i < peopleCount; i++) {
                    int[] person = people.get(i);

                    distance[i][s] =
                            Math.abs(person[0] - stair[0])
                            + Math.abs(person[1] - stair[1]);
                }
            }

            dfs(0);

            sb.append('#').append(tc)
              .append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    // 각 사람이 이용할 계단 배정
    static void dfs(int idx) {
        if (idx == peopleCount) {
            int time0 = calculateTime(0);
            int time1 = calculateTime(1);

            int totalTime = Math.max(time0, time1);
            answer = Math.min(answer, totalTime);
            return;
        }

        selected[idx] = 0;
        dfs(idx + 1);

        selected[idx] = 1;
        dfs(idx + 1);
    }

    // stair번 계단에 배정된 사람들이 모두 내려가는 시각
    static int calculateTime(int stair) {
        int[] arrival = new int[peopleCount];
        int count = 0;

        for (int i = 0; i < peopleCount; i++) {
            if (selected[i] == stair) {
                arrival[count++] = distance[i][stair];
            }
        }

        if (count == 0) {
            return 0;
        }

        // 실제로 배정된 사람들의 도착 시간만 정렬
        Arrays.sort(arrival, 0, count);

        int[] finish = new int[count];
        int length = stairLength[stair];

        for (int i = 0; i < count; i++) {
            // 도착한 뒤 1분 후부터 내려갈 수 있음
            int start = arrival[i] + 1;

            // 동시에 3명까지 이용하므로 자리 확보 시각 확인
            if (i >= 3) {
                start = Math.max(start, finish[i - 3]);
            }

            finish[i] = start + length;
        }

        return finish[count - 1];
    }
}