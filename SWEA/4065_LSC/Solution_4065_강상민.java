import java.util.*;
import java.io.*;


public class Solution_4065_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static String s1;
  static String s2;
  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      s1 = br.readLine();
      s2 = br.readLine();

      int len1 = s1.length();
      int len2 = s2.length();

      int[][] d = new int[len1+1][len2+1];
      // d[i][j] : 첫 문자열 i번째, 두번째 문자열 j번째까지 봤을 때 LCS 길이

     


      for (int i=1; i<=len1; i++) {
        for (int j=1; j<=len2; j++) {
          if (s1.charAt(i-1) == s2.charAt(j-1)) {
             // 두 문자열의 i번째, j번째 char가 같으면 그 둘을 모두 선택하고 길이 1 늘리기, d[i][j] = d[i-1][j-1] + 1
            d[i][j] = d[i-1][j-1]+1;
          }

            // 그렇지 않으면, i-1,j 에서의 LCS 와 i,j-1 에서의 LCS 중 큰 것으로 갱신
          d[i][j] = Math.max(d[i][j], d[i-1][j]);
          d[i][j] = Math.max(d[i][j], d[i][j-1]);
        }
      }


      sb.append("#"+t+" "+d[len1][len2]).append("\n");


    }

    // tc 종료

    System.out.print(sb);
    
  }
  
}
