import java.util.*;
import java.io.*;

public class Solution_3289_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int n,m;
  static int[] parent;

  // 1 ~ n 이 각각 집합
  // 

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());

      parent = new int[n+1];
      for (int i=1; i<=n; i++) parent[i] = i;

      sb.append("#"+t+" ");

      for (int i=0; i<m; i++) {
        st = new StringTokenizer(br.readLine());

        int com = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (com == 0) {
          union(a,b); // a,b 합집합
        } else {
          sb.append(find(a) == find(b) ? 1: 0); // 같으면 같은 집합
        }

      }

      sb.append("\n");


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

    if (pa == pb) return false; // 사이클 발생 false로

    parent[pb] = pa; 
    return true;
  } 
  
}
