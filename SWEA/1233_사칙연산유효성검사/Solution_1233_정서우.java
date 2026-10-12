import java.util.*;
import java.io.*;

public class Solution_1233_정서우 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  public static void main(String[] args) throws IOException {
    int T = 10;
    for (int test_case = 1; test_case <= T; test_case++) {
      int N = Integer.parseInt(br.readLine());
      int answer = 1;

      for (int n = 0; n < N; n++) {
        st = new StringTokenizer(br.readLine());
        st.nextToken();
        String s = st.nextToken();
        boolean isOp = false;

        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
          isOp = true;
        }

        if (isOp) {
          if (st.countTokens() != 2) {
            answer = 0;
          }
        } else {
          if (st.countTokens() != 0) {
            answer = 0;
          }
        }
        
        
      }
      sb.append('#').append(test_case).append(' ').append(answer).append('\n');
    }
    System.out.println(sb);
  }
}