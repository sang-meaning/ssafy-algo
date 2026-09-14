import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5644_유혜진 {
    static int M, A;
    static int[] moveA, moveB;
    static BatteryCharger[] bcs;
    
    // 이동 방향: 정지(0), 상(1), 우(2), 하(3), 좌(4)
    static int[] dr = {0, -1, 0, 1, 0};
    static int[] dc = {0, 0, 1, 0, -1};

    static class BatteryCharger {
        int r, c, cMax, p;
        public BatteryCharger(int c, int r, int cMax, int p) {
            this.r = r;
            this.c = c;
            this.cMax = cMax;
            this.p = p;
        }
        
        public boolean isInRange(int row, int col) {
            return Math.abs(this.r - row) + Math.abs(this.c - col) <= this.cMax;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            moveA = new int[M + 1];
            moveB = new int[M + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) moveA[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) moveB[i] = Integer.parseInt(st.nextToken());

            bcs = new BatteryCharger[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int cMax = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bcs[i] = new BatteryCharger(c, r, cMax, p);
            }

            int totalSum = solve();
            sb.append("#").append(t).append(" ").append(totalSum).append("\n");
        }
        System.out.print(sb);
    }

    static int solve() {
        int rA = 1, cA = 1;
        int rB = 10, cB = 10;
        int totalPower = 0;

        for (int time = 0; time <= M; time++) {
            cA += dc[moveA[time]];
            rA += dr[moveA[time]];
            cB += dc[moveB[time]];
            rB += dr[moveB[time]];

            totalPower += getMaxPower(rA, cA, rB, cB);
        }
        return totalPower;
    }

    static int getMaxPower(int rA, int cA, int rB, int cB) {
        int max = 0;

        for (int i = 0; i < A; i++) {
            for (int j = 0; j < A; j++) {
                int sum = 0;
                boolean aIn = bcs[i].isInRange(rA, cA);
                boolean bIn = bcs[j].isInRange(rB, cB);

                if (!aIn && !bIn) continue;

                if (i == j) {
                    if (aIn) sum += bcs[i].p;
                } else {
                    if (aIn) sum += bcs[i].p;
                    if (bIn) sum += bcs[j].p;
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}