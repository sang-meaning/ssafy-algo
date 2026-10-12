import java.io.*;
import java.util.*;

public class Solution_1251_크루스칼_정서우 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();

  static int[] parent;
  static int[][] board;
  static long[][] edges;

  public static void main(String[] args) throws IOException {
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      int N = Integer.parseInt(br.readLine());

      int com = N*(N-1)/2;
      board = new int[2][N];
      parent = new int[N];
      edges = new long[com][3];

      for (int i = 0; i < 2; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      double E = Double.parseDouble(br.readLine());

      for (int i = 0; i < N; i++) {
        parent[i] = i;
      }

      int iter = 0;
      for (int i = 0; i < N - 1; i++) {
        for (int j = i + 1; j < N; j++) {
          long x = board[0][i];
          long y = board[1][i];

          long nx = board[0][j];
          long ny = board[1][j];

          long v = (nx - x) * (nx - x) + (ny - y) * (ny - y);
          edges[iter] = new long[]{i, j, v};
          iter++;
        }
      }
      Arrays.sort(edges, (a,b) -> Long.compare(a[2],b[2]));

      long sum = 0;
      int count = 0;

      for (long[] l : edges) {
          if (union(l[0], l[1])) {
              sum += l[2];
              count++;
          }

          if (count == N - 1) break;
      }

      sb.append("#").append(tc).append(" ").append(Math.round(sum * E)).append("\n");
    }
    System.out.println(sb);
  }

  static int find(int x) {
    if (parent[x] == x) {
      return x;
    }
    return parent[x] = find(parent[x]);
  }

  static boolean union(long a, long b) {
    int pa = find((int)a);
    int pb = find((int)b);

    if (pa != pb) {
      parent[pa] = pb;
      return true;
    }
    return false;
  }

}
