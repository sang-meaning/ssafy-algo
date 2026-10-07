import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[] parent;
    static int[] size;

    static class Edge {
        int from;
        int to;
        long cost;

        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int find(int x) {

        if(parent[x] == x)
            return x;

        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {

        a = find(a);
        b = find(b);

        if(a == b)
            return false;

        if(size[a] < size[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        parent[b] = a;
        size[a] += size[b];

        return true;
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {

            N = Integer.parseInt(br.readLine());

            long[] x = new long[N];
            long[] y = new long[N];

            StringTokenizer st =
                    new StringTokenizer(br.readLine());

            for(int i=0; i<N; i++)
                x[i] = Long.parseLong(st.nextToken());

            st = new StringTokenizer(br.readLine());

            for(int i=0; i<N; i++)
                y[i] = Long.parseLong(st.nextToken());


            double E = Double.parseDouble(br.readLine());


            ArrayList<Edge> list = new ArrayList<>();


            for(int i=0; i<N; i++) {

                for(int j=i+1; j<N; j++) {

                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];

                    long dist = dx*dx + dy*dy;

                    list.add(
                        new Edge(i, j, dist)
                    );
                }
            }


            list.sort((a, b) ->
                Long.compare(a.cost, b.cost)
            );


            parent = new int[N];
            size = new int[N];

            for(int i=0; i<N; i++) {
                parent[i] = i;
                size[i] = 1;
            }


            long sum = 0;
            int cnt = 0;


            for(Edge e : list) {

                if(union(e.from, e.to)) {

                    sum += e.cost;
                    cnt++;

                    if(cnt == N-1)
                        break;
                }
            }


            long ans = Math.round(sum * E);


            sb.append("#")
              .append(tc)
              .append(" ")
              .append(ans)
              .append('\n');
        }

        System.out.print(sb);
    }
}