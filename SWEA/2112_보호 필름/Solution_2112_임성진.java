import java.util.*;
import java.io.*;

public class Solution_2112_임성진 {
	static int D, W, K, answer;
	static int[][] film;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			answer = D;
			dfs(0, 0);

			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}

	static void dfs(int row, int used) {
		if (used >= answer) return;                   
		if (check()) {     
			answer = used;
			return;
		}
		if (row == D) return;

		dfs(row + 1, used);

		int[] backup = film[row].clone();
		Arrays.fill(film[row], 0);
		dfs(row + 1, used + 1);
		Arrays.fill(film[row], 1);
		dfs(row + 1, used + 1);
		film[row] = backup;
	}

	static boolean check() {
		for (int j = 0; j < W; j++) {
			int run = 1;
			boolean ok = run >= K;

			for (int i = 1; i < D && !ok; i++) {
				if (film[i][j] == film[i - 1][j]) run++;
				else run = 1;
				if (run >= K) ok = true;
			}
			if (!ok) return false;
		}
		return true;
	}
}