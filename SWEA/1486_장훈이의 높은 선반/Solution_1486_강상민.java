import java.util.*;
import java.io.*;

public class Solution_1486_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int N,B;
  static int result;
  static int[] arr;
  static boolean[] vis;

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      B = Integer.parseInt(st.nextToken());

      result = Integer.MAX_VALUE;

      arr = new int[N];
      vis = new boolean[N];

      st = new StringTokenizer(br.readLine());
      for (int i=0; i<N; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }

      // B 이상이 되는 합 중 최소 구하고 -B 하기

      // N <= 20 인데, 부분 집합의 합 dfs로 풀기 가능할까?
      // 최소 합을 전역으로 관리하고 가지치기

      dfs(0,0);

      int r = result - B;

      sb.append("#"+t+" "+r).append("\n");


    }

    System.out.println(sb);

  }

  static void dfs(int sum, int cur) {

    if (sum > result) return;

    if (sum >= B) {
      result = Math.min(result, sum);
    }

    for (int i=cur; i<arr.length; i++) {
      if (vis[i]) continue;

      vis[i] = true;
      dfs(sum + arr[i], i+1);
      vis[i] = false;
    }
  }
}
