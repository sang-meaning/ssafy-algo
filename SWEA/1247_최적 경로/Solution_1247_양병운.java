import java.util.*;
import java.io.*;
class Solution {
    static class Point {
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getDistance(Point p){
            return Math.abs(x - p.x) + Math.abs(y - p.y);
        }
    }
    static int N, min;
    static boolean[] visited;
    static Point[] home;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			/**
            	N(2~10)명의 고객을 방문하고 자신의 집으로 돌아감
                두 위치는 멘해튼 거리로 계산
                경로가 가장 짧은 것으로
                회사의 좌표, 집의 좌표를 포함한 모든 N+2개의 좌표는 서로 다른 위치에 있으며 좌표의 값은 0이상 100 이하의 정수로 이루어진다.
            */
            N = Integer.parseInt(br.readLine()) + 2;
            StringTokenizer st = new StringTokenizer(br.readLine());
            visited = new boolean[N];
            home = new Point[N];
            min = Integer.MAX_VALUE;
            for(int i=0; i<N; i++){
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                home[i] = new Point(x, y);
            }
            visited[0] = true;
            dfs(0, 0);
            System.out.println("#"+test_case+" "+min);
		}
	}
    public static void dfs(int idx, int dist){
        Point cur = home[idx];
        for(int i=2; i<N; i++){
            if(visited[i]) continue;
            int d = cur.getDistance(home[i]);
            if(dist+d >= min) continue;
            visited[i] = true;
            dfs(i, dist+d);
            visited[i] = false;
        }
        if(allVisited()) min = Math.min(min, dist+cur.getDistance(home[1]));
    }
    public static boolean allVisited(){
        for(int i=2; i<N; i++){
            if(!visited[i])  return false;
        }
        return true;
    }
}