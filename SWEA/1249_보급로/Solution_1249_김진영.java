import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N;
	static int[][] map;
	static int[][] dist;
	static Deque<int[]> q;
	
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int INF = 0x3f3f3f3f;
	static ArrayList<int[]>[][] graph;
	static PriorityQueue<int[]> pq;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			dist = new int[N][N];
			
			for(int i=0;i<N;i++) {
				String temp = br.readLine();
				for(int j=0;j<N;j++) {
					map[i][j] = temp.charAt(j) - '0';
				}
			}
			
			graph = new ArrayList[N][N];
			pq = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					graph[i][j] = new ArrayList<>();
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					int cx = i;
					int cy = j;
					
					for(int dir=0;dir<4;dir++) {
						int nx = cx + dx[dir];
						int ny = cy + dy[dir];
						
						if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
							continue;
						}
						graph[cx][cy].add(new int[] {nx,ny,map[nx][ny]});
					}
				}
			}

			for(int i=0;i<N;i++) {
				Arrays.fill(dist[i], INF);
			}
			
			pq.add(new int[] {0,0,0});
			dist[0][0] = 0;
			
			while(!pq.isEmpty()) {
				int temp[] = pq.poll();
				
				int cx = temp[0];
				int cy = temp[1];
				int cur_cost = temp[2];
				
				if(dist[cx][cy] < cur_cost) {
					continue;
				}
				
				for(int[] edge : graph[cx][cy]) {
					int nx = edge[0];
					int ny = edge[1];
					int cost = edge[2];
					
					int nextCost = dist[cx][cy] + cost;
					
					if(nextCost < dist[nx][ny]) {
						dist[nx][ny] = nextCost;
						
						pq.add(new int[] {nx,ny,nextCost});
					}					
				}
			}

			sb.append("#").append(tc).append(" ").append(dist[N-1][N-1]).append("\n");
		}
		System.out.print(sb);
		
	}
}
