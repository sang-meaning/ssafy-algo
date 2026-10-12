import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dd {
	static int N;
	static int K;
	static int[][] arr;
	static int[] dx= {0,0,1,-1};
	static int[] dy= {1,-1,0,0};
	static boolean[][] visited;
	
	public static void main(String args[]) throws Exception{
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());

		 	int T =Integer.parseInt(st.nextToken());
	        for(int t=1;t<=T;t++) {
	        	
	      	  st = new StringTokenizer(br.readLine());

	      	   N = Integer.parseInt(st.nextToken());
	      	  
	      	  arr= new int[N][N];
	      	  visited = new boolean[N][N];
	      	  int max=0;
	      	  
	      	  for(int i=0;i<N;i++) {
		      	  st = new StringTokenizer(br.readLine());
	      		 for(int j=0;j<N;j++) {
		      		 
	      			 arr[i][j]=Integer.parseInt(st.nextToken());
	      			 
	      			 if(arr[i][j]>max) {
	      				 max=arr[i][j];//최대값
	      			 }
		      	  }
	      	  }
	      	  
	      	  
	      	  
	      	// k(날짜)가 가장 바깥에 있어야 함
	      	for (int k = 1; k <= 100; k++) {
	      	    
	      	    // 1. k일차에 해당하는 치즈를 전부 회색칠(방문 처리)
	      	    for (int i = 0; i < N; i++) {
	      	        for (int j = 0; j < N; j++) {
	      	            if (arr[i][j] == k) {
	      	                visited[i][j] = true; // 먹힌 치즈
	      	            }
	      	        }
	      	    }

	      	    // 2. DFS용 방문 배열(dfsVisited)을 만들고, 
	      	    //    회색칠 안 된(방문 안 한) 곳부터 DFS 돌려서 덩어리 세기
	      	    boolean[][] dfsVisited = new boolean[N][N];
	      	    int cnt = 0;

	      	    for (int i = 0; i < N; i++) {
	      	        for (int j = 0; j < N; j++) {
	      	            // 요정이 안 먹었고(visited == false), 
	      	            // 이번 날짜 DFS에서도 아직 안 들른 곳(dfsVisited == false)
	      	            if (!visited[i][j] && !dfsVisited[i][j]) {
	      	                dfs(i, j, dfsVisited); // 연결된 남은 치즈들 싹 방문
	      	                cnt++;
	      	            }
	      	        }
	      	    }
	      	    
	      	    max = Math.max(max, cnt);
	      	}

	      	  
	      	  
	        }
	}
	static void dfs(int x, int y, boolean[][] dfsVisited) {
        dfsVisited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            // 좌표 누적이 아닌 독립된 새 좌표 계산 (nx += dx[d] 사용 금지)
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 경계선 체크
            if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                continue;

            // 요정이 먹은 칸이 아니고, 아직 이번 DFS에서 안 들른 칸만 이동
            if (!visited[nx][ny] && !dfsVisited[nx][ny]) {
                dfs(nx, ny, dfsVisited);
            }
        }
    }
}
/*
 * NxN 1~100
 * 순회 중 가장 치즈 덩이리가 많을떄 덩어리 개수 구하기
 * 
 * 1. k는 1~100까지 배열전체 방문처리 하면서 시작
 * 2. 
 * 3. max에 넣고 초기하하고 다시 탐색
 * 
 * */
