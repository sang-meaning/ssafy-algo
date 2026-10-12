import java.util.*;
import java.io.*;

public class Solution_1267_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T = 10;
  static int V,E;
  static ArrayList<Integer>[] list;
  static int[] degree;


  public static void main(String[] args) throws IOException {
    for (int t=1; t<=T; t++) {
       st = new StringTokenizer(br.readLine());
       V = Integer.parseInt(st.nextToken());
       E = Integer.parseInt(st.nextToken());

       list = new ArrayList[V+1];
       degree = new int[V+1];

       for (int i=1; i<=V; i++) {
        list[i] = new ArrayList<>();
       }

       st = new StringTokenizer(br.readLine());

       for (int i=0; i<E; i++) {
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        list[a].add(b);
        degree[b]++;
       }

       topologicalSort(t);
    }

    System.out.print(sb);
   


  }

  static void topologicalSort(int test) {
    Deque<Integer> q = new ArrayDeque<>();
    sb.append("#"+test+" ");

    for (int i=1; i<=V; i++) {
      if (degree[i] == 0) {
        q.add(i);
      }

    }


    while(!q.isEmpty()) {
      int cur = q.poll();

      sb.append(cur+" ");

      for (int nxt: list[cur]) {
        degree[nxt]--;

        if (degree[nxt] == 0)
          q.add(nxt);
      }
    }

    sb.append("\n");
  }
  
}
