import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int N;
	static int companyX, companyY;
	static int homeX, homeY;
	static int[][] customers;
	static boolean[] visited;
	static int minDistance;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			companyX = Integer.parseInt(st.nextToken());
			companyY = Integer.parseInt(st.nextToken());
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());
			visited = new boolean[N];
			customers = new int[N][2];
			minDistance = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				customers[i][0] = x;
				customers[i][1] = y;
			}
			dfs(companyX, companyY, 0, 0);

			System.out.println("#" + test_case + " " + minDistance);
		}
	}

	static void dfs(int preX, int preY, int distance, int count) {
		int toHome = Math.abs(preX - homeX) + Math.abs(preY - homeY);

		if (distance + toHome >= minDistance) {
			return;
		}

		if (count == N) {
			minDistance = distance + toHome;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;

			int addDist = Math.abs(preX - customers[i][0]) + Math.abs(preY - customers[i][1]);
			visited[i] = true;
			dfs(customers[i][0], customers[i][1], distance + addDist, count + 1);
			visited[i] = false;

		}
	}
}