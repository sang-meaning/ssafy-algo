import java.util.*;
import java.io.*;
class Solution {
    static int min;
    static int[] term;
    static int[] month;
    static boolean[] visited;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            term = new int[4];
            for(int i=0; i<4; i++) term[i] = Integer.parseInt(st.nextToken());
             
            month = new int[12];
            visited = new boolean[12];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<12; i++) month[i] = Integer.parseInt(st.nextToken());
            min = Integer.MAX_VALUE;
            dfs(0, 0);
            if(min>term[3]) min = term[3];
            System.out.println("#"+test_case+" "+min);
        }
    }
    public static void dfs(int m, int sum) {
        if(m >= 12) {
            min = Math.min(min, sum);
            return;
        }
        int days = month[m];
        dfs(m+1, sum + days*term[0]);
        dfs(m+1, sum + term[1]);
        dfs(m+3, sum + term[2]);
    }
}