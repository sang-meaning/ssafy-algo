import java.util.*;

public class Solution {
	
	static int n;
	static int[][] arr;
	static int count;
	static boolean[][] visited;
	
	//상하좌우 좌상 우상 좌하 우하
	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		for(int t=1; t<=test_case; t++) {
			
			
			n = sc.nextInt();
			
			arr = new int[n][n];
			
			count = 0;
			
			visited = new boolean[n][n];
			
			// 지뢰 = -1
			// 갈수있는 칸은 0;
			
			for(int i=0; i<n; i++) {
				String str = sc.next();
				for(int j=0; j<n; j++) {
					int num = 0;
					char ch = str.charAt(j);
					if(ch == '*') num = -1;
					else num = 0;
					arr[i][j] = num;
				}
			}
			
			
			
			//칸마다 근처 지뢰 몇개있는지 계산후 배열에 입력
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					
					if(arr[i][j] == -1) continue;
					
					int boom_cnt = 0;
					
					for(int k=0; k<8; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(nx>=0 && nx<n && ny>=0 && ny<n) {
							if(arr[nx][ny] == -1) boom_cnt++;
						}
					}
					
					arr[i][j] = boom_cnt;
					
				}
			}
			
			// 0 인 것만 bfs
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					
					if(!visited[i][j] && arr[i][j]==0) {
						bfs(i, j);
						count++;
					}
				}
			}
			
			
			// 나머지
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					
					if(!visited[i][j] && arr[i][j] != -1) {
						visited[i][j] = true;
						count++;
					}
				}
			}
			
			System.out.println("#" +t+" "+count);
			
			
			
		}
		

	}
	
	public static void bfs(int x, int y) {
		
		Queue<int[]> queue = new LinkedList<>();
		visited[x][y] = true;
		queue.offer(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int cur_x = cur[0];
			int cur_y = cur[1];
			
			for(int i=0; i<8; i++) {
				int nx = cur_x + dx[i];
				int ny = cur_y + dy[i];
				
				
				if(nx>=0 && nx<n && ny>=0 && ny<n && !visited[nx][ny]) {
					if(arr[nx][ny]==-1) continue;
					
					visited[nx][ny] = true;
					
					if(arr[nx][ny]==0) {	
						queue.add(new int[] {nx, ny});
					}
				}
				
			}
			
			
		}
		
	}

}

/*

* = 지뢰칸, . = 지뢰없는 칸
*  = -1

C = 클릭했을때 지뢰가 없는칸, 


1.테케
2. n (NxN의 크기)
3. n개의 줄만큼 n개의 문자열

*/