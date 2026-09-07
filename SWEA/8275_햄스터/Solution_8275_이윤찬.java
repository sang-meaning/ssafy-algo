package asdasd13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class hamster {

	static int T;
	static int N;
	static int M;
	static int X;

	static int[] cage;
	static int[] answer;
	static int[][] records;

	static int maxSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // N개의 우리가 있음
			M = Integer.parseInt(st.nextToken()); // M 개의 기록을 남김
			X = Integer.parseInt(st.nextToken()); // 0~X 마리

			cage = new int[N];
			answer = new int[N];
			records = new int[M][3];
			maxSum = -1;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				records[m][0] = Integer.parseInt(st.nextToken());
				records[m][1] = Integer.parseInt(st.nextToken());
				records[m][2] = Integer.parseInt(st.nextToken());
			}

			dfs(0);

			System.out.print("#" + t + " ");

			if (maxSum == -1) {
				System.out.println("-1");
			} else {
				for (int i = 0; i < N; i++) {
					System.out.print(answer[i] + " ");
				}
				System.out.println();
			}
		}
	}

	static void dfs(int depth) {
		if (depth == N) {
			if (!check()) {
				return;
			}
			int sum = 0;

			for (int i = 0; i < N; i++) {
				sum += cage[i];
			}
			if (sum > maxSum) {
				maxSum = sum;
				for (int i = 0; i < N; i++) {
					answer[i] = cage[i];
				}
			}
			return;
		}

		for (int i = 0; i <= X; i++) {
			cage[depth] = i;
			dfs(depth + 1);
		}
	}

	static boolean check() {
		for (int m = 0; m < M; m++) {
			int l = records[m][0];
			int r = records[m][1];
			int s = records[m][2];

			int sum = 0;

			for (int i = l - 1; i <= r - 1; i++) {
				sum += cage[i];
			}
			if (sum != s) {
				return false;
			}
		}
		return true;
	}
}
