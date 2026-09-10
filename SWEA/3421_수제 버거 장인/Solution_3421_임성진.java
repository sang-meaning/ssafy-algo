import java.util.*;
import java.io.*;

public class Solution_3421_임성진 {
	static int N, M, answer;
	static int[] ban;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			ban = new int[N];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				ban[a] |= (1 << b);
				ban[b] |= (1 << a);
			}

			answer = 0;
			dfs(0, 0);

			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}

	static void dfs(int idx, int mask) {
		answer++;

		for (int i = idx; i < N; i++) {
			if ((ban[i] & mask) != 0) continue;
			dfs(i + 1, mask | (1 << i));
		}
	}
}