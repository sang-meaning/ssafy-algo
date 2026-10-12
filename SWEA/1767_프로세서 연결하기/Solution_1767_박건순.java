import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	static int N;
	static ArrayList<int[]> coreList;
	static boolean[][] wireMap;
	static int maxCore;
	static int minWireLength;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			wireMap = new boolean[N][N];
			maxCore = 0;
			minWireLength = 9999999;
			coreList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int value = Integer.parseInt(st.nextToken());
					if (value == 1) {
						wireMap[i][j] = true;
						if (i != 0 && i != N - 1 && j != 0 && j != N - 1) {
							coreList.add(new int[] { i, j });
						}
					}
				}
			}
			dfs(0, 0, 0);
			System.out.println("#" + test_case + " " + minWireLength);
		}
	}

	static void dfs(int index, int connectedCount, int wireLength) {

		if (index == coreList.size()) {
			if (maxCore < connectedCount) {
				maxCore = connectedCount;
				minWireLength = wireLength;
			} else if (maxCore == connectedCount) {
				minWireLength = Math.min(minWireLength, wireLength);
			}

			return;
		}

		int remain = coreList.size() - index;
		if (connectedCount + remain < maxCore) {
			return;
		}

		if (connectedCount + remain == maxCore && wireLength >= minWireLength) {
			return;
		}

		int x = coreList.get(index)[0];
		int y = coreList.get(index)[1];

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			int length = 0;
			boolean canConnect = true;

			while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (wireMap[nx][ny]) {
					canConnect = false;
					break;
				}
				length++;

				nx += dx[i];
				ny += dy[i];
			}
			if (!canConnect) {
				continue;
			}

			nx = x + dx[i];
			ny = y + dy[i];

			while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				wireMap[nx][ny] = true;
				nx += dx[i];
				ny += dy[i];
			}
			dfs(index + 1, connectedCount + 1, wireLength + length);

			nx = x + dx[i];
			ny = y + dy[i];

			while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				wireMap[nx][ny] = false;
				nx += dx[i];
				ny += dy[i];
			}
		}

		dfs(index + 1, connectedCount, wireLength);
	}
}
