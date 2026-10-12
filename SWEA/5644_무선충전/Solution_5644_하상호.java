package swea;

import java.io.*;
import java.util.*;

public class Solution_5644_하상호 {

    static int M, BC_COUNT;

    static int[] moveA;
    static int[] moveB;

    // 0: 이동X, 1: 상, 2: 우, 3: 하, 4: 좌
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    // BC[i] = {x, y, 충전범위, 성능}
    static int[][] BC;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            BC_COUNT = Integer.parseInt(st.nextToken());

            moveA = new int[M];
            moveB = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                moveA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                moveB[i] = Integer.parseInt(st.nextToken());
            }

            BC = new int[BC_COUNT][4];

            for (int i = 0; i < BC_COUNT; i++) {

                st = new StringTokenizer(br.readLine());

                BC[i][0] = Integer.parseInt(st.nextToken()); // x
                BC[i][1] = Integer.parseInt(st.nextToken()); // y
                BC[i][2] = Integer.parseInt(st.nextToken()); // C
                BC[i][3] = Integer.parseInt(st.nextToken()); // P
            }

            // A 시작 위치
            int ax = 1;
            int ay = 1;

            // B 시작 위치
            int bx = 10;
            int by = 10;

            int answer = 0;

            // 시작 위치에서도 충전 가능
            for (int time = 0; time <= M; time++) {

                // 현재 위치에서 최대 충전량
                answer += getMaxCharge(ax, ay, bx, by);

                // 마지막 시간이라면 이동할 필요 없음
                if (time == M) {
                    break;
                }

                // 사용자 A 이동
                ax += dx[moveA[time]];
                ay += dy[moveA[time]];

                // 사용자 B 이동
                bx += dx[moveB[time]];
                by += dy[moveB[time]];
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    // 현재 위치에서 두 사용자가 얻을 수 있는 최대 충전량
    static int getMaxCharge(int ax, int ay, int bx, int by) {

        int max = 0;

        // A가 선택할 BC
        for (int i = 0; i < BC_COUNT; i++) {

            // B가 선택할 BC
            for (int j = 0; j < BC_COUNT; j++) {

                int chargeA = 0;
                int chargeB = 0;

                if (canCharge(ax, ay, i)) {
                    chargeA = BC[i][3];
                }

                if (canCharge(bx, by, j)) {
                    chargeB = BC[j][3];
                }

                int sum;

                // 같은 BC를 사용하는 경우
                if (i == j) {
                    sum = Math.max(chargeA, chargeB);
                }

                // 서로 다른 BC
                else {
                    sum = chargeA + chargeB;
                }

                max = Math.max(max, sum);
            }
        }

        return max;
    }

    // 사용자가 BC의 충전 범위 안에 있는지 확인
    static boolean canCharge(int x, int y, int bcIndex) {

        int bcX = BC[bcIndex][0];
        int bcY = BC[bcIndex][1];
        int range = BC[bcIndex][2];

        int distance = Math.abs(x - bcX) + Math.abs(y - bcY);

        return distance <= range;
    }
}