import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int maxHouse = 0;
			ArrayList<int[]> houses = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int value = Integer.parseInt(st.nextToken());
					if (value == 1) {
						houses.add(new int[] { i, j });
					}
				}
			}
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (int k = 1; k <= 2 * N; k++) {
						int houseCount = 0;
						int cost = k * k + (k - 1) * (k - 1);
						
						if (cost > houses.size() * M) {
							break;
						}

						for (int[] house : houses) {
							int distance = Math.abs(r - house[0]) + Math.abs(c - house[1]);
							if (distance < k) {
								houseCount++;
							}
						}

						if (houseCount * M >= cost) {
							maxHouse = Math.max(maxHouse, houseCount);
						}
					}
				}
			}

			System.out.println("#" + test_case + " " + maxHouse);
		}
	}
}