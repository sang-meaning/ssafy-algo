import java.io.*;
import java.util.*;

public class Solution {

    static int[] parent, size;

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return;

        if(size[a] < size[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        parent[b] = a;
        size[a] += size[b];
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            parent = new int[N+1];
            size = new int[N+1];

            for(int i=1; i<=N; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            sb.append("#").append(tc).append(" ");

            for(int i=0; i<M; i++) {

                st = new StringTokenizer(br.readLine());

                int cmd = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(cmd == 0) {
                    union(a, b);
                }
                else {
                    sb.append(find(a) == find(b) ? 1 : 0);
                }
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}