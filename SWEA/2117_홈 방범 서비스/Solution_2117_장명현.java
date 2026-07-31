import java.util.*;

// 완전 탐색 (Complete Search)
class Solution {
	
	public static int T, N, M;
	public static int[][] arr;
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			arr = new int[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			int answer = 0;
			for (int x=0; x<N; x++) {
				for (int y=0; y<N; y++) {
					int homeCnt = 0;
					
          // K = 1
					if (arr[x][y] == 1) homeCnt++;
					if (M*homeCnt - 1 >= 0) {
						answer = Math.max(answer, homeCnt);
					}
					
          // K를 키워가며 모서리 추가 탐색
					for (int k=1; k<=N+1; k++) {
						int span = k-1;
						for (int i=x, j=y+span; i<x+span && j>y; i++, j--) if (isIn(i, j) && arr[i][j] == 1) homeCnt++;
						for (int i=x+span, j=y; i>x && j>y-span; i--, j--) if (isIn(i, j) && arr[i][j] == 1) homeCnt++;
						for (int i=x, j=y-span; i>x-span && j<y; i--, j++) if (isIn(i, j) && arr[i][j] == 1) homeCnt++;
						for (int i=x-span, j=y; i<x && j<y+span; i++, j++) if (isIn(i, j) && arr[i][j] == 1) homeCnt++;
						if (M*homeCnt - (k*k + (k-1)*(k-1)) >= 0) {
							answer = Math.max(answer, homeCnt);
						}
					}
				}
			}
			
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	public static boolean isIn(int i, int j) {
		return 0 <= i && i < N && 0 <= j && j < N;
	}
}