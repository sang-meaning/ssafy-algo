import java.io.*;
import java.util.*;

public class Solution {

    static int N;

    static long[] x;
    static long[] y;

    static boolean[] visited;
    static long[] minEdge;


    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(br.readLine());


        for(int tc=1; tc<=T; tc++) {


            N = Integer.parseInt(br.readLine());


            x = new long[N];
            y = new long[N];


            StringTokenizer st =
                    new StringTokenizer(br.readLine());


            for(int i=0; i<N; i++)
                x[i] = Long.parseLong(st.nextToken());


            st = new StringTokenizer(br.readLine());


            for(int i=0; i<N; i++)
                y[i] = Long.parseLong(st.nextToken());


            double E =
                Double.parseDouble(br.readLine());


            visited = new boolean[N];
            minEdge = new long[N];


            Arrays.fill(
                minEdge,
                Long.MAX_VALUE
            );


            minEdge[0] = 0;


            long sum = 0;


            for(int cnt=0; cnt<N; cnt++) {


                int minVertex = -1;
                long min = Long.MAX_VALUE;


                for(int i=0; i<N; i++) {

                    if(!visited[i]
                       && minEdge[i] < min) {

                        min = minEdge[i];
                        minVertex = i;
                    }
                }


                visited[minVertex] = true;

                sum += min;


                for(int i=0; i<N; i++) {

                    if(visited[i])
                        continue;


                    long dx =
                        x[minVertex] - x[i];

                    long dy =
                        y[minVertex] - y[i];


                    long dist =
                        dx*dx + dy*dy;


                    if(minEdge[i] > dist)
                        minEdge[i] = dist;
                }
            }


            long ans =
                Math.round(sum * E);


            sb.append("#")
              .append(tc)
              .append(" ")
              .append(ans)
              .append('\n');
        }


        System.out.print(sb);
    }
}