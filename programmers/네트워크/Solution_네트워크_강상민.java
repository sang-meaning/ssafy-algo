import java.util.*;

class Solution {
    
    static boolean[][] vis; // 방문 여부
    
    public int solution(int n, int[][] computers) {
        int answer = 0; // 영역 카운트
        
        Deque<int[]> q = new ArrayDeque<>();
        vis = new boolean[n][n];
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                // computers i,j 가 1이면 i와 j가 연결되었음
                if (vis[i][j] == true) continue;
                if (computers[i][j] != 1) continue;
                
                // 방문한적도 없고, 연결되었음 의미 : BFS 시작
                answer++;
                
                q.add(new int[] {i,j});
                vis[i][j] = true;
                
                while(!q.isEmpty()) {
                    int[] cur = q.poll();
                    int curX = cur[0];
                    int curY = cur[1];
                    
                    for (int ii=0; ii<n; ii++) {
                        // i,j 에서 j와 연결된 것들을 다 훑어야 함
                        if (computers[curY][ii] != 1) continue;
                        if (vis[curY][ii] == true) continue;
                        
                        // j와 연결된것 찾음 j,ii
                        
                        q.add(new int[] {curY,ii});
                        vis[curY][ii] = true;
                    }
                }
            }
        }
        
        return answer;
    }
}