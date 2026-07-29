import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,1,-1};
	static int answer = 0;
	
	public static void main(String args[]) throws Exception
	{
	
	    Scanner sc = new Scanner(System.in);
	    int T;
	    T=sc.nextInt();
	
	
	    for(int test_case = 1; test_case <= T; test_case++)
	    {
	        int N;
	        N=sc.nextInt();
	        answer = 0;
	        int[][] map = new int[N][N];
	        boolean[][] visited = new boolean[N][N];

	        int K;
	        K=sc.nextInt();
	        
	        int max = 0;
	        for(int i=0;i<N;i++) {
	            for(int j=0;j<N;j++) {
	            		map[i][j] = sc.nextInt();
	                max = Math.max(max, map[i][j]);
	            }
	        }
	
	        for(int i=0;i<N;i++) {
	            for(int j=0;j<N;j++) {
	                int count = 1;
	                if(max == map[i][j]) {
	                		visited[i][j] = true;
	                    dfs(map,i,j,K,N,count,visited);
                			visited[i][j] = false;
	                }	                
	            }
	        }
	        System.out.printf("#%d %d\n",test_case,answer);
	    }
	}
	
	private static void dfs(int[][] map, int cur_x, int cur_y, int dig, int N, int count, boolean[][] visited) {
		answer = Math.max(answer, count);
	    for(int dir=0;dir<4;dir++) {
	        int nx = cur_x + dx[dir];
	        int ny = cur_y + dy[dir];
	        
	        if((nx >= 0 && ny >= 0 && nx < N && ny < N) && visited[nx][ny] == false) { //범위 안이고
	            if(map[cur_x][cur_y] > map[nx][ny]) { //높이가 낮다면
	                visited[nx][ny] = true;
	                dfs(map,nx,ny,dig,N,count+1,visited);
	                visited[nx][ny] = false;
	            }else if(dig > 0 && (map[cur_x][cur_y] > map[nx][ny] - dig)) { // 10 12 4 땅을 파서 갈 수 있다면
	            	int temp = map[nx][ny];
	                visited[nx][ny] = true;
	                map[nx][ny] = map[cur_x][cur_y] -1;
	                dfs(map,nx,ny,0,N,count+1,visited);
	                visited[nx][ny] = false;
	                map[nx][ny] = temp;
	            }
	        }
	    }
	}
}