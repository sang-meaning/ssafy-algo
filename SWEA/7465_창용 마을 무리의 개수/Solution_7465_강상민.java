import java.util.*;
import java.io.*;


public class Solution_7465_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int N,M;
  static int[] parent;

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      parent = new int[N+1];
      for (int i=1; i<=N; i++) parent[i] = i;

      for (int i=0; i<M; i++) {
        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        union(a,b); // 합칠 수 있으면 합침

      }

      HashSet<Integer> set = new HashSet<>();

      for (int i=1; i<=N; i++) {
        set.add(find(i));
      }

      sb.append("#"+t+" "+set.size()).append("\n");
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
