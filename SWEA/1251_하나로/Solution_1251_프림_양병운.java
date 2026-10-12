import java.util.*;
import java.io.*;

class Solution {
    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int n1, n2;
        long weight;

        Edge(int n1, int n2, long weight) {
            this.n1 = n1;
            this.n2 = n2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Long.compare(this.weight, e.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine().trim());
            StringTokenizer stx = new StringTokenizer(br.readLine());
            StringTokenizer sty = new StringTokenizer(br.readLine());
            Node[] nodes = new Node[N];
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(stx.nextToken());
                int y = Integer.parseInt(sty.nextToken());
                nodes[i] = new Node(x, y);
            }
            double E = Double.parseDouble(br.readLine().trim());
            boolean[] visited = new boolean[N];
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.add(new Edge(-1, 0, 0));
            long result = 0;
            int count = 0;
            while (!pq.isEmpty() && count < N) {
                Edge e = pq.poll();
                int current = e.n2;
                if (visited[current]) continue;
                visited[current] = true;
                count++;
                result += e.weight;
                if (count == N) break;
                for (int next = 0; next < N; next++) {
                    if (!visited[next]) pq.add(new Edge(current,next,dist(nodes[current], nodes[next])));
                }
            }
            sb.append('#').append(testCase).append(' ').append(Math.round(result * E)).append('\n');
        }
        System.out.print(sb);
    }

    static long dist(Node a, Node b) {
        long dx = (long) a.x - b.x;
        long dy = (long) a.y - b.y;
        return dx * dx + dy * dy;
    }
}