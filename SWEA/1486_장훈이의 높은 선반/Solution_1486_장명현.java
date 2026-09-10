import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static int N, B, answer;
	public static int[] h;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			h = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				h[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = Integer.MAX_VALUE;
			comb(0, 0);

			sb.append("#").append(test_case).append(' ');
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void comb(int size, int len) {
		if (size == N) {
			if (len >= B)
				answer = Math.min(answer, len-B);
			return;
		}

		comb(size+1, len);
		comb(size+1, len+h[size]);
	}
}