class Solution {
    public int solution(int n) {
        boolean[] check = new boolean[n + 1];
		int answer = 0;
		for (int i = 2; i * i <= n; i++) {
			if (check[i] == true) {
				continue;
			}
			for (int j = i + i; j <= n; j += i) {
				check[j] = true;
			}
		}
		for (int k = 2; k <= n; k++) {
			if (!check[k]) {
				answer++;
			}
		}
		return answer;
    }
}