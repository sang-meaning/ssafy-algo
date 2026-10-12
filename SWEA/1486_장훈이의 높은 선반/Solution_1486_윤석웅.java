
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Solution1486 {
  static int N, T, lim, ans;
  static Integer[] height;
  static void dfs(int idx, int target){
    if (target >= lim){ // 최소높이를 넘고 들고온 키가 결과값보다 크면
      if(ans >= target || ans == 0){  
        ans = target;
      }
      return;
    }
    for (int i= idx; i<N; i++){ // 고른 사람수는 들고가지 않아도 되니까
      dfs(i + 1, target+height[i]);
    }
  }

  public static void main(String[] args) throws IOException{
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    T = Integer.parseInt(st.nextToken());

    for(int tc =1;tc<=T;tc++){
      ans = 0;
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      lim = Integer.parseInt(st.nextToken());
      height = new Integer[N];


      st = new StringTokenizer(br.readLine());

      for (int i = 0; i< N; i++){
        height[i] = Integer.valueOf(st.nextToken());
      }

      Arrays.sort(height,Collections.reverseOrder());
      dfs(0, 0);
      sb.append("#").append(tc).append(" ").append(ans-lim).append("\n");
    }
    System.out.print(sb);
  }
}
