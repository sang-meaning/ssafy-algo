class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int l = lost.length;
        int r = reserve.length;
        
        int[] clothes = new int[n+1];
        for (int i=1; i<=n; i++)    clothes[i] = 1;
        for (int x : lost)          clothes[x]--;
        for (int x : reserve)       clothes[x]++;
        
        for (int i=1; i<=n; i++) {
            if (clothes[i] == 2) {
                if (i-1 > 0 && clothes[i-1] == 0) {
                    clothes[i-1]++;
                    clothes[i]--;
                    continue;
                }
                
                if (i+1 <= n && clothes[i+1] == 0) {
                    clothes[i+1]++;
                    clothes[i]--;
                    continue;
                }
            }
        }
        
        int answer = 0;
        for (int i=1; i<=n; i++) {
            if (clothes[i] >= 1) {
                answer++;
            }
        }
        
        return answer;
    }
}