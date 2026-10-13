package swea;
import java.util.*;
public class Solution_2117_김현호 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] map = new int[N][N];
			for(int i = 0;i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int answer = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int[] count = new int[2*N];
					for(int k = 0; k < N; k++) {
						for(int l = 0; l < N; l++) {
							if(map[k][l] == 1) {
								int distance = Math.abs(i - k) + Math.abs(j - l);
								count[distance]++;
							}
						}
					}
					int[] dp = new int[2 * N];
					
					for(int k = 1; k <= 2 * N - 1; k++) {
						dp[k] = dp[k - 1] + count[k - 1];
						
						int cost = k * k + (k - 1) * (k - 1);
						int income = dp[k] * M;
						
						if(income - cost >= 0) {
							answer = Math.max(answer, dp[k]);
						}
					}
				}
			}
			System.out.println("#" + test_case + " " + answer);
		}
	}
}
