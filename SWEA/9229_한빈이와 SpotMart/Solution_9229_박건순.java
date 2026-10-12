import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			
			int maxGram = -1;
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] gramArr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				gramArr[i] = Integer.parseInt(st.nextToken());
			}

			for (int j = 0; j < N; j++) {
				for (int k = j + 1; k < N; k++) {
					int sum = gramArr[j] + gramArr[k];
					if (M >= sum && sum > maxGram) {
						maxGram = sum;
					}
				}
			}

			System.out.println("#" + test_case + " " + maxGram);
		}
	}
}