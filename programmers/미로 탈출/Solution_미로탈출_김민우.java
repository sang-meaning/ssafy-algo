import java.util.*;

class Solution {
    
    static int len, s_len, cnt;
    static boolean visited[][];
    static int[][] delta = {{0,-1},{-1,0},{0,1},{1,0}};
    static int S, E, L;
    
    public int solution(String[] maps) {
        int answer = 0;
        int cnt1 = -1;
        int cnt2 = -1;
        len = maps.length;
        s_len = maps[0].length();
        visited = new boolean[len][s_len];
        
        for(int i = 0; i < len; i++){
            String s = maps[i];
            for(int j = 0; j < s_len; j++){
                if(s.charAt(j) == 'S')
                    S = i*s_len + j;
                if(s.charAt(j) == 'E')
                    E = i*s_len + j;
                if(s.charAt(j) == 'L')
                    L = i*s_len + j;          
            }
        }
        
        // 1차 목표 : 시작지점 => 레버
        Queue<int[]> first = new LinkedList<>();
        first.offer(new int[]{S, 0});
        visited[S/s_len][S%s_len] = true;
        cnt1 = bfs(maps, first, L);
        
        //초기화
        visited = new boolean[len][s_len];
        
        // 2차 목표 : 레버 => 도착지점
        Queue<int[]> second = new LinkedList<>();
        second.offer(new int[]{L, 0});
        visited[L/s_len][L%s_len] = true;
        cnt2 = bfs(maps, second, E);
        
        if(cnt1 == -1 || cnt2 == -1)
            return -1;

        answer = cnt1 + cnt2;
        return answer;
    }
    
    public int bfs(String[] maps, Queue<int[]> queList, int target){
        int result = -1;
        
        while(!queList.isEmpty()){
            int[] cur = queList.poll();
            if(cur[0] == target){
                result = cur[1];
                break;
            }
            
            int r = cur[0] / s_len;
            int c = cur[0] % s_len;
            int t = cur[1];
            for(int[] d : delta){
                int nr = r + d[0];
                int nc = c + d[1];
                
                if(!isIn(nr, nc) || visited[nr][nc] || maps[nr].charAt(nc) == 'X')
                    continue;
                
                visited[nr][nc] = true;
                queList.offer(new int[]{nr*s_len + nc, t+1});
            }
        }
        
        return result;
    }
    
    
    public boolean isIn(int r, int c){
        return r >= 0 && r < len && c >= 0 && c < s_len;
    }
}