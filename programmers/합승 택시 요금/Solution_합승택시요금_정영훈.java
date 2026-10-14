import java.util.*;

class Solution {
    static List<Node>[] graph;
    static final int INF = 1000000000;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        graph=new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0; i<fares.length; i++){
            int from=fares[i][0];
            int to=fares[i][1];
            int fare=fares[i][2];
            graph[from].add(new Node(to, fare));
            graph[to].add(new Node(from, fare));
        }
        int[] distS = dijkstra(n,s);
        int[] distA = dijkstra(n,a);
        int[] distB = dijkstra(n,b);
        for (int i = 1; i <= n; i++) {
            if (distS[i] == INF ||
                distA[i] == INF ||
                distB[i] == INF) {
                continue;
            }

            int total = distS[i] + distA[i] + distB[i];
            answer = Math.min(answer, total);
        }
        
        return answer;
    }
    
    public int[] dijkstra(int n, int start){
        PriorityQueue<Node> pq=new PriorityQueue<>();
        int[] dist=new int[n+1];
        Arrays.fill(dist, INF);
        dist[start]=0;
        pq.offer(new Node(start,0));
        while(!pq.isEmpty()){
            Node cur=pq.poll();
            
            if (cur.cost > dist[cur.to]) 
                continue;
            
            
            for(Node next: graph[cur.to]){
                int newFare=cur.cost+next.cost;
                
                if(newFare<dist[next.to]){
                    dist[next.to]=newFare;
                    pq.offer(new Node(next.to, newFare));
                }
                
                
                
            }
        }
        return dist;
        
    }
    static class Node implements Comparable<Node> {
        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    
}