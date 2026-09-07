//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class Solution {
//	public static void main(String args[]) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		StringTokenizer st;
//		
//		int T = Integer.parseInt(br.readLine());
//		for (int test_case = 1; test_case <= T; test_case++) {
//			st = new StringTokenizer(br.readLine());
//			int N = Integer.parseInt(st.nextToken());
//			int L = Integer.parseInt(st.nextToken());
//			
//			int[][] arr = new int[N][2];
//			for (int i=0; i<N; i++) {
//				st = new StringTokenizer(br.readLine());
//				arr[i][0] = Integer.parseInt(st.nextToken());
//				arr[i][1] = Integer.parseInt(st.nextToken());
//			}
//			
//			int[] dp = new int[L+1];
//			for (int i=0; i<N; i++) {
//				for (int j=L; j>=arr[i][1]; j--) {
//					dp[j] = Math.max(dp[j], dp[j-arr[i][1]] + arr[i][0]);
//				}
//			}
//			
//			
//			sb.append('#').append(test_case).append(' ').append(dp[L]).append('\n');
//		}
//		
//		System.out.println(sb);
//	}
//}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static int N, L, maxi;
	public static int[][] arr;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			arr = new int[N][2];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			maxi = 0;
			comb(-1, 0, 0);
			
			sb.append('#').append(test_case).append(' ').append(maxi).append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static void comb(int last, int score, int cal) {
		for (int i=last+1; i<N; i++) {
			int addScore = score + arr[i][0];
			int addCal = cal + arr[i][1];
			
			if (addCal <= L) {
				maxi = Math.max(maxi, addScore);
				comb(i, addScore, addCal);
			}
		}
	}
}