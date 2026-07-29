import java.util.Scanner;

public class Solution_1861_홍성대 {
	public static int [] dx = {0, 0, 1, -1};
	public static int [] dy = {1, -1, 0, 0};
	public static int N;
	public static int maxd;
	public static int [][] a;
	public static boolean [][] visited;
	public static int startnum;
 	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++){
			N = sc.nextInt();
			a = new int[N][N];
			visited = new boolean [N][N];
			maxd = 0;
			startnum = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					a[i][j] = sc.nextInt();
				}
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dfs(i,j, 1, a[i][j]);
				}
			}
			System.out.println("#" + test_case + " " + startnum + " " +maxd);
		}
		
	}
 	public static void dfs(int n, int k, int count, int start) {
 		//가장 많이 진행한 값을 넣고 시작지점을 저장
 		if(count > maxd) {
			maxd = count;
			startnum = start;
		} else if(count == maxd) {
			if(start < startnum) {
				startnum = start;
			}
		}
		for(int i = 0; i < 4; i++) {
			int nc = n + dx[i];
			int nr = k + dy[i];
			// 배열 범위 내에 있는지 확인
	        if (nc >= 0 && nc < N && nr >= 0 && nr < N) {
	            // 아직 방문하지 않은 위치이고 다음 위치가 내 위치에서 정확히 1만큼 크면 진행
	            if (!visited[nc][nr] && a[nc][nr] == a[n][k] +1) {
	            	visited[nc][nr] = true;
	            	dfs(nc, nr, count+1, start);
	            	visited[nc][nr] = false;
	            }
	        }
		}
 	}
 }
