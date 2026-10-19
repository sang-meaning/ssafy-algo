import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5643_한석호 {
    static int N, M;
    static ArrayList<Integer>[] taller; // taller[a] : a보다 큰 학생들
    static ArrayList<Integer>[] shorter; // shorter[b] : b보다 작은 학생들

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());

        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            M = Integer.parseInt(br.readLine().trim());

            taller = new ArrayList[N + 1];
            shorter = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                taller[i] = new ArrayList<>();
                shorter[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                taller[a].add(b);   // a < b
                shorter[b].add(a);  // b > a
            }

            int answer = 0;
            for (int i = 1; i <= N; i++) {
                int countTaller = bfs(i, taller);
                int countShorter = bfs(i, shorter);

                // 자신보다 큰 학생 + 작은 학생 == N - 1 이면 순위를 알 수 있음
                if (countTaller + countShorter == N - 1) {
                    answer++;
                }
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static int bfs(int start, ArrayList<Integer>[] adj) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();

        visited[start] = true;
        q.offer(start);
        int count = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : adj[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    count++;
                    q.offer(next);
                }
            }
        }

        return count;
    }
}