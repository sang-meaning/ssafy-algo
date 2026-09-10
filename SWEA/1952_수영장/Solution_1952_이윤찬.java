import java.util.*;
import java.io.*;


public class swimmingpool {
  static int T;
  static int[] price;
  static int[] plan;
  static int[] dp; // 최소비용 찾기 dp
  static int answer;

  public static void main(String[] args)throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());
    StringTokenizer st;
    for(int t= 1; t<=T; t++){
      price = new int[4];
      plan = new int[13];
      dp = new int[13];
      
      st= new StringTokenizer(br.readLine());
      for(int i =0; i<4; i++){
        price[i] = Integer.parseInt(st.nextToken());
      }
      
      
      st= new StringTokenizer(br.readLine());
      for(int i = 1; i<=12; i++){
        plan[i] = Integer.parseInt(st.nextToken());
      }

      for(int i = 1; i<=12; i++){

        dp[i] = dp[i-1]+Math.min(plan[i]*price[0],price[1]);

        if(i>=3){
          dp[i] = Math.min(dp[i],dp[i-3]+price[2]);
        }
      }
      answer = Math.min(dp[12],price[3]);
      System.out.println(answer);
    }
  }
}