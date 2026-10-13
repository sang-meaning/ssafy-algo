package com;

import java.io.*;
import java.util.*;

public class SWEA3282 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] item = new int[N][2];
			
			// 0 부피 1 가치
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				item[i][0] = Integer.parseInt(st.nextToken());
				item[i][1] = Integer.parseInt(st.nextToken());
			}
			
			int[][] dp = new int[N + 1][K + 1];
			
			for(int i = 1; i <= N; i++) {
				int v = item[i-1][0];
				int c = item[i-1][1];
				
				for(int j = 0; j <= K; j++) {
					dp[i][j] = dp[i-1][j];
					
					if(j >= v) {
						dp[i][j] = Math.max(dp[i][j], dp[i-1][j-v] + c);
					}
				}
			}
			sb.append("#").append(tc).append(" ")
			  .append(dp[N][K]).append("\n");
		}
		System.out.println(sb);
	}
}
