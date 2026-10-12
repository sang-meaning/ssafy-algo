import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 위상정렬
class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] inBound = new int[N+1];
            ArrayList<Integer>[] g = new ArrayList[N+1];
            for (int i=1; i<=N; i++) g[i] = new ArrayList<>();

            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                inBound[y]++;
                g[x].add(y);
            }

            Queue<Integer> q = new ArrayDeque<>();
            for (int i=1; i<=N; i++) {
                if (inBound[i] == 0) {
                    q.add(i);
                }
            }

            sb.append('#').append(test_case).append(' ');

            while (!q.isEmpty()) {
                int f = q.poll();
                sb.append(f).append(' ');

                for (int u:g[f]) {
                    inBound[u]--;
                    if (inBound[u] == 0) {
                        q.add(u);
                    }
                }
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}