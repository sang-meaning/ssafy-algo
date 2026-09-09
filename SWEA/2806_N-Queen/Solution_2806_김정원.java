package ssafy.swea.kjw;

import java.io.*;
import java.util.*;

public class Solution_2806_김정원 {
	static int N,cnt;
	static boolean[] col, mainDiagonal, subDiagonal;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			cnt = 0;
			N = Integer.parseInt(br.readLine());
			col = new boolean[N+1];
			mainDiagonal = new boolean[2*N+1];
			subDiagonal = new boolean[2*N+1];
			NQueen(1);
			System.out.println(String.format("#%d %d", test_case, cnt));
		}
		
	}
	
	static void NQueen(int row) {
		if (row > N) {
			++cnt;
			return;
		}
		
		for (int c = 1; c <= N; c++) {			
			// 가지치기, 놓을 수 없다면 가지를 잘라낸다
			if (!isAvailable(row, c)) continue; 
			col[c] = mainDiagonal[(row-c)+N] = subDiagonal[row+c] = true;
			NQueen(row + 1);
			col[c] = mainDiagonal[(row-c)+N] = subDiagonal[row+c] = false;
		}
	}
	
	static boolean isAvailable(int r, int c) {
		return !col[c] && !mainDiagonal[(r-c)+N] && !subDiagonal[r+c];
	}
}
