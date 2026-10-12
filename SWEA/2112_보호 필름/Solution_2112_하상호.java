package swea;

import java.io.*;
import java.util.*;

public class Solution_2112_하상호 {

    static int D, W, K;

    static int[][] film;

    static int answer;

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st =
                    new StringTokenizer(br.readLine());

            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];

            for (int i = 0; i < D; i++) {

                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < W; j++) {
                    film[i][j] =
                            Integer.parseInt(st.nextToken());
                }
            }

            if (K == 1 || check()) {
                answer = 0;
            } else {
                answer = K;

                dfs(0, 0);
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int row, int count) {

        // 이미 현재 정답보다 많이 사용했으면 중단
        if (count >= answer) {
            return;
        }

        // 성능검사 통과
        if (check()) {
            answer = count;
            return;
        }

        if (row == D) {
            return;
        }

        int[] backup = film[row].clone();

        // 1. 약품을 투입하지 않음
        dfs(row + 1, count);

        // 2. A 약품 투입
        Arrays.fill(film[row], 0);
        dfs(row + 1, count + 1);

        // 3. B 약품 투입
        Arrays.fill(film[row], 1);
        dfs(row + 1, count + 1);

        // 원상복구
        film[row] = backup;
    }

    static boolean check() {

        for (int col = 0; col < W; col++) {

            int count = 1;

            boolean pass = false;

            for (int row = 1; row < D; row++) {

                if (film[row][col]
                        == film[row - 1][col]) {

                    count++;

                } else {

                    count = 1;
                }

                if (count >= K) {
                    pass = true;
                    break;
                }
            }

            if (K == 1) {
                pass = true;
            }

            if (!pass) {
                return false;
            }
        }

        return true;
    }
}