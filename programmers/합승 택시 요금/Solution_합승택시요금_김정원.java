import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    static List<List<Edge>> graph = new ArrayList<>();
    static List<List<State>> shortestPaths = new ArrayList<>();
    static int V;
    
    // 인접 리스트에 저장하는 간선
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

		@Override
		public String toString() {
			return "Edge [to=" + to + ", weight=" + weight + "]";
		}
        
    }
    
    // 우선순위 큐에 저장하는 정점과 시작점부터의 누적 거리
    static class State {
        int vertex;
        long distance;

        State(int vertex, long distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

		@Override
		public String toString() {
			return "State [vertex=" + vertex + ", distance=" + distance + "]";
		}
        
        
    }
    
    static List<State> copyPaths(List<State> original) {
        List<State> copied = new ArrayList<>();
        for (State state : original) {
            copied.add(new State(state.vertex, state.distance));
        }
        return copied;
    }
    
    static void addEdge(int from, int to, int weight) {
        graph.get(from).add(new Edge(to, weight));
        graph.get(to).add(new Edge(from, weight));
    }
    
    static long[] dijkstra(int start) {
        long[] dist = new long[V + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        // 누적 거리가 작은 것부터 꺼냄
        PriorityQueue<State> pq = new PriorityQueue<>(
            Comparator.comparingLong(s -> s.distance)
        );
        pq.offer(new State(start, 0));
        List<State> currentPath = new ArrayList<>();
        while (!pq.isEmpty()) {
            State current = pq.poll();

            int cur = current.vertex;
            long currentDist = current.distance;

            // 더 짧은 경로를 이미 찾았다면 오래된 정보는 건너뜀
            if (currentDist > dist[cur]) {
                continue;
            }

            for (Edge edge : graph.get(cur)) {
                int next = edge.to;
                
                // 시작점 → cur → next의 누적 비용
                long nextDist = currentDist + edge.weight;

                if (nextDist < dist[next]) {
                    dist[next] = nextDist;
                    pq.offer(new State(next, nextDist));
                }
            }
        }
        //System.out.println(Arrays.toString(dist));
        return dist;
    }
    
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        // n 정점의 갯수, s 시작 정점 번호, a의 도착지 정점 번호, b의 도착지 정점 번호
        // fares 간선 비용
        V = n;

        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
            shortestPaths.add(new ArrayList<>());
        }
        
        for (int[] fare : fares) {
        	addEdge(fare[0], fare[1], fare[2]);
        }
        
        long[] distFromS = dijkstra(s);
        long[] distFromA = dijkstra(a);
        long[] distFromB = dijkstra(b);
        long cost = 0;
        for (int d = 0; d <= n; d++) {
        	cost = distFromS[d] + distFromA[d] + distFromB[d];
        	answer = (int) (answer > cost ? cost : answer);
        }
        return answer;
    }
}
