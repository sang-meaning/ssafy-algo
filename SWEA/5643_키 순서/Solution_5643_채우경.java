import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int N, M;
    static List<Integer>[] adj;        // 나보다 큰 사람 방향
    static List<Integer>[] reverseAdj; // 나보다 작은 사람 방향

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            M = Integer.parseInt(br.readLine().trim());

            adj = new ArrayList[N + 1];
            reverseAdj = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
                reverseAdj[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adj[a].add(b);        // a < b (a에서 b로)
                reverseAdj[b].add(a); // b > a (b에서 a로)
            }

            int answer = 0;

            // 모든 학생(1 ~ N)에 대해 순위 파악 가능 여부 확인
            for (int i = 1; i <= N; i++) {
                int tallerCount = bfs(i, adj);          // 나보다 큰 학생 수
                int smallerCount = bfs(i, reverseAdj);  // 나보다 작은 학생 수

                if (tallerCount + smallerCount == N - 1) {
                    answer++;
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    // start 노드에서 출발하여 도달할 수 있는 노드의 개수 반환
    static int bfs(int start, List<Integer>[] graph) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        q.add(start);
        visited[start] = true;

        int count = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                    count++;
                }
            }
        }

        return count;
    }
}