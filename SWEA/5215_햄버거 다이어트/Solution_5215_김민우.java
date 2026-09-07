import java.util.*;
import java.io.*;

public class Solution_5215_김민우 {
	static int T, N, L;
	static int[][] comb;
	static int[] score;
	static int[] cal;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			comb = new int[N+1][L+1];
			score = new int[N+1];
			cal = new int[N+1];
			
			for(int i= 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= L; j++) {
					if(cal[i] > j)
						comb[i][j] = comb[i-1][j];
					else
						comb[i][j] = Math.max(comb[i-1][j], comb[i-1][j-cal[i]] + score[i]);
				}
			}
			
			System.out.printf("#%d %d\n", test_case, comb[N][L]);
		}//test_case 끝
	
	}//main 끝

}//Solution 끝
