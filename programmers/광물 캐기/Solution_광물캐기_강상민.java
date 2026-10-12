import java.util.*;

/*
3개 곡괭이 중 하나 선택해서 5개 깸
종료조건은 mineral 가리키는 idx가 끝을 가리키거나 곡괭이 다쓰면

*/


class Solution {
    static int[] p;
    static String[] m;
    static int ans = Integer.MAX_VALUE;
    static int[][] fati = {{1,1,1},{5,1,1},{25,5,1}}; // 행 : 곡괭이, 열 : 광물
    
    public int solution(int[] picks, String[] minerals) {
    
        p = picks.clone();
        m = minerals.clone();
        
        dfs(0,0);
        
        return ans;
        

        
    }
    
    static void dfs(int idx, int sum) {
        // 현재 깨려는 m 위치 : idx
        // 누적 피로도 : sum
        if (idx >= m.length || p[0]+p[1]+p[2] == 0) {
            ans = Math.min(ans, sum);
            return;
        }
        
        for (int i=0; i<3; i++) { // 0,1,2 -> 순서대로 다이아, 철, 돌
            if (p[i] == 0) continue; // 곡괭이 다쓰면 continue
            
            int temp = 0; // 이번 곡괭이로 누적 피로도
            
            for (int aa=idx; aa<idx+5; aa++) {
                if (aa == m.length) break; // 끝까지 다 깨면 탈출
                
                int mi = -1;
                if (m[aa].equals("diamond")) mi = 0;
                if (m[aa].equals("iron")) mi = 1;
                if (m[aa].equals("stone")) mi = 2;
                
                temp += fati[i][mi]; // 행 : i : 곡괭이,    열 : mi : 광물
            }
            
            p[i]--; // 곡괭이 사용
            dfs(idx+5, sum+temp);
            p[i]++;
        }
    }
}