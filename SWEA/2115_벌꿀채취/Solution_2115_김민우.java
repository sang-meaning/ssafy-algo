import java.io.*;
import java.util.*;

public class Solution_2115_김민우 {
	static int T, N, M, C;
	static int[][] honey;
	static int[][] honeySum;
	static boolean[][] visited;
	static int totalH;
	static int adjN;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			honey = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			adjN = N-M+1;
			honeySum = new int[N][adjN];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j <= N-M; j++) {
					honeySum[i][j] = checkC(i, j);
				}
			}
			
//			System.out.printf("#%d\n", test_case);
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j <= N-M; j++) {
//					System.out.printf("%d ", honeySum[i][j]);
//				}
//				System.out.println();
//			}
			
			totalH = 0;
			getHoney(0, -M, 0, 0);
			sb.append("#"+test_case+" "+totalH+"\n");
		}
		System.out.print(sb);
	}

	public static int checkC(int r, int c) {

		int max = 0;
		
		for(int flag = 0; flag < (1<<M); flag++) {
			int limit = 0;
			int score = 0;
			for(int j = 0; j < M; j++) {
				if((flag&(1<<j)) != 0) {
					int curH = honey[r][c+j];
					limit += curH;
					score += (curH*curH);
				}
			}
			if(limit <= C)
				max = (score > max)?score:max;
		}
		
		return max;
	}
	
	public static void getHoney(int curR, int curC, int cnt, int sum) {
		if(cnt == 2) {
			totalH = (sum > totalH)?sum:totalH;
			return;
		}
		
		for(int i = curR; i < N; i++) {
			if(i == curR) {
				for(int j = curC+M; j <adjN; j++) {
					getHoney(i, j, cnt+1, sum + honeySum[i][j]);
				}
			}
			else {
				for(int j = 0; j <adjN; j++) {
					getHoney(i, j, cnt+1, sum + honeySum[i][j]);
				}
			}
		}
	}
}
