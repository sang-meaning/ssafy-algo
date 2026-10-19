import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static List<Integer>[] taller;                       // taller[a] = a보다 키가 큰 학생들 (a → b)
    static List<Integer>[] shorter;                      // shorter[b] = b보다 키가 작은 학생들 (간선을 뒤집은 그래프)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);    // 줄 구분 없이 숫자를 차례로 읽는다
        StringBuilder sb = new StringBuilder();

        in.nextToken();
        int T = (int) in.nval;
        for (int tc = 1; tc <= T; tc++) {
            in.nextToken();
            N = (int) in.nval;
            in.nextToken();
            int M = (int) in.nval;

            taller = new ArrayList[N + 1];
            shorter = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                taller[i] = new ArrayList<>();
                shorter[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                in.nextToken();
                int a = (int) in.nval;
                in.nextToken();
                int b = (int) in.nval;
                taller[a].add(b);                        // a < b : a에서 b로 가면 "더 큰 쪽"
                shorter[b].add(a);                       // 뒤집으면 b에서 a로 가면 "더 작은 쪽"
            }

            int answer = 0;
            for (int i = 1; i <= N; i++) {
                int up = count(i, taller);               // 나보다 큰 학생 수
                int down = count(i, shorter);            // 나보다 작은 학생 수
                if (up + down == N - 1) answer++;        // 나머지 모두와 관계가 정해짐 → 순위 확정
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    }

    // start 에서 graph 의 간선을 따라 도달할 수 있는 학생 수 (BFS, 자기 자신은 세지 않음)
    static int count(int start, List<Integer>[] graph) {
        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.offer(start);
        int reached = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph[cur]) {
                if (visited[next]) continue;
                visited[next] = true;                    // 큐에 넣을 때 방문 표시
                reached++;
                q.offer(next);
            }
        }
        return reached;
    }
}
