import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	static int day, month, three, year, minCost;
	static int[] plan;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			plan = new int[12];
			st = new StringTokenizer(br.readLine());

			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			three = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			minCost = year;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				int num = Integer.parseInt(st.nextToken());
				plan[i] = num;
			}
			dfs(0,0);
			System.out.println("#" + test_case + " " + minCost);
		}
	}

	static void dfs(int count, int cost) {
		if (count >= 12) {
			minCost = Math.min(minCost, cost);
			return;
		}

		int dayTicket = plan[count] * day;
		int monthTicket = month;

		dfs(count + 1, cost + dayTicket);
		dfs(count + 1, cost + monthTicket);
		dfs(count + 3, cost + three);
	}
}