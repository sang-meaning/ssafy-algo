import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_2117_임성진 {

	static int N, M;
	static List<int[]> houses;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			houses = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					if (Integer.parseInt(st.nextToken()) == 1) {
						houses.add(new int[] { r, c });
					}
				}
			}

			int ans = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 중심 (r, c) 기준 거리별 집 개수
					int[] cnt = new int[2 * N];
					for (int[] h : houses) {
						cnt[Math.abs(r - h[0]) + Math.abs(c - h[1])]++;
					}

					int sum = 0;
					for (int K = 1; K <= N + 1; K++) {
						sum += cnt[K - 1]; // 거리 K-1 인 집까지 포함
						int cost = K * K + (K - 1) * (K - 1);
						if (sum * M - cost >= 0) {
							ans = Math.max(ans, sum);
						}
					}
				}
			}

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}
}
