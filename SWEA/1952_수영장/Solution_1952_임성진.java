import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1952_임성진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int month = Integer.parseInt(st.nextToken());
			int month3 = Integer.parseInt(st.nextToken());
			int year = Integer.parseInt(st.nextToken());

			int[] plan = new int[13];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}

			int[] dp = new int[13];
			for (int i = 1; i <= 12; i++) {
				// 1일권 vs 1달권
				dp[i] = dp[i - 1] + Math.min(plan[i] * day, month);

				// 3달권
				dp[i] = Math.min(dp[i], dp[Math.max(i - 3, 0)] + month3);
			}

			// 1년권
			int ans = Math.min(dp[12], year);

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}
}
