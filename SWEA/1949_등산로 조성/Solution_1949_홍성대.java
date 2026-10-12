import java.util.Scanner;

public class Solution_1949_홍성대 {
	public static int[] dx = {0, 0, 1, -1};
	public static int[] dy = {1, -1, 0, 0};
	public static int N;
	public static int K;
	public static int [][] matrix;
	public static int maxd;
	public static boolean iscut;
	public static boolean [][] visited = new boolean[N][N];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
			
		for(int test_case = 1; test_case <= T; test_case++){
			maxd = 0;
			N = sc.nextInt();
			K = sc.nextInt();
			matrix = new int [N][N];
			visited = new boolean[N][N];
			int maxval = 0;
			// 칸을 채우며 가장 큰 값을 찾음
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int p = sc.nextInt();
					matrix[i][j] = p;
					if(p > maxval) {
						maxval = p;
					}
				}
			}
			// 모든 칸을 검사해 가장 큰 값을 가진 칸이면 dfs를 시작함
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(matrix[i][j] == maxval) {
						visited[i][j] = true;
						dfs(i, j, 1);
						visited[i][j] = false;
					}
				}
			}	
			System.out.println("#" + test_case + " " + maxd);
		}
	}
	
	public static void dfs(int n, int k, int count) {
		if(count > maxd) {
			maxd = count;
		}
		for(int i = 0; i < 4; i++) {
			int nc = n + dx[i];
			int nr = k + dy[i];
			
			// 배열 범위 내에 있는지 확인
	        if (nc >= 0 && nc < N && nr >= 0 && nr < N) {
	            
	            // 아직 방문하지 않은 위치일 때만 진행
	            if (!visited[nc][nr]) {

	                // Case A: 지형을 깎지 않고 바로 이동 가능한 경우
	                if (matrix[nc][nr] < matrix[n][k]) {
	                    visited[nc][nr] = true;
	                    dfs(nc, nr, count + 1);
	                    visited[nc][nr] = false;
	                } 
	                // Case B: 아직 산을 깎은 적이 없고 K 만큼 깎아서 이동할 수 있는 경우
	                else if (!iscut && (matrix[nc][nr] - K < matrix[n][k])) {
	                    int temp = matrix[nc][nr]; // 원본 높이 보존
	                    
	                    // 다음 지점을 현재 지점 높이보다 1 낮게 깎음
	                    matrix[nc][nr] = matrix[n][k] - 1; 
	                    iscut = true;
	                    visited[nc][nr] = true;

	                    dfs(nc, nr, count + 1);

	                    // 백트래킹
	                    visited[nc][nr] = false;
	                    iscut = false;
	                    matrix[nc][nr] = temp;
	                }
	            }
	        }
		}
	}
}
	
	 
