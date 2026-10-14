import java.io.*;
import java.util.*;

public class Solution_2383_임성진 {

	static int N, P, ans;
	static List<int[]> people;
	static List<int[]> stairs;
	static int[] sel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());

			people = new ArrayList<>();
			stairs = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					int v = Integer.parseInt(st.nextToken());
					if (v == 1) {
						people.add(new int[] { r, c });
					} else if (v >= 2) {
						stairs.add(new int[] { r, c, v });
					}
				}
			}

			P = people.size();
			sel = new int[P];
			ans = Integer.MAX_VALUE;

			dfs(0);

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}

	static void dfs(int idx) {
		if (idx == P) {
			ans = Math.min(ans, Math.max(calc(0), calc(1)));
			return;
		}

		sel[idx] = 0;
		dfs(idx + 1);

		sel[idx] = 1;
		dfs(idx + 1);
	}

	static int calc(int s) {
		int[] stair = stairs.get(s);
		int len = stair[2];

		List<Integer> arrive = new ArrayList<>();
		for (int i = 0; i < P; i++) {
			if (sel[i] == s) {
				int[] p = people.get(i);
				arrive.add(Math.abs(p[0] - stair[0]) + Math.abs(p[1] - stair[1]));
			}
		}

		if (arrive.isEmpty()) return 0;

		Collections.sort(arrive);

		int size = arrive.size();
		int[] end = new int[size];
		for (int i = 0; i < size; i++) {
			int start = arrive.get(i) + 1;
			if (i >= 3) {
				start = Math.max(start, end[i - 3]);
			}
			end[i] = start + len;
		}

		return end[size - 1];
	}
}
