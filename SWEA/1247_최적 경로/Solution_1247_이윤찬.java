package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1247_이윤찬 {

	static int T;
	static int homeX, homeY, comX, comY;

	static int computationRoute(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);

	}

	static int x, y, N;

	static List<int[]> personList;

	static int optimalDis;

	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		StringTokenizer st;

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());

			comX = Integer.parseInt(st.nextToken());

			comY = Integer.parseInt(st.nextToken());

			homeX = Integer.parseInt(st.nextToken());

			homeY = Integer.parseInt(st.nextToken());

			optimalDis = Integer.MAX_VALUE;

			visited = new boolean[N];

			personList = new ArrayList<>();

			for (int n = 0; n < N; n++) {
				int Px = Integer.parseInt(st.nextToken());
				int Py = Integer.parseInt(st.nextToken());

				personList.add(new int[] { Px, Py });
			}

			dfs(comX, comY, 0, 0); // 위치 x, y , 깊이 , sum..

			System.out.println("#" + t + " " + optimalDis);

		}
	}

	static void dfs(int x, int y, int depth, int sum) {
		if (sum > optimalDis) {
			return;
		}
		if (depth == N) {
			optimalDis = Math.min(optimalDis, sum + computationRoute(x, y, homeX, homeY));
			return;
		}

		for (int c = 0; c < N; c++) {
			int[] customer = personList.get(c);

			if (visited[c])
				continue;

			int px = customer[0];
			int py = customer[1];

			visited[c] = true;
			dfs(px, py, depth + 1, sum + computationRoute(x, y, px, py));
			visited[c] = false;

		}

	}

}
