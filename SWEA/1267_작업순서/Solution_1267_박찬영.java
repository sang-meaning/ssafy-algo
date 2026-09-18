import java.io.*;

import java.util.*;


public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken()); // 정점의 개수

            int E = Integer.parseInt(st.nextToken()); // 간선의 개수

            ArrayList<Integer>[] graph = new ArrayList[V + 1];

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            int[] inDegree = new int[V + 1];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < E; i++) {

                int u = Integer.parseInt(st.nextToken()); // 선행 작업

                int v = Integer.parseInt(st.nextToken()); // 후행 작업
         
                graph[u].add(v); 
                inDegree[v]++;   
            }


            Queue<Integer> q = new ArrayDeque<>();

            for (int i = 1; i <= V; i++) {

                if (inDegree[i] == 0) {

                    q.offer(i);

                }

            }



            sb.append("#").append(tc).append(" ");


            while (!q.isEmpty()) {

                int curr = q.poll(); 

                sb.append(curr).append(" "); 


                for (int next : graph[curr]) {

                    inDegree[next]--; 


                    if (inDegree[next] == 0) {

                        q.offer(next); 

                    }

                }

            }

            sb.append("\n");

        }

        System.out.print(sb);

    }

}