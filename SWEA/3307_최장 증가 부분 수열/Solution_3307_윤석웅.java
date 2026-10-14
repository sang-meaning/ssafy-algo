import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            dp = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

            int ans = 0;

            for(int i=0; i<N; i++) {
                // 자기 자신만 선택한 길이 1에서 시작
                dp[i] = 1;

                // i보다 앞의 작은 값 뒤에 arr[i]를 붙인다.
                for(int j=0; j<i; j++) {
                    if(arr[j] < arr[i])
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                }

                // LIS가 반드시 마지막 원소에서 끝나는 것은 아니다.
                ans = Math.max(ans, dp[i]);
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }
}
