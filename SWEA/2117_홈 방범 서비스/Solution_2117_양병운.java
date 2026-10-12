import java.util.*;
import java.io.*;
 
class Solution {
    static int N, M, home;
    static int[][] matrix;
 
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            matrix = new int[N][N];
            int totalHome = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                    totalHome += matrix[i][j];
                }
            }
            home = 0;
 
            for (int k = 1; k <= 2 * N - 1; k++) {
                int cost = k * k + (k - 1) * (k - 1);
                if (cost > totalHome * M) break;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) dfs(i, j, k, cost);
                }
            }
            System.out.println("#" + test_case + " " + home);
        }
    }
 
    public static void dfs(int x, int y, int k, int cost) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (check(i, j, x, y, k) && matrix[i][j] == 1) {
                    count++;
                }
            }
        }
 
        if (count * M >= cost) home = Math.max(home, count);
    }
 
    public static boolean check(int a, int b, int c, int d, int k) {
        int diff = Math.abs(a - c) + Math.abs(b - d);
        return diff < k;
    }
}