import java.util.*;
import java.io.*;


public class Solution_7206_강상민 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int[] vis;

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());
    for (int t=1; t<=T; t++) {
      vis = new int[100000]; // vis[i] : i 수로 만들수 있는 turn 수
      Arrays.fill(vis, -1);

      String s = br.readLine();

      // s길이 -1 개의 위치가 나오고, 각 위치를 고르거나 고르지 않거나
      // ex) 99999 는 int vis = 0000 인 mask가 생성되고, 0000 ~ 1111까지 경우의 수가 존재,
      //    각각에 대해 dfs 재귀호출
      // 넘어온 String의 길이 == 1이면 return, 길이 최댓값 갱신

      // 넘어온 String으로 쪼개서 곱한 수 : a, vis[a] 가 있으면 그거 사용해서 바로 리턴

      int result = dfs(s);

      sb.append("#"+t+" "+result).append("\n");


    }

    System.out.println(sb);
    
  }
  
  static int dfs(String now) {
    int len = now.length();
    int cur = Integer.parseInt(now);

    if (len == 1) return 0;

    if (vis[cur] != -1) return vis[cur];

    // now를 len-1개만큼의 위치를 골라서 쪼갬
    // ex) 99999 이면 0000~1111 사이의 mask를 통해 각각 dfs 진행
    int bits = len-1;
    int best = 0; // depth 최댓값
    
    for (int mask = 1; mask < (1<<bits); mask++) {

      int nxt = 1; // 곱해질 수
      int start = 0; // substring 구할 때 시작 index

      // ex) mask = 1010 이면, index 1, 3 의 비트가 켜 있다 -> 99999의 index 1 바로 뒤에서 끊고 3 바로 뒤에서 끊는다
      // 99 99 9로
      // 1이 켜진 비트만큼 for문 반복해서 nxt 갱신하고, 맨 마지막도 따로 nxt에 곱해줌
      for (int k=0; k<bits; k++) {
        if ((mask & (1<<k)) != 0) {
          // 해당 비트 켜짐
          nxt *= Integer.parseInt(now.substring(start, k+1)); 
          start = k+1;
        }
      }

      nxt *= Integer.parseInt(now.substring(start)); // 맨 끝도 곱해줌

      String nxt2 = nxt+"";
      best = Math.max(best, 1+ dfs(nxt2));
    }

    vis[cur] = best;
    return vis[cur];

  }
}
