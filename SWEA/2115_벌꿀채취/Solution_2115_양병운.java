import java.util.*;
import java.io.*;
class Solution {
    static int N, M, C, max;
    static int[][] honeys;
    static boolean[][] visited;
    static int honeyProfit;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			/**
            	최대한 많은 수익을 얻으려고 함
                두 명의 일꾼이 있음
                벌통 수 M이 주어지면
                일꾼은 가로로 연속되도록 M개의 벌통을 선택 가능
                두 명의 일꾼이 선택한 벌통은 서로 겹치면 안된다.
                하나의 벌통에서 채취한 꿀은 하나의 용기에 담아야 한다.
                최대로 채취 가능한 벌통의 크기는 C
                각 용기에 있는 꿀의 양의 제곱만큼 수익이 생긴다.
            */
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            honeys = new int[N][N];
            visited = new boolean[N][N];
            max = Integer.MIN_VALUE;
            for(int i=0; i<N; i++) honeys[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dfs(0, 0);
            System.out.println("#"+test_case+" "+max);
		}
	}
    public static boolean check(int x, int y){
        for(int j=y; j<y+M; j++) {
            if(visited[x][j]) return false;
        }
        return true;
    }
    public static void doVisit(int x, int y){
        for(int j=y; j<y+M; j++) visited[x][j] = true;
    }
    public static void doUnvisit(int x, int y){
        for(int j=y; j<y+M; j++) visited[x][j] = false;
    }
    public static int getHoney(int x, int y) {
        honeyProfit = 0;
        calHoney(x, y, y, 0, 0);
        return honeyProfit;
    }
    public static void calHoney(int x, int startY, int y, int sum, int profit) {
        if (y == startY + M) {
            honeyProfit = Math.max(honeyProfit, profit);
            return;
        }
        calHoney(x, startY, y + 1, sum, profit);
        int honey = honeys[x][y];
        if (sum + honey <= C) calHoney(x, startY, y + 1, sum + honey, profit + honey * honey);
    }
    public static void dfs(int worker, int sum){
        if(worker==2){
            max = Math.max(max, sum);
            return;
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<=N-M; j++){
                if(!check(i, j)) continue;
                doVisit(i ,j);
                int honey = getHoney(i, j);
                dfs(worker+1, sum+honey);
                doUnvisit(i, j);
            }
        }
    }
}