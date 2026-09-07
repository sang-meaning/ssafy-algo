import java.util.*;
import java.io.*;

public class Solution_1233_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T = 10;

  public static void main(String[] args) throws IOException {
    for (int t=1; t<=T; t++) {
      int n = Integer.parseInt(br.readLine().trim());

      int answer = 1;
      for (int i=0; i<n; i++) {
        st = new StringTokenizer(br.readLine());

        // 노드 번호
        st.nextToken();

        // 노드 값이 연산자인데, 바로 밑 자식 노드가 2개가 아니라면 return 0
        String s1 = st.nextToken();
        boolean ok = false;

        if (s1.equals("-") || s1.equals("+") || s1.equals("/") || s1.equals("*"))
          ok = true;

        // 노드 값이 숫자인데, 바로 밑 자식 노드가 0이 아니라면 return 0
        if (ok) {
          if (st.countTokens() != 2) answer = 0;
        } else {
          if (st.countTokens() != 0) answer = 0;
        }
        
        
      }

      sb.append("#"+t+" "+answer).append("\n");
    }

    System.out.print(sb);
    
  }
  
}
