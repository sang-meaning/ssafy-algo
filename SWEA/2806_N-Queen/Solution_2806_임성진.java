import java.util.*;
import java.io.*;

public class Solution_2806_임성진 {
	static int N, count;
	static int[] col;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			col = new int[N];
			count = 0;

			dfs(0);

			sb.append('#').append(tc).append(' ').append(count).append('\n');
		}
		System.out.print(sb);
	}

	static void dfs(int row) {
		if (row == N) {                               // 마지막 행까지
			count++;
			return;
		}
		for (int c = 0; c < N; c++) {
			if (!safe(row, c)) continue;
			col[row] = c;
			dfs(row + 1);
		}
	}

	static boolean safe(int row, int c) {
		for (int i = 0; i < row; i++) {
			if (col[i] == c) return false;
			if (Math.abs(col[i] - c) == row - i) return false;
		}
		return true;
	}
}