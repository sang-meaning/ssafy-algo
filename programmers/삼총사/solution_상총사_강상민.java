class Solution {
    static int count;
    static boolean[] vis;
    
    public int solution(int[] number) {
        
        vis = new boolean[number.length];
        dfs(0,0, 0, number);
        
        return count;
    }
    
    static void dfs(int depth, int sum, int cur, int[] number) {
        if (depth == 3) {
            if (sum == 0) {
                count++;
            }
            
            return;
        }
        
        for (int i=cur; i<number.length; i++) {
            if (vis[i]) continue;
            
            vis[i] = true;
            dfs(depth+1, sum+number[i], i+1, number);
            
            vis[i] = false;
        }
        
    }
}