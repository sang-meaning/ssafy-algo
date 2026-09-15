import java.io.*;
import java.util.*;

public class Solution_5644_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    static class BatteryCharger {
        int x, y, c, p;

        BatteryCharger(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }

        boolean isInRange(int userX, int userY) {
            return Math.abs(this.x - userX) + Math.abs(this.y - userY) <= this.c;
        }
    }

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken()); // 총 이동 시간
            int A = Integer.parseInt(st.nextToken()); // BC 개수

            int[] pathA = new int[M];
            int[] pathB = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                pathA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                pathB[i] = Integer.parseInt(st.nextToken());
            }

            BatteryCharger[] bcList = new BatteryCharger[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bcList[i] = new BatteryCharger(x, y, c, p);
            }

            int ax = 1, ay = 1;
            int bx = 10, by = 10;

            int totalCharge = 0;

            for (int time = 0; time <= M; time++) {
                ArrayList<Integer> bcA = new ArrayList<>();
                ArrayList<Integer> bcB = new ArrayList<>();

                for (int i = 0; i < A; i++) {
                    if (bcList[i].isInRange(ax, ay)) bcA.add(i);
                    if (bcList[i].isInRange(bx, by)) bcB.add(i);
                }

                int maxCharge = 0;

                if (bcA.isEmpty() && bcB.isEmpty()) {
                    maxCharge = 0;
                } else if (!bcA.isEmpty() && bcB.isEmpty()) {
                    for (int aIdx : bcA) {
                        maxCharge = Math.max(maxCharge, bcList[aIdx].p);
                    }
                } else if (bcA.isEmpty() && !bcB.isEmpty()) {
                    for (int bIdx : bcB) {
                        maxCharge = Math.max(maxCharge, bcList[bIdx].p);
                    }
                } else {
                    for (int aIdx : bcA) {
                        for (int bIdx : bcB) {
                            if (aIdx == bIdx) {
                                maxCharge = Math.max(maxCharge, bcList[aIdx].p);
                            } else {
                                maxCharge = Math.max(maxCharge, bcList[aIdx].p + bcList[bIdx].p);
                            }
                        }
                    }
                }

                totalCharge += maxCharge;

                if (time == M) break;

                ax += dx[pathA[time]];
                ay += dy[pathA[time]];
                bx += dx[pathB[time]];
                by += dy[pathB[time]];
            }

            sb.append("#").append(tc).append(" ").append(totalCharge).append("\n");
        }

        System.out.print(sb);
    }
}