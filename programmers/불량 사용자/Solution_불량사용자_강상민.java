import java.util.*;

class Solution {
    
    static HashSet<Integer> set = new HashSet<>();
    static int result = 0;
    static int ul;
    static int bl;
    
    public int solution(String[] user_id, String[] banned_id) {
        
        ul = user_id.length;
        bl = banned_id.length;
        
        
        dfs(0,0,user_id,banned_id);
        
        // set의 크기 출력
        
        return set.size();
        
    }
    
    // depth : banned_id 의 index
    // vis : user_id 선택 여부 비트마스크
    static void dfs(int depth, int vis, String[] user_id, String[] banned_id) {
        if (depth == bl) {
            // 불량 사용자 다 훑어보면
            set.add(vis);
            return;
        }
        
        for (int i=0; i<ul; i++) {
            // 유저 이름 순회
            
            // 아직 선택하지 않은 user_id 이고, 매칭 되면
            if ((vis & (1<<i)) == 0 && match(banned_id[depth], user_id[i])) {
                // 선택해서 dfs 깊이 증가
                dfs(depth+1, (vis | (1<<i)) , user_id, banned_id);
                
                // 갱신한 mask(vis) 자체를 넘겼기 때문에 
                // 방문 관련 백트래킹 필요 없음
                
            }

            
        }
        
    }
    
    // 일치 여부
    static boolean match(String a, String b) {
        // a 가 banned_id
        
        if (a.length() != b.length()) return false;
        
        boolean ok = true;
        
        for (int i=0; i<a.length(); i++) {
            if (a.charAt(i) == '*') continue;
            
            if (a.charAt(i) != b.charAt(i)) ok = false;
        }
        
        return ok;
    }
    
    
    
    
}
    
    
