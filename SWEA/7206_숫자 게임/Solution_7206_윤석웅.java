import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Solution {
  static int T;
  static HashMap<Integer, Integer> memo;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      int N = Integer.parseInt(br.readLine());
      memo = new HashMap<>();

      int ans = game(N);
      sb.append("#").append(tc).append(" ")
        .append(ans).append('\n');
    }

    System.out.print(sb);
  }

  static int game(int num) {
    if (num < 10) { // 한 자리 숫자면 게임 종료
      return 0;
    }

    if (memo.containsKey(num)) {
      return memo.get(num);
    }
    String s = Integer.toString(num);
    // 숫자 사이에 존재하는 자르는 위치의 개수
    int splitCount = s.length() - 1;

    int max = 0;

    // 최소 한 곳 이상 잘라야 함
    for (int mask = 1; mask < (1 << splitCount); mask++) {
      int mul = 1;
      int current = 0;

      for (int i = 0; i < s.length(); i++) {
        current = current * 10 + (s.charAt(i) - '0');

        // 마지막 숫자거나 현재 위치에서 자르는 경우
        if (i == s.length() - 1 ||
            (i < splitCount && (mask & (1 << i)) != 0)) {

          mul *= current;
          current = 0;
        }
      }

      int temp = game(mul) + 1;

      if (max < temp) {
        max = temp;
      }
    }

    memo.put(num, max);

    return max;
  }
}