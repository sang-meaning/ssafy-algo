import java.util.*;
import java.io.*;

public class Solution_3499_정서우 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    int T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      int N = Integer.parseInt(br.readLine());

      Deque<String> q1  = new ArrayDeque<>(); // 앞 쪽 카드 넣어둘 큐
      Deque<String> q2  = new ArrayDeque<>(); // 뒤 쪽 카드 넣어둘 큐

      st = new StringTokenizer(br.readLine());

      for (int i = 0; i < N; i++) {
        if (i < ((N + 1) / 2)) q1.add(st.nextToken()); // 앞 쪽 카드이면 q1에 추가
        else q2.add(st.nextToken());
      }

      sb.append("#").append(test_case).append(' ');

      while (!q2.isEmpty()) {
        sb.append(q1.poll())
        .append(' ')
        .append(q2.poll())
        .append(' ');
      }
      if (!q1.isEmpty()) sb.append(q1.poll()).append(' '); // 홀수이면 q1에 카드가 남아있으므로 남은 카드 처리
      sb.append('\n');
      
    }
    System.out.println(sb);

  }
}
