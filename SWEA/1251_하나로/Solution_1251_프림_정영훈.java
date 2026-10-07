import java.util.*;
import java.io.*;
class Solution {
    static List<Edge>[] graph;
    static boolean[] visited;
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            int V = Integer.parseInt(br.readLine());
            graph = new ArrayList[V + 1];
            visited = new boolean[V + 1];
            map=new int[V][V];
            for (int i = 0; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }
            StringTokenizer st;
            for (int col = 0; col < 2; col++) {
                st=new StringTokenizer(br.readLine()," ");
                for (int row = 0; row < V; row++) {
                    map[row][col]=Integer.parseInt(st.nextToken());
                    
                }
                
            }
            double e=Double.parseDouble(br.readLine());
            for (int from = 0; from < V; from++) {
                for (int to = from+1; to < V; to++) {
                    long weight=calDist(map[from], map[to]);
                    graph[from].add(new Edge(to, weight));
                    graph[to]. add(new Edge(from, weight));
                    
                }
                
            }
            long result=prim(0);
            sb.append("#"+test_case+" "+Math.round(result*e)+"\n");

        }
        System.out.println(sb.toString());
    }
    static class Edge implements Comparable<Edge> {
        int to;
        long weight;

        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }



    static long prim(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        long totalCost = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.to]) continue;

            visited[cur.to] = true;
            totalCost += cur.weight;
            count++;

            for (Edge next : graph[cur.to]) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }

        // 모든 정점을 방문하지 못했다면 MST 생성 불가능
        if (count != graph.length - 1) {
            return -1;
        }

        return totalCost;
    }
    public static long calDist(int[] fromPos, int[] toPos){
        long dx = (long) fromPos[0] - toPos[0];
        long dy = (long) fromPos[1] - toPos[1];

        return dx * dx + dy * dy;
    }
    
}