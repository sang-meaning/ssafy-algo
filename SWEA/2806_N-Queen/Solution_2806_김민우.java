import java.util.*;
import java.io.*;


public class Solution_2806_김민우 {
	static int T, N;
	static boolean[] col, mainDiagonal, subDiagonal;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			col = new boolean[N+1];
			mainDiagonal = new boolean[2*N+1];
			subDiagonal = new boolean[2*N+1];
			
			cnt = 0;
			setQueen(1);
			
			System.out.printf("#%d %d\n",test_case, cnt);
		}//test_case
		
		
	}//main
	
	static void setQueen(int row) {
		
		if(row > N) {
			++cnt;
			return;
		}
		
		
		
		for(int c = 1; c <= N; c++) {
			if(!isAvailable(row, c))
				continue;
			col[c] = mainDiagonal[(row-c)+N] = subDiagonal[row+c] = true;
			setQueen(row+1);
			col[c] = mainDiagonal[(row-c)+N] = subDiagonal[row+c] = false;
		}
	}
	
	static boolean isAvailable(int r, int c) {
		return !col[c] && !mainDiagonal[(r-c)+N] && !subDiagonal[r+c];
	}
}
