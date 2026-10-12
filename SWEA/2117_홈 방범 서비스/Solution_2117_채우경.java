import java.util.*;
import java.io.*;

public class Solution {
    private static int[][] field;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            field = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    field[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max_house = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 1; k <= N + 2; k++) {
                        int manageCost = k * k + (k - 1) * (k - 1);
                        int h = findHouse(i, j, k);

                        if (manageCost <= h * M && max_house < h) {
                            max_house = h;
                        }
                    }
                }
            }

            sb.append('#').append(t).append(' ').append(max_house).append('\n');
        }

        System.out.print(sb);
    }

    private static int findHouse(int r, int c, int rng) {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int distance = Math.abs(r - i) + Math.abs(c - j);
                if (distance < rng && field[i][j] == 1) cnt++;
            }
        }

        return cnt;
    }
}