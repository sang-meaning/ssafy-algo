import java.util.*;
import java.io.*;

class Solution {
    static int N, B;
    static int[] height;
    static int min;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            height = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) height[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(height);
            min = Integer.MAX_VALUE;
            dfs(0, 0);
            System.out.println("#"+tc+" "+min);
        }
    }


    static void dfs(int start, int sum){
        if(sum >= B){
            min = Math.min(min, sum-B);
            return;
        }
        for(int i=start; i<N; i++){
            dfs(i+1, sum + height[i]);
        }
    }
}