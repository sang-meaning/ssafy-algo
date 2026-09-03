import java.util.*;
import java.io.*;

class Solution {
    static int max, N, M;
    static int[] weight;
    static boolean[] visited;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
            max = Integer.MIN_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            weight = new int[N];
            visited = new boolean[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) weight[i] = Integer.parseInt(st.nextToken());
            
            dfs(0, 0);
            if(max==Integer.MIN_VALUE) max = -1;
            System.out.println("#"+test_case+" "+max);
		}
	}
    public static void dfs(int depth, int sum){
        if(depth == 2) {
            if(sum > M) return;
            max = Math.max(max, sum);
            return;
        }
        for(int i=0; i<N; i++){
            if(visited[i]) continue;
            visited[i] = true;
            sum += weight[i];
            dfs(depth+1, sum);
            sum -= weight[i];
            visited[i] = false;
        }
    }
}