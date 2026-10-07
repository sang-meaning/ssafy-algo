import java.util.*;

/*
위상은 두 가지
1. path 에서 확인 : 부모를 방문해야 자식 방문 가능 : 누가 부모인지도 확인 필요
2. order 에서 확인 : 위상 직접 주어짐

0부터 BFS로 탐색하면서 방문한 노드 개수를 구함
0을 꺼내고, 0과 인접한 0을 전제로 갖는 노드의 degree--
degree == 0 이면 큐에 노드 넣음

BFS 끝나고 모든 노드를 방문했으면 true 반환
*/

class Solution {
    static ArrayList<Integer>[] adj;
    static ArrayList<Integer>[] list;
    static int[] degree;
    
    
    public boolean solution(int n, int[][] path, int[][] order) {
        int pl = path.length;
        int ol = order.length;
        
        adj = new ArrayList[n];
        list = new ArrayList[n];
        degree = new int[n];
        
        for (int i=0; i<n; i++) {
            adj[i] = new ArrayList<>();
            list[i] = new ArrayList<>();
        }
        
        // path (누가 부모인지 모름 : BFS로 먼저 도착한 노드를 부모로 간주 : adj 에대해 탐색하며 부모 찾기)
        for (int i=0; i<pl; i++) { 
            int ss = path[i][0];
            int ee = path[i][1];
            
            adj[ss].add(ee); // 양방향
            adj[ee].add(ss);
        }
        
        Deque<Integer> qq = new ArrayDeque<>();
        boolean[] vis = new boolean[n];
        qq.add(0);
        vis[0] = true;
        
        while(!qq.isEmpty()) {
            int cur = qq.poll();
            
            for (int nxt : adj[cur]) {
                if (vis[nxt]) continue;
                vis[nxt] = true;
                list[cur].add(nxt); // cur가 부모
                degree[nxt]++;
                qq.add(nxt);
            }
        }
        
        // order
        for (int i=0; i<ol; i++) {
            int ss = order[i][0];
            int ee = order[i][1];
            
            list[ss].add(ee);
            degree[ee]++;
        }
        
        // 세팅 완료, 0번부터 BFS
        
        Deque<Integer> q = new ArrayDeque<>();
        
        q.add(0);
        
        int cnt = 1; // 방문 노드 개수 (0번 방문했음)
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for (int nxt : list[cur]) {
                degree[nxt]--; // cur과 인접한 노드 진입차수 줄이기
                
                if (degree[nxt] == 0) {
                    q.add(nxt);
                    cnt++;          
                } 
            }
            
        }

        if (cnt == n) return true;
        else return false;
        
        
        
    }
}