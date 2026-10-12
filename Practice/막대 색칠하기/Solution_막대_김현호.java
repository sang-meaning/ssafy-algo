package swea;
import java.util.*;

public class Solution_막대_김현호 {
	static long[] memo;
	public static long dp(int N) {
		if(memo[N] != -1) {
			return memo[N];
		}
		memo[N] = 2*dp(N-1) + dp(N-2);
		
		return memo[N];
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		memo = new long[N + 1];
		Arrays.fill(memo, -1);
		memo[0] = 0;
		memo[1] = 2;
		memo[2] = 5;
		System.out.println(dp(N));
	}
}
