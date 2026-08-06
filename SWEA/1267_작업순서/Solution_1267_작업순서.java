import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 표준 위상정렬 문제
class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int[] inDegree = new int[V+1];
            ArrayList<Integer>[] graph = new ArrayList[V+1];
            for (int i=1; i<=V; i++) graph[i] = new ArrayList<>();

            // 그래프와 진입차수를 저장하고
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<E; i++) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                inDegree[v]++;
            }

            // 진입차수가 0인 것부터
            Queue<Integer> q = new ArrayDeque<>();
            for (int i=1; i<=V; i++) {
                if (inDegree[i] == 0) {
                    q.offer(i);
                }
            }

            System.out.print("#" + test_case + " ");

            // 차례대로 순환하면서
            while (!q.isEmpty()) {
                int f = q.poll();
                System.out.print(f + " ");

                // 진입차수가 0이 되는 것은 추가
                for (int u : graph[f]) {
                    inDegree[u]--;
                    if (inDegree[u] == 0) {
                        q.offer(u);
                    }
                }
            }
            System.out.println();
        }
    }
}