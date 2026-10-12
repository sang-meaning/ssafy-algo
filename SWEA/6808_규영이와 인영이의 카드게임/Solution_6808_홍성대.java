import java.io.*;
import java.util.*;

public class Solution {
    static int[] gyu = new int[9];
    static int[] iny = new int[9];
    static boolean[] visited = new boolean[9];
    static int winCnt, loseCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean[] usedCards = new boolean[19];

            for (int i = 0; i < 9; i++) {
                gyu[i] = Integer.parseInt(st.nextToken());
                usedCards[gyu[i]] = true;
            }

            int idx = 0;
            for (int i = 1; i <= 18; i++) {
                if (!usedCards[i]) {
                    iny[idx++] = i;
                }
            }

            winCnt = 0;
            loseCnt = 0;

            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(winCnt).append(" ").append(loseCnt).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int round, int gyuScore, int inyScore) {
        if (round == 9) {
            if (gyuScore > inyScore) winCnt++;
            else if (gyuScore < inyScore) loseCnt++;
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            int sum = gyu[round] + iny[i];

            if (gyu[round] > iny[i]) {
                dfs(round + 1, gyuScore + sum, inyScore);
            } else {
                dfs(round + 1, gyuScore, inyScore + sum);
            }

            visited[i] = false;
        }
    }
}