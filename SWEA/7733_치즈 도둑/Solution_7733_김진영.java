import java.util.*;
import java.io.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int T,N;
	static int[][] map;
	static int[][] eat;
	static int result;
	static Deque<int[]> q;
	static int max;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	
    public static void main(String[] args) throws Exception{
    	
    	T = Integer.parseInt(br.readLine());
    	for(int tc=1;tc<=T;tc++) {
    		//변수 초기화
    		N = Integer.parseInt(br.readLine()); 
    		max = 0;
    		result = 0;
    		map = new int[N][N];
    		eat = new int[N][N];
    		q = new ArrayDeque<>();
    		
    		for(int i=0;i<N;i++) {
    			st = new StringTokenizer(br.readLine());
    			for(int j=0;j<N;j++) {
    				map[i][j] = Integer.parseInt(st.nextToken());
    				max = Math.max(max, map[i][j]);
    			}
    		}

    		int[][] visited = new int[N][N];
    		for(int k=0;k<=max;k++) {
    			//eat으로 덩어리 체크 + bfs
    			int count = 0;        		
    			for(int i=0;i<N;i++) {
        			for(int j=0;j<N;j++) {
        				if(eat[i][j] == 0 && visited[i][j] == 0) {
        					q.add(new int[] {i,j});
        					visited[i][j] = 1;
        					count++;
        					
        					while(!q.isEmpty()) {
        						int temp[] = q.poll();
        						int cx = temp[0];
        						int cy = temp[1];
        						
        						for(int dir=0;dir<4;dir++) {
        							int nx = cx + dx[dir];
        							int ny = cy + dy[dir];
        							
        							if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
        								continue;
        							}
        							
        							if(visited[nx][ny] == 1) {
        								continue;
        							}
        							
        							visited[nx][ny] = 1;
        							q.add(new int[] {nx,ny});
        						}
        						
        						
        					}
        					
        				}
        			}
    			}    			
    			result = Math.max(result, count);
    			
    			//i,j인 좌표를 eat하기
    			for(int i=0;i<N;i++) {
        			for(int j=0;j<N;j++) {
        				visited[i][j] = 0;
        				if(map[i][j] == k) {
        					eat[i][j] = 1;
        				}
        				if(map[i][j] <= k) {
        					visited[i][j] = 1;
        				}
        			}
        		}
    		}

			
    		sb.append("#"+tc+" "+result+"\n");
    	}
    	System.out.print(sb);
		
	}
}