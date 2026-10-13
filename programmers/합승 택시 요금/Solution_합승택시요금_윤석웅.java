import java.util.*;

class Solution {
    static ArrayList<int[]>[] graph;

    static int[] dijkstra(int start, int n) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq =
            new PriorityQueue<>((x, y) -> x[1] - y[1]);

        dist[start] = 0;
        pq.offer(new int[]{start, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[1] != dist[cur[0]])
                continue;

            for(int[] next : graph[cur[0]]) {
                int cost = cur[1] + next[1];

                if(cost < dist[next[0]]) {
                    dist[next[0]] = cost;
                    pq.offer(new int[]{next[0], cost});
                }
            }
        }

        return dist;
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList[n+1];

        for(int i=1; i<=n; i++)
            graph[i] = new ArrayList<>();

        for(int[] f : fares) {
            graph[f[0]].add(new int[]{f[1], f[2]});
            graph[f[1]].add(new int[]{f[0], f[2]});
        }

        int[] ds = dijkstra(s, n);
        int[] da = dijkstra(a, n);
        int[] db = dijkstra(b, n);

        int ans = Integer.MAX_VALUE;

        for(int i=1; i<=n; i++)
            ans = Math.min(ans, ds[i] + da[i] + db[i]);

        return ans;
    }
}
