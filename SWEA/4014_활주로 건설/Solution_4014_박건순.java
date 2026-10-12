import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution_4014 {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			int result = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				 int[] row = new int[N];
				    int[] col = new int[N];

				    for (int j = 0; j < N; j++) {
				        row[j] = map[i][j];
				        col[j] = map[j][i];
				    }

				    if (checkRoad(row, X)) result++;
				    if (checkRoad(col, X)) result++;
			}

			System.out.println("#" + test_case + " " + result);

		}
	}
	
	static boolean checkRoad(int[] road, int X) {
	    int pre = road[0];
	    int count = 1;
	    boolean buildingRamp = false;

	    for (int i = 1; i < road.length; i++) {

	        if (pre == road[i]) {
	            count++;

	            if (buildingRamp && count >= X) {
	                buildingRamp = false;
	                count -= X;
	            }

	        } else if (pre < road[i]) { // 오르막

	            if (road[i] - pre > 1) {
	                return false;
	            }

	            if (count >= X) {
	                count = 1;
	            } else {
	                return false;
	            }

	        } else { // 내리막

	            if (pre - road[i] > 1) {
	                return false;
	            }

	            if (buildingRamp) {
	                return false;
	            }

	            buildingRamp = true;
	            count = 1;
	        }

	        pre = road[i];
	    }

	    return !buildingRamp;
	}
}
