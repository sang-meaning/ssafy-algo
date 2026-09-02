import java.io.*;
import java.util.*;

class Solution1233{
  static StringBuilder sb;
  static StringTokenizer st;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  public static void main(String[] args) throws IOException {
    int tc = 10;
    for (int T =1; T<=tc;T++){
      st = new StringTokenizer(br.readLine());
      sb = new StringBuilder();
      sb.append("#").append(T).append(" ");

      int answer =1;
      int n = nextInt();
      for (int i = 0; i<n; i++){
        st= new StringTokenizer(br.readLine()); // 정점 정보 갱신
        st.nextToken(); // 정점 번호는 상관없으니 버리기
        if (st.countTokens() == 1){ // 정점 비교
          if (!Character.isDigit(st.nextToken().charAt(0))){
            answer = 0;
          }
        }else{
          if (Character.isDigit(st.nextToken().charAt(0))){
            answer = 0;
          }
        } // 그냥 이후 계산은 answer=1 업데이트 구문이 없어서 영향 안미치고, 남는 줄은 flush 해도 괜찮으나 생각하기 귀찮아서 그냥 계산하기
      }

      sb.append(answer).append('\n');
      System.out.print(sb);
    }
  }
  public static String next() throws IOException{
    if (st == null || !st.hasMoreTokens()){
      st = new StringTokenizer(br.readLine());
    }
    return st.nextToken();
  }
  public static int nextInt() throws IOException{
    return Integer.parseInt(next());
  }
}