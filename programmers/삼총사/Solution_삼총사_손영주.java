package _submission;

public class Solution{
	
	public static int solution(int[] number) {
		int cnt = 0;
		int n = number.length;

		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				for (int k = j + 1; k < n; k++) {
					int sum = number[i] + number[j] + number[k];
					if (sum == 0) {
						cnt++;
					}
				}
			}
		}
		return cnt;
	}
}
