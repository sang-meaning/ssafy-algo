import java.io.*;
import java.util.*;

public class Solution_2117_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] board = new int[N][N];
            int totalHomes = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1)
                        totalHomes++;
                }
            }

            int maxHomes = 0;

            for (int k = 1; k <= N + 1; k++) {
                int cost = k * k + (k - 1) * (k - 1);

                if (totalHomes * M < cost)
                    continue;

                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        int cnt = 0;

                        for (int i = 0; i < N; i++) {
                            for (int j = 0; j < N; j++) {
                                if (board[i][j] == 1) {
                                    int dist = Math.abs(r - i) + Math.abs(c - j);
                                    if (dist < k)
                                        cnt++;
                                }
                            }
                        }

                        if (cnt * M >= cost) {
                            maxHomes = Math.max(maxHomes, cnt);
                        }

                    }
                }
            }
            sb.append("#" + tc + " " + maxHomes).append("\n");
        }
        System.out.println(sb);
    }
}
