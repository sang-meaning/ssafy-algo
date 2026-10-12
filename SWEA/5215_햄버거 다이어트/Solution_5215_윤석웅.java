import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
  static int N, limit, ans =0;
  static int[][] input;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();
    int tc = Integer.parseInt(st.nextToken());

    for (int T = 1; T<=tc; T++){
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      limit = Integer.parseInt(st.nextToken());

      input = new int[N][2];
      for (int i = 0; i<N;i++){
        st = new StringTokenizer(br.readLine());
        input[i][0] = Integer.parseInt(st.nextToken());
        input[i][1] = Integer.parseInt(st.nextToken());
      }

      diet(0,0, 0);
      sb.append("#").append(T).append(" ").append(ans).append("\n");
      ans = 0;
    }
    System.out.println(sb);
  }

  static void diet(int idx, int sum, int score){
    if (sum <= limit && ans < score){
      ans = score;
    }else if (sum > limit || idx >= N){
      return;
    }
    if (sum <= limit && idx <N){
      diet(idx+1, sum, score);
      
      diet(idx+1, sum+ input[idx][1], score + input[idx][0]);
    }
  }
}
