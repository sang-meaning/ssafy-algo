import java.util.*;
import java.io.*;


public class Solution_6808_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int[] arr1;
  static int[] arr2;
  static int[] opponent;
  static boolean[] vis;
  static int win;
  static int lose;

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());

      arr1 = new int[9];
      arr2 = new int[9]; // 상대방
      opponent = new int[9];
      vis = new boolean[9];

      win = 0;
      lose = 0;

      HashSet<Integer> set = new HashSet<>();
      for (int i=1; i<=18; i++)
        set.add(i);

      for (int i=0; i<9; i++) {
        arr1[i] = Integer.parseInt(st.nextToken());
        set.remove(arr1[i]);
      }

      int iter = 0;
      for (int temp : set) {
        arr2[iter] = temp;
        iter++;
      }

      Arrays.sort(arr2);

      // 세팅 완료

      dfs(0);

      sb.append("#"+t+" "+win+" "+lose).append("\n");

      


    }

    // tc 종료
    System.out.println(sb);

    
  }

  // 상대방의 9개 카드의 순열 구하기
  static void dfs(int depth) {
    if (depth == 9) {
      // opponent 완성, 겨뤄서 승 패 가르기

      int p1=0;
      int p2=0; //상대 점수

      for (int i=0; i<9; i++) {
        if (arr1[i] > opponent[i]) {
          p1 += arr1[i] + opponent[i];
        } else {
          p2 += arr1[i] + opponent[i];
        }
      }

      if (p1 > p2) win++;
      else if (p1 < p2) lose++;

      return;
    }

    for (int i=0; i<9; i++) {
      if (vis[i]) continue;

      opponent[depth] = arr2[i];
      vis[i] = true;

      dfs(depth+1);

      vis[i] = false;
    }

  }
  
}
