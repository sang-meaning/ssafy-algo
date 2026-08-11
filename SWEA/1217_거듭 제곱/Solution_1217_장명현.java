//import java.util.*;
import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		
//		int T = sc.nextInt();
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			int t = sc.nextInt();
			int N = sc.nextInt();
			int M = sc.nextInt();
			System.out.println("#" + test_case + " " + multiply(N, M));
		}
	}
	
	public static int multiply(int now, int left) {
		if (left == 0) return 1;
		return now * multiply(now, left-1);
	}
	
	public static long fast_mul(long n, long m) {
		if (m == 0) return 1;
		
		long MOD = 1000000007; // 1e9 + 7
		long div = fast_mul(n, m/2) % MOD;
		
		if (m%2 == 1) {
			return (((div * div) % MOD) * n) % MOD;
		}
		else {
			return (div * div) % MOD;
		}
	}
}