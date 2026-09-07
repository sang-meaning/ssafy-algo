package swea;

import java.io.*;
import java.util.*;

public class Solution_6808_하상호 {

    static int[] gyu = new int[9];
    static int[] in = new int[9];

    static boolean[] used = new boolean[9];

    static int win;
    static int lose;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            boolean[] cardCheck = new boolean[19];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 9; i++) {
                gyu[i] = Integer.parseInt(st.nextToken());
                cardCheck[gyu[i]] = true;
            }

            int idx = 0;

            for (int card = 1; card <= 18; card++) {
                if (!cardCheck[card]) {
                    in[idx++] = card;
                }
            }

            used = new boolean[9];

            win = 0;
            lose = 0;

            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + win + " " + lose);
        }
    }

    static void dfs(int round, int gyuScore, int inScore) {

        // 9라운드 종료
        if (round == 9) {

            if (gyuScore > inScore) {
                win++;
            } else if (gyuScore < inScore) {
                lose++;
            }

            return;
        }

        for (int i = 0; i < 9; i++) {

            if (used[i]) {
                continue;
            }

            used[i] = true;

            int gyuCard = gyu[round];
            int inCard = in[i];

            if (gyuCard > inCard) {
                dfs(
                    round + 1,
                    gyuScore + gyuCard + inCard,
                    inScore
                );
            } else {
                dfs(
                    round + 1,
                    gyuScore,
                    inScore + gyuCard + inCard
                );
            }

            used[i] = false;
        }
    }
}