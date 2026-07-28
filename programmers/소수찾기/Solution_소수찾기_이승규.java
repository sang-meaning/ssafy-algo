class Solution {
	public int solution(int n) {
		int answer = 0;
		boolean[] check = new boolean[n + 1]; // 초기값 false

		for (int i = 2; i * i <= n; i++) { // n+1
			if (check[i] == false) {
				for (int j = i * i; j <= n; j += i) { // i*i부터 i씩
					check[j] = true; // true면 합성수
				}
			}
		}

		for (int i = 2; i <= n; i++) {
			if (!check[i])
				answer++;
		}

		return answer;
	}
}