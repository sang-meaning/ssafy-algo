package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3282_김정원 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[][] dp = new int[N + 1][K + 1];
			int[] V = new int[N];
			int[] C = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				V[i] = Integer.parseInt(st.nextToken());
				C[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= N; i++) {
				for (int v = 0; v <= K; v++) {
					// i번 물건의 부피보다 타겟 부피보다 작다면
					if (V[i-1] > v) {
						dp[i][v] = dp[i-1][v]; 
					} else {						
						dp[i][v] = Math.max(dp[i-1][v-V[i-1]] + C[i-1], dp[i-1][v]) ;
					}
				}
			}
			// 열 제목: 부피
//			System.out.printf("%6s", "i\\v");
//			for (int v = 0; v < dp[0].length; v++) {
//			    System.out.printf("%6d", v);
//			}
//			System.out.println();
//
//			// 행 제목: 물건 개수
//			for (int i = 0; i < dp.length; i++) {
//			    System.out.printf("%6d", i);
//
//			    for (int v = 0; v < dp[i].length; v++) {
//			        System.out.printf("%6d", dp[i][v]);
//			    }
//
//			    System.out.println();
//			}
			System.out.println(String.format("#%d %d", t, dp[N][K]));
		}
	}
}
