import java.util.*;
import java.io.*;

public class Solution_1486_임성진 {
	static int N, B, min;
	static int[] h;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			h = new int[N];
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < N; i++) {
				h[i] = Integer.parseInt(st.nextToken());
			}

			min = Integer.MAX_VALUE;
			dfs(0, 0);

			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		System.out.print(sb);
	}

	static void dfs(int idx, int sum) {
		if (sum >= B) {
			min = Math.min(min, sum - B);
			return;
		}
		if (idx == N) return;

		dfs(idx + 1, sum + h[idx]);
		dfs(idx + 1, sum);
	}
}