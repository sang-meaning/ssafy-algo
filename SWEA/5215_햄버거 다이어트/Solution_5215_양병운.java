import java.util.*;
import java.io.*;

class Solution {
    static int N, L, max;
    static int[] sweet;
    static int[] cal;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            sweet = new int[N];
            cal = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                sweet[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
            }

            max = 0;
            dfs(0, 0, 0);
            System.out.println("#" + test_case + " " + max);
        }
    }

    public static void dfs(int index, int totalSweet, int totalCal) {
        if (totalCal > L) return;
        if (index == N) {
            max = Math.max(max, totalSweet);
            return;
        }

        dfs(index + 1, totalSweet + sweet[index],totalCal + cal[index]);
        dfs(index + 1, totalSweet, totalCal);
    }
}