import java.util.*;

/*
O(E log V) V : 200, E : 20000 -> 30만
n번 dijk : 6000만

시작 노드에서 dijk, n-1개의 노드까지 같이 가고, 그 위치에서 각각 집으로 가는 경우 : n번 dijk
시작부터 따로 이동하는 경우 : 위에서 커버될듯

*/

class Solution {
    static ArrayList<int[]>[] adj;
    static int[] d1;
    static int[] d2;
    static int INF = Integer.MAX_VALUE/3;
    static int N;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        int len = fares.length; // 단방향 간선 수
        N = n;
        
        adj = new ArrayList[n+1]; // 노드는 1번부터
        d1 = new int[n+1];
        d2 = new int[n+1];
        
        for (int i=1; i<=n; i++) {
            adj[i] = new ArrayList<>();
            d1[i] = INF;
            d2[i] = INF;
        }
        
        for (int i=0; i<len; i++) {
            int ss = fares[i][0];
            int ee = fares[i][1];
            int ww = fares[i][2];
            
            adj[ss].add(new int[] {ww, ee});
            adj[ee].add(new int[] {ww, ss});
        }
        
        dijkstra(s, d1);
        
        for (int i=1; i<=n; i++) {
            // s-> i 까지 간 다음, 그 위치에서 각자 가는 경우
            int temp = d1[i];
            
            dijkstra(i, d2);
            temp += d2[a] + d2[b];
            
            answer = Math.min(answer, temp);
            
        }
 
  

        
        
        return answer;
    }
    
    static void dijkstra(int startNode, int[] d) {
        // 비용, 노드
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0],b[0]));
        for (int i=1; i<=N; i++) d[i] = INF;
        
        d[startNode] = 0;
        pq.add(new int[] {0, startNode});
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curW = cur[0];
            int curN = cur[1];
            
            if (curW != d[curN]) continue;
            
            for (int[] nxt : adj[curN]) {
                int nxtW = nxt[0];
                int nxtN = nxt[1];
                
                if (d[nxtN] <= d[curN] + nxtW) continue;
                d[nxtN] = d[curN] + nxtW;
                
                pq.add(new int[] {d[nxtN], nxtN});
            }
            
        }
        
    }
}