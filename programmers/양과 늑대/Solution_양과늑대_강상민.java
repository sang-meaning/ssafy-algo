import java.util.*;

class Solution {
    static int maxSheep = 0;
    
    public int solution(int[] info, int[][] edges) {
        dfs(1,0,1,info, edges);
        
        return maxSheep;
    }
    
    
    
    static void dfs(int sheep, int wolf, int mask, int[] info, int[][] edges) {
        // 매번 maxSheep 갱신    
        maxSheep = Math.max(maxSheep, sheep);
        
        // 모든 엣지에 대해 검사
        for (int[] node : edges) {
            int parent = node[0];
            int child = node[1];
            
            // 부모는 방문, 자식은 미방문일 때 깊이탐색
            if ( (mask & (1<<parent)) != 0 && (mask & (1<<child)) == 0 ) {
                
                int ns = sheep;
                int nw = wolf;
                
                if (info[child] == 0) {
                    ns++;
                } else {
                    nw++;
                }
                
                if (ns > nw)
                    dfs(ns, nw, (mask | (1<<child)), info, edges);
            }
            
            
        }
    }
}