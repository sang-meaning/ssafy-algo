import java.util.*;

public class Solution_네트워크_정서우 {
  public int solution(int n, int[][] computers) {
    int answer = 0;
    boolean[] visited = new boolean[n];
    Deque<Integer> q = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
      if (visited[i]) continue;

      // 새로운 네트워크 발견
      answer++;
      q.add(i);
      visited[i] = true;

      while (!q.isEmpty()) {
        int cur = q.poll();

        for (int next = 0; next < n; next++) {
          if (visited[next]) continue;
          if (computers[cur][next] != 1) continue;

          visited[next] = true;
          q.add(next);
        }
      }
    }

    return answer;
  }
}