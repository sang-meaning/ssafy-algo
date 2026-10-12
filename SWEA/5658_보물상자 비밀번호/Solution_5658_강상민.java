import java.util.*;
import java.io.*;


/*
deque, poll, add 하면 rotate
한 숫자의 길이는 N/4 = n
n-1번 회전하면 모든 수가 나옴 : 0번회전 ~ n-1번 회전까지
회전해서 생기는 4개의 수를 전부 set에 넣고, 마지막에 10진수로 변환, arr에 넣고 sort, k번째 접근


*/

public class Solution_5658_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int N,K;
  static String s;
  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      s = br.readLine();

      // 입력 완료

      Deque<Character> q = new ArrayDeque<>(); // 회전시킬 원본
      HashSet<String> set = new HashSet<>(); // 만든 전체 숫자 set

      for (int i=0; i<s.length(); i++) {
        q.add(s.charAt(i));
      }



      int n = N/4; // 회전 필요횟수

      for (int i=0; i<n; i++) { // 각 회전 시행
        char temp = q.poll();
        q.add(temp);

        Deque<Character> q_temp = new ArrayDeque<>(q); // 주소 말고 진짜 수만 담은걸로 복사
       

        for (int iter=0; iter<4; iter++) { // N을 n길이의 4개의 char 뽑아서 String으로 만들고 set에 넣기
          String num = "";
          for (int slen = 0; slen<n; slen++) {
              num += q_temp.poll();
          }

          set.add(num);
          
        }
        
      }

      // 모든 회전에 대해 숫자 후보 set에 넣음

      ArrayList<Integer> arr = new ArrayList<>();

      for (String s : set) {
        int len = s.length();

        int number = 0;
        int scale = 0;

        for (int ii=len-1; ii>=0; ii--) {
          // s의 오른쪽 끝부터 접근
          char c = s.charAt(ii);
          int temp = (int)c - '0';
          if (temp >= 10) temp -= 7; // 10보다 큰 'A' 는 7 빼야 10진수 숫자 등장

          //System.out.println(temp);
          number += (temp) * Math.pow(16, scale); // 10진수 숫자
          scale++;

        }

        //System.out.println(number);
        arr.add(number);

      }

      arr.sort((a,b) -> Integer.compare(b,a));
      sb.append("#"+t+" "+arr.get(K-1)).append("\n");
      
    }

    // tc 완료
    System.out.print(sb);
    
  }
  
}
