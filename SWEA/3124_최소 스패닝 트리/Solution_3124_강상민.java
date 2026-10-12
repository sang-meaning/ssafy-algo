import java.util.*;
import java.io.*;

public class Solution_3124_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int V,E;
  static int[][] edge;
  static int[] parent;
  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());

      V = Integer.parseInt(st.nextToken());
      E = Integer.parseInt(st.nextToken());

      edge = new int[E][3]; // 시작, 도착, 비용
      parent = new int[V+1];

      for (int i=1; i<=V; i++) parent[i] = i;

      for (int i=0; i<E; i++) {
        st = new StringTokenizer(br.readLine());
        int ss = Integer.parseInt(st.nextToken());
        int ee = Integer.parseInt(st.nextToken());
        int ww = Integer.parseInt(st.nextToken());

        edge[i] = new int[] {ss,ee,ww};
      }

      Arrays.sort(edge, (a,b) -> Integer.compare(a[2],b[2]));

      int cnt = 0; // 선택 간선 수
      long sum = 0; // 가중치 합

      for (int[] cur : edge) {
        if (union(cur[0], cur[1])) {
          cnt++;
          sum += cur[2];
        }

        if (cnt == V-1) break;
      }

      sb.append("#"+t+" "+sum).append("\n");


    }
    System.out.println(sb);
    
  }

  static int find(int x) {
    if (parent[x] == x) return x;

    return parent[x] = find(parent[x]);
  }

  static boolean union(int a, int b) {
    int pa = find(a);
    int pb = find(b);

    if (pa == pb) return false;

    parent[pb] = pa;
    return true;
  }
  
}
