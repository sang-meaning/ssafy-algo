import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution {
	static int minInject;
	static int D;
	static int W;
	static int K;
	static int[][] film;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); // 보호 필름 두께
			W = Integer.parseInt(st.nextToken()); // 보호필름 가로 크기
			K = Integer.parseInt(st.nextToken()); // 합격 기준
			minInject = K;
			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0, 0);
			System.out.println("#" + test_case + " " + minInject);
		}
	}

	static void dfs(int row, int injectCount) {
		if (injectCount >= minInject) {
			return;
		}

		if (check()) {
			minInject = Math.min(minInject, injectCount);
			return;
		}

		if (row == D) {
			return;
		}

		dfs(row + 1, injectCount);

		int[] backup = film[row].clone();

		Arrays.fill(film[row], 0);
		dfs(row + 1, injectCount + 1);

		Arrays.fill(film[row], 1);
		dfs(row + 1, injectCount + 1);

		film[row] = backup;
	}

	static boolean check() {

		if (K == 1) {
			return true;
		}

		for (int col = 0; col < W; col++) {

			int sameCount = 1;
			boolean isSuccess = false;

			for (int row = 1; row < D; row++) {

				if (film[row][col] == film[row - 1][col]) {
					sameCount++;
				} else {
					sameCount = 1;
				}

				if (sameCount >= K) {
					isSuccess = true;
					break;
				}
			}

			if (!isSuccess) {
				return false;
			}
		}

		return true;
	}
}