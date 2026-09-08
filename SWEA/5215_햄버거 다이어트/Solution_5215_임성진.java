import java.util.*;
import java.io.*;

public class Solution_5215_임성진 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			int[] score = new int[N];
			int[] cal = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}

			int[] dp = new int[L + 1];

			for (int i = 0; i < N; i++) {
				for (int c = L; c >= cal[i]; c--) { 
					dp[c] = Math.max(dp[c], dp[c - cal[i]] + score[i]);
				}
			}
			sb.append('#').append(tc).append(' ').append(dp[L]).append('\n');
		}
		System.out.print(sb);
	}
}