package pratice;

import java.util.Scanner;

public class Solution {
	
	static int N,cnt;
	static boolean[] col, mainDiagonal, subDiagonal;
	
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int T = sc.nextInt(); 
			for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			cnt = 0;
			
			col = new boolean[N+1];
			mainDiagonal = new boolean[2*N+1];
			subDiagonal = new boolean[2*N+1];
			
			setQueen(1);
			System.out.println("#"+tc+" "+cnt);
		}
	}
		
		static void setQueen(int row) {
			
			if(row > N) {
				// 유망할때만 계속 트리를 탐색해서 왔는데 기저조건이면 주모건 답
				++cnt;
				return;
			}
			
			for(int c =1; c <= N; c++) {
				if(!isAvailable(row, c)) continue; // 가치치기
				col[c] = mainDiagonal[(row-c)+N] = subDiagonal[row+c] = true;
				setQueen(row+1);
				col[c] = mainDiagonal[(row-c)+N] = subDiagonal[row+c] = false;
			}
			
		}
		static boolean isAvailable(int r, int c) {
			return !col[c] && !mainDiagonal[(r-c)+N] && !subDiagonal[r+c];
		
	}
}