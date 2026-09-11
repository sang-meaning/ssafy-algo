package practice;

import java.util.Scanner;

public class Solution {
	static int sum;
	static int min;
	static int N;
	static int B;
	static int[] staff;
	static void backtracking(int start) {
		if (sum >= B && sum - B < min) {
			min = sum - B;
		}
		for (int i=start; i<N;i++) {
			sum += staff[i];
			backtracking(i+1);
			sum -= staff[i];
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc=1; tc<= T; tc++) {
			sum = 0;
			min = Integer.MAX_VALUE;
			N = sc.nextInt();
			B = sc.nextInt();
			staff = new int[N];
			for(int i=0; i<N; i++) {
				staff[i] = sc.nextInt();
			}
			backtracking(0);
			System.out.println("#"+tc+" "+min);
		}
	}
}
