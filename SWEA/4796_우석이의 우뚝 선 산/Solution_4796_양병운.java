import java.util.*;
import java.io.*;
class Solution {
    static int N;
	static long cnt;
    static long[] H;
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            H = new long[N];
            for (int i = 0; i < N; i++) H[i] = sc.nextLong();
            cnt = 0;
            for(int i=1; i<N-1; i++) {
                if (H[i] > H[i-1] && H[i] > H[i+1]) dfs(i);
            }
            System.out.println("#"+test_case+" "+cnt);
		}
	}
    public static void dfs(int idx) {
        int left = 0, right = 0;
        for (int i = idx; i >= 1; i--) {
            if (H[i] > H[i - 1]) left++;
            else break;
        }
        for (int i = idx; i < N - 1; i++) {
            if (H[i] > H[i + 1]) right++;
            else break;
        }
        cnt += (long) left * right;
    }
}
