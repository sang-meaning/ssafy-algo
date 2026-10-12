class Solution {
    public static int maxi = 0;
    public int solution(int k, int[][] dungeons) {
        
        int n = dungeons.length;
        boolean[] visited = new boolean[n];
        explore(k, 0, visited, dungeons);
        
        return maxi;
    }
    
    public void explore(int now, int cnt, boolean[] visited, int[][] dungeons) {        
        for (int i=0; i<dungeons.length; i++) {
            if (visited[i] == true) continue;
            if (now < dungeons[i][0]) continue;
            
            visited[i] = true; 
            maxi = Math.max(maxi, cnt+1);
            
            explore(now-dungeons[i][1], cnt+1, visited, dungeons);
            visited[i] = false;
        }
    }
}

// class Solution {
//     public int solution(int k, int[][] dungeons) {
//         return explore(k, new boolean[dungeons.length], dungeons);
//     }
    
//     public int explore(int now, boolean[] visited, int[][] dungeons) {
//         int maxi = 0;
        
//         for (int i=0; i<dungeons.length; i++) {
//             if (visited[i] == true) continue;
//             if (now < dungeons[i][0]) continue;
            
//             visited[i] = true;
//             int cnt = explore(now-dungeons[i][1], visited, dungeons) + 1;
//             visited[i] = false;
            
//             maxi = Math.max(maxi, cnt);
//         }
        
//         return maxi;
//     }
// }