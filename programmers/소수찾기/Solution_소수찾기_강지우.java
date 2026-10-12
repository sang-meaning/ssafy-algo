class Solution_소수찾기_강지우 {
	public int solution(int n) {
        boolean[] check = new boolean[n+1];

        check[0] = check[1] = true;

        for (int i=2; i*i<=n; i++) {
            if (check[i] == true) {
                continue;
            }
            for (int j=i+i; j<=n; j+=i) {
                check[j] = true;
            }
        }

        int count = 0;
        for (int i=2; i<=n; i++) {
            if(check[i] == false) {
                count++;
            }
        }
        return count;
    }
}