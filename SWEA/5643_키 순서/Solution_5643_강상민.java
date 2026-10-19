import java.util.*;
import java.io.*;


public class Solution_5643_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T,N,M;
    static ArrayList<Integer>[] taller, shorter;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            taller = new ArrayList[N+1];
            shorter = new ArrayList[N+1];

            for (int i=1; i<=N; i++) {
                taller[i] = new ArrayList<>();
                shorter[i] = new ArrayList<>();
            }

            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                // a < b
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                taller[a].add(b); // a보다 큰 것
                shorter[b].add(a);
            }

            int ans = 0;
            for (int i=1; i<=N; i++) {
                if (bfs(i,taller) + bfs(i, shorter) == N-1) ans++;
            }
            sb.append("#"+t+" "+ans).append("\n");


        }

        System.out.println(sb);
    }

    static int bfs(int start, List<Integer>[] g) {
        boolean[] vis = new boolean[N+1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        vis[start] = true;
        q.add(start);
        int cnt = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for (int nxt : g[cur]) {
                if (vis[nxt]) continue;

                vis[nxt] = true;
                cnt++;
                q.add(nxt);
            }
        }

        return cnt;
    }
}
