class Solution {
    static boolean[] num = new boolean[1000001];

    public int solution(int n) {
        num[0] = true;
        num[1] = true;
        num[2] = false;

        int i = 2;
        int count = 0;
        while (i <= n) {
            deleteNonPrime(i, n);
            i = findNextPrime(i, n);
            count++;
        }

        return count;
    }

    public void deleteNonPrime(int prime, int max) {
        int i = 2;
        while ((prime * i) <= max) {
            num[prime * i] = true;
            i++;
        }
    }

    public int findNextPrime(int prime, int max) {
        int i = prime + 1;
        while (i <= max) {
            if (num[i] == false)
                return i;
            i++;
        }
        return Integer.MAX_VALUE;
    }
}
