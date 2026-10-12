import java.util.*;
import java.io.*;

public class Solution_7206_임성진 {
	static Map<Integer, Integer> memo = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			sb.append('#').append(tc).append(' ').append(game(N)).append('\n');
		}
		System.out.print(sb);
	}
	

	static int game(int n) {
		if (n < 10) return 0;
		if (memo.containsKey(n)) return memo.get(n);

		String s = String.valueOf(n);
		int gaps = s.length() - 1;
		int max = 0;

		for (int mask = 1; mask < (1 << gaps); mask++) {
			int piece = s.charAt(0) - '0';
			int product = 1;

			for (int j = 0; j < gaps; j++) {
				if ((mask & (1 << j)) == 0) {
					piece = piece * 10 + (s.charAt(j + 1) - '0');
				} else {
					product *= piece;
					piece = s.charAt(j + 1) - '0';
				}
			}
			product *= piece;

			max = Math.max(max, game(product));
		}

		memo.put(n, max + 1);
		return max + 1;
	}
}