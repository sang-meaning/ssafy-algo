import java.util.*;
import java.io.*;
class Solution {
    static class House {
        int x, y;
        public House(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getDistance(House o){
            return Math.abs(this.x - o.x) + Math.abs(this.y - o.y);
        }
    }
    static House[] houses;
    static int N, min;
    static int[] result;
    static boolean[] visited;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine()) + 2;
            houses = new House[N];
            visited = new boolean[N];
            min = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            houses[0] = new House(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            houses[1] = new House(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for(int i=2; i<N; i++) houses[i] = new House(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            //회사 - houses - home
            int[] result = new int[N];
            Arrays.fill(result, Integer.MAX_VALUE);
            result[0] = 0;
            dfs(0, 0, 0);
            System.out.println("#"+test_case+" "+min);
		}
	}
    public static void dfs(int idx, int dist, int count){
        if(dist >= min) return;
        House h = houses[idx];
        if(count==N-2) {
            min = Math.min(min, dist+h.getDistance(houses[1]));
            return;
        }
        for(int i=2; i<N; i++){
            if(visited[i]) continue;
            visited[i] = true;
            int d = dist + h.getDistance(houses[i]);
            dfs(i, d, count+1);
            visited[i] = false;
        }
    }
}