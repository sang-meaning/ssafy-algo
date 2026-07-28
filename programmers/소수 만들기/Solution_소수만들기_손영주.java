class Solution {

    public int solution(int[] nums) {
        int len = nums.length;
        int answer = 0;

        // 범위 소수 배열 만들기.
        makePrimeNumArray();
        // 각 숫자를 더하고, 그게 소수인지 판단한다. 만약 소수라면 카운팅을 올린다.
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (num[nums[i] + nums[j] + nums[k]] == false)
                        answer++;
                }
            }
        }
        return answer;
    }

    static final int MAX_SUM_VALUE = 1000 + 999 + 998;
    static boolean[] num = new boolean[MAX_SUM_VALUE + 1];

    public void makePrimeNumArray() {
        num[0] = true;
        num[1] = true;
        num[2] = false;

        int i = 2;
        while (i <= MAX_SUM_VALUE) {
            deleteNonPrime(i);
            i = findNextPrime(i);
        }
    }

    public void deleteNonPrime(int prime) {
        int i = 2;
        while ((prime * i) <= MAX_SUM_VALUE) {
            num[prime * i] = true;
            i++;
        }
    }

    public int findNextPrime(int prime) {
        int i = prime + 1;
        while (i <= MAX_SUM_VALUE) {
            if (num[i] == false)
                return i;
            i++;
        }
        return Integer.MAX_VALUE;
    }
}
