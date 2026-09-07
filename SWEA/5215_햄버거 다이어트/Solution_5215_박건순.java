import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int N;
	static int L;
	static int maxTasteScore;
	static int[] tasteArr;
	static int[] calArr;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			maxTasteScore = 0;
			tasteArr = new int[N];
			calArr = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				tasteArr[i] = Integer.parseInt(st.nextToken());
				calArr[i] = Integer.parseInt(st.nextToken());
			}
			createHamburger(0, 0, 0);

			System.out.println("#" + test_case + " " + maxTasteScore);
		}
	}

	static void createHamburger(int totalCal, int start, int sumTasteScore) {

		maxTasteScore = Math.max(maxTasteScore, sumTasteScore);

		for (int i = start; i < N; i++) {

			if (totalCal + calArr[i] <= L) {
				createHamburger(totalCal + calArr[i], i + 1, sumTasteScore + tasteArr[i]);
			}
		}
	}
}