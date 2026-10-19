import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// dp[i]: arr[i]를 마지막으로 하는 최장 증가 부분 수열의 길이
			int[] dp = new int[N];
			int answer = 0;
			for (int i=0; i<N; i++) {
				// 자기 자신만 선택하면 길이는 1
				dp[i] = 1;
				for (int j=0; j<i; j++) {
					if (arr[j] < arr[i]) {
						dp[i] = Math.max(dp[i], dp[j]+1);
					}
				}
				answer = Math.max(answer, dp[i]);
			}

			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}

		System.out.print(sb);
	}
}
