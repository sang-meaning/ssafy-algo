import java.util.*;
import java.io.*;

public class Solution_1251_프림_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T, N;
  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine());
      long[] x = new long[N];
      long[] y = new long[N];

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) x[i] = Long.parseLong(st.nextToken());

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) y[i] = Long.parseLong(st.nextToken());

      double E = Double.parseDouble(br.readLine());

      boolean[] vis = new boolean[N];
      long[] minDist = new long[N]; // tree <-> island 현재 최소 비용
      Arrays.fill(minDist, Long.MAX_VALUE);

      // {섬 번호, 그 섬을 트리에 붙이는 비용}
      PriorityQueue<long[]> pq = new PriorityQueue<>((a,b) -> Long.compare(a[1],b[1]));

      minDist[0] = 0;
      pq.add(new long[] {0,0});

      long sum = 0; // 비용 합
      int count = 0; // 선택한 섬 수

      while(!pq.isEmpty()) {
        long[] cur = pq.poll();
        int curN = (int)cur[0]; // 현재 섬 번호

        if (vis[curN]) continue; // 이미 트리에 포함되면 continue;
        vis[curN] = true;
        sum += cur[1];
        count++;
        if (count == N) break; // 모든 섬 연결

        for (int nxt=0; nxt<N; nxt++) {
          if (vis[nxt]) continue; 

          long dx = x[curN] - x[nxt];
          long dy = y[curN] - y[nxt];
          long d = dx*dx + dy*dy;

          if (d < minDist[nxt]) { // 더 낮은 비용 간선일 때만 큐에 넣기
            minDist[nxt] = d;
            pq.add(new long[] {nxt, d});
          }
        }
      }

      sb.append("#"+t+" "+Math.round(sum*E)).append("\n");



    }

    System.out.println(sb);

    
  }
}
