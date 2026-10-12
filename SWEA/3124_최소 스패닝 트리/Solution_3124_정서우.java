import java.io.*;
import java.util.*;

public class Solution_3124_정서우 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();

  static int[] parent;
  public static void main(String[] args) throws IOException {
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(br.readLine());
      int V = Integer.parseInt(st.nextToken());
      int E = Integer.parseInt(st.nextToken());

      int[][] edges = new int[E][3];
      parent = new int[V + 1];

      for (int i = 1; i <= V; i++) {
        parent[i] = i;
      }

      for (int i = 0; i < E; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 3; j++) {
          edges[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

      long sum = 0;
      int cnt = 0;
      for (int i = 0; i < E; i++) {
        if (find(edges[i][0]) != find(edges[i][1])) {
          union(edges[i][0], edges[i][1]);
          sum += edges[i][2];
          cnt++;
        }
        if (cnt == V -1) break;
      }

      sb.append("#" +tc+" "+ sum).append("\n");
    }

    System.out.println(sb);
  }

  static int find(int x) {
    if (parent[x] == x) {
      return x;
    }
    return parent[x] = find(parent[x]);
  }

  static void union(int a, int b) {
    a = find(a);
    b = find(b);

    parent[b] = a;
  }
}
