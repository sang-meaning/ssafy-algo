import java.io.*;
import java.util.*;

public class Solution {
    static class BC {
        int x, y, c, p;
        BC(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }

    // 0: 정지, 1: 상, 2: 우, 3: 하, 4: 좌
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    static int M, bcCount;
    static int[] pathA, pathB;
    static BC[] bcList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            bcCount = Integer.parseInt(st.nextToken());

            pathA = new int[M];
            pathB = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                pathA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                pathB[i] = Integer.parseInt(st.nextToken());
            }

            bcList = new BC[bcCount];
            for (int i = 0; i < bcCount; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bcList[i] = new BC(x, y, c, p);
            }

            int ax = 1, ay = 1;
            int bx = 10, by = 10;

            int totalCharge = getMaxCharge(ax, ay, bx, by);

            for (int t = 0; t < M; t++) {
                ax += dx[pathA[t]];
                ay += dy[pathA[t]];
                bx += dx[pathB[t]];
                by += dy[pathB[t]];

                totalCharge += getMaxCharge(ax, ay, bx, by);
            }

            sb.append("#").append(tc).append(" ").append(totalCharge).append("\n");
        }

        System.out.print(sb);
    }

    static int getMaxCharge(int ax, int ay, int bx, int by) {
        int max = 0;

        for (int a = 0; a < bcCount; a++) {
            for (int b = 0; b < bcCount; b++) {
                int sum = 0;
                int chargeA = check(a, ax, ay);
                int chargeB = check(b, bx, by);

                if (a != b) {
                    sum = chargeA + chargeB;
                } else {
                    sum = Math.max(chargeA, chargeB);
                }

                max = Math.max(max, sum);
            }
        }

        return max;
    }

    static int check(int idx, int x, int y) {
        BC bc = bcList[idx];
        int dist = Math.abs(bc.x - x) + Math.abs(bc.y - y);
        return dist <= bc.c ? bc.p : 0;
    }
}