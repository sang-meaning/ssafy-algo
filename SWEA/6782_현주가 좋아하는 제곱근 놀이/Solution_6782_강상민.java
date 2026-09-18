import java.util.*;
import java.io.*;

public class Solution_6782_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      long N = Long.parseLong(br.readLine());

      // 현재 수인 N 보다 크거나 같은 제곱수 a를 구함
      // a-N 을 구하고 result에 더하고 N을 sqrt(N) 으로 만듦
      // 현재 값이 2가 될때까지 반복
      long result = 0;

      while(N != 2) {
        double sq = Math.sqrt((double)N);
        long iter = (long) sq;
        
        while(true) {
          long temp = 0;

          if (N <= iter * iter) {
            temp = iter* iter - N;
            result += temp;
            break;
          } else {
            iter++;
          }

          
        }
        N = iter;
        result++;
      } 

      sb.append("#"+t+" "+result).append("\n");


    }
    System.out.println(sb);
  }
  
}
