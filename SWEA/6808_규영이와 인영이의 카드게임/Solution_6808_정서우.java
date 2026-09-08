import java.util.*;
import java.io.*;

public class Solution_6808_정서우 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  
  static int[] k_arr = new int[9];
  static int[] i_arr = new int[9];

  static boolean[] isPicked = new boolean[19];
  static boolean[] isUsed = new boolean[9];

  static int win_cnt = 0;
  static int lose_cnt = 0;

  public static void main(String[] args) throws IOException {
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(br.readLine());

      Arrays.fill(isPicked, false);
      for (int i = 0; i < 9; i++) {
        k_arr[i] = Integer.parseInt(st.nextToken());
        isPicked[k_arr[i]] = true;
      }

      for (int i = 1, j = 0; i < 19; i++) {
        if (!isPicked[i]) i_arr[j++] = i;
      }

      win_cnt = 0;
      lose_cnt = 0;

      dfs(0, 0, 0);

      sb.append('#').append(tc).append(' ')
      .append(win_cnt).append(' ').append(lose_cnt).append('\n');

    }

    System.out.println(sb);
  }

  static void dfs(int depth, int k_score, int i_score) {

    if (depth == 9) {
      if (k_score > i_score) win_cnt++;
      else if (k_score < i_score) lose_cnt++;
      return;
    }
    
    int k_card = k_arr[depth];

    for (int i = 0; i < 9; i++) {
      if (!isUsed[i]) {
        isUsed[i] = true;
        int i_card = i_arr[i];
        int sum = k_card + i_card;

        if (k_card > i_card) {
          dfs(depth + 1, k_score + sum, i_score);
        } else {
          dfs(depth + 1, k_score, i_score + sum);
        }

        isUsed[i] = false;
      }
    }
  }
}
