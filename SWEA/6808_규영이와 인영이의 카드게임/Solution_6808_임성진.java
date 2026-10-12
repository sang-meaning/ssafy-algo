import java.util.*;
import java.io.*;

public class Solution_6808_임성진 {
	static int[] gyu = new int[9];
	static int[] in = new int[9];
	static int[] perm = new int[9];
	static boolean[] used = new boolean[9];
	static int win, lose;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			boolean[] owned = new boolean[19];

			for (int i = 0; i < 9; i++) {
				gyu[i] = Integer.parseInt(st.nextToken());
				owned[gyu[i]] = true;
			}

			int idx = 0;
			for (int n = 1; n <= 18; n++) {
				if (!owned[n]) in[idx++] = n;
			}

			win = 0;
			lose = 0;
			permute(0);

			sb.append('#').append(tc).append(' ').append(win).append(' ').append(lose).append('\n');
		}
		System.out.print(sb);
	}

	static void permute(int depth) {
		if (depth == 9) {
			int scoreG = 0;
			int scoreI = 0;

			for (int i = 0; i < 9; i++) {
				int sum = gyu[i] + perm[i];
				if (gyu[i] > perm[i]) scoreG += sum;
				else scoreI += sum;
			}

			if (scoreG > scoreI) win++;
			else lose++;
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (used[i]) continue;

			used[i] = true;
			perm[depth] = in[i];
			permute(depth + 1);
			used[i] = false;
		}
	}
}