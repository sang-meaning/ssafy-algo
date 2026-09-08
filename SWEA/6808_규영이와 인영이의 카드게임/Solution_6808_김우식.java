import java.io.*;
import java.util.*;

public class Solution {

    static int[] gyu = new int[9];
    static int[] iny = new int[9];
    static boolean[] visited = new boolean[9];

    static int win, lose;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            boolean[] used = new boolean[19];

            for (int i = 0; i < 9; i++) {
                gyu[i] = Integer.parseInt(st.nextToken());
                used[gyu[i]] = true;
            }

            int idx = 0;

            for (int i = 1; i <= 18; i++) {
                if (!used[i]) {
                    iny[idx++] = i;
                }
            }

            Arrays.fill(visited, false);

            win = 0;
            lose = 0;

            dfs(0, 0, 0);

            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(win)
                    .append(" ")
                    .append(lose)
                    .append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int round, int gyuScore, int inyScore) {

        if (round == 9) {

            if (gyuScore > inyScore) {
                win++;
            } else if (gyuScore < inyScore) {
                lose++;
            }

            return;
        }

        for (int i = 0; i < 9; i++) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;

            if (gyu[round] > iny[i]) {
                dfs(
                        round + 1,
                        gyuScore + gyu[round] + iny[i],
                        inyScore
                );
            } else {
                dfs(
                        round + 1,
                        gyuScore,
                        inyScore + gyu[round] + iny[i]
                );
            }

            visited[i] = false;
        }
    }
}