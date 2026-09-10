package swea;

import java.io.*;
import java.util.*;

public class Solution_1486_하상호 {

    static int N, B;
    static int[] height;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            height = new int[N];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }

            min = Integer.MAX_VALUE;

            dfs(0, 0);

            System.out.println("#" + tc + " " + (min - B));
        }
    }

    static void dfs(int index, int sum) {

        // 선반 높이 이상이 되면 최솟값 갱신
        if (sum >= B) {
            min = Math.min(min, sum);
            return;
        }

        // 모든 직원을 확인한 경우
        if (index == N) {
            return;
        }

        // 현재 직원을 선택하는 경우
        dfs(index + 1, sum + height[index]);

        // 현재 직원을 선택하지 않는 경우
        dfs(index + 1, sum);
    }
}