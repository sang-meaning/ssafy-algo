package testA;

import java.util.Scanner;

public class memoDP {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			int[] a = new int[N];
			for(int i=0; i<N; i++) {
				a[i] = sc.nextInt();
			}
			
			// dp[i] = a[i]가 맨 뒤에 오는 수열 중 가장 긴 것의 길이
			int[] dp = new int[N];
			int answer = 0;
			
			for(int i=0; i<N; i++) {
				dp[i] = 1;
				for(int j=0; j<i; j++) {
					if(a[j]<a[i]) {
						dp[i] = Math.max(dp[i], dp[j]+1);
					}
				}
				answer = Math.max(answer, dp[i]);
			}
			System.out.println("#"+tc+" "+answer);
		}
	}
}

