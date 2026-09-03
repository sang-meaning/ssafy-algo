import java.util.*;
import java.io.*;

public class Solution_9229_김민우 {
  static int T, N, W;
  static int[] snacks;
  static int max;

  static public void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());
    for(int test_case = 1; test_case <= T; test_case++){
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());

      snacks = new int[N];

      st = new StringTokenizer(br.readLine());
      for(int i = 0; i < N; i++)
        snacks[i] = Integer.parseInt(st.nextToken());

      max = -1;
      dfs(-1, 0, 0);
      System.out.printf("#%d %d\n", test_case, max);
    }//test_case
  }//main

  static public void dfs(int idx, int sum, int cnt){
    if(cnt == 2){
      if(sum <= W)
        max = (sum > max)?sum:max;
      return;
    }

    else if(idx == N-1 && cnt != 2){
      return;
    }

    dfs(idx+1, sum+snacks[idx+1], cnt+1);
    dfs(idx+1, sum, cnt);
  }//dfs

}
