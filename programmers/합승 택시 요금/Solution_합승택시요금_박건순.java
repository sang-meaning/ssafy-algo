import java.util.*;

class Solution {
    static final int INF = 1_000_000_000;

    ArrayList<Node>[] graph;

    static class Node {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int cost = fare[2];

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        int[] dist_s = dijkstra(s, n);
        int[] dist_a = dijkstra(a, n);
        int[] dist_b = dijkstra(b, n);

        int answer = INF;

        for (int k = 1; k <= n; k++) {
            if (dist_s[k] == INF
                    || dist_a[k] == INF
                    || dist_b[k] == INF) {
                continue;
            }

            int total = dist_s[k] + dist_a[k] + dist_b[k];
            answer = Math.min(answer, total);
        }

        return answer;
    }

    int[] dijkstra(int start, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>(
            (x, y) -> Integer.compare(x.cost, y.cost)
        );

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.cost > dist[current.to]) {
                continue;
            }

            for (Node next : graph[current.to]) {
                int new_cost = current.cost + next.cost;

                if (new_cost < dist[next.to]) {
                    dist[next.to] = new_cost;
                    pq.offer(new Node(next.to, new_cost));
                }
            }
        }

        return dist;
    }
}