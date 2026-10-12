
import java.io.*;
import java.util.*;

public class Solution {
	static int L, N;
	static int[][] ingr;

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			ingr = new int[N][2];
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				ingr[i][0] = Integer.parseInt(st.nextToken());
				ingr[i][1] = Integer.parseInt(st.nextToken());
			}
			int max_score = combination(0, 0, 0);
			System.out.println("#"+testcase + " " + max_score);
		}
	}
	
	
	// 조합 후 합 반환
	public static int combination(int total, int score, int idx) {
		if(total > L) {
			return 0;
		}
		if(idx == N) {
			return score;
		}

		int a = combination(total + ingr[idx][1], score + ingr[idx][0], idx+1);
		int b = combination(total, score, idx+1);
		
		
		return Math.max(a, b);
	}
}