import java.util.*;
import java.io.*;

public class Solution_3952_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int N,M; // 학생수, 간선 수
  static int[] degree; // 진입 차수
  static ArrayList<Integer>[] list; 


  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      degree = new int[N+1];
      list = new ArrayList[N+1];

      for (int i=1; i<=N; i++) {
        list[i] = new ArrayList<>();
      }

      for (int i=0; i<M; i++) {
        st = new StringTokenizer(br.readLine());

        // a 뒤에 b
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        list[a].add(b);

        degree[b]++;


      }

      // 세팅 완료

      topologicalSort(t);
      
    }
    // tc 종료

     System.out.print(sb);
    
  }

  static void topologicalSort(int t) {
    Deque<Integer> q = new ArrayDeque<>();
    sb.append("#"+t+" ");

    for (int i=1; i<=N; i++) {
      if (degree[i] == 0) {
        // 진입 차수 0인 것
        q.add(i);
      }
    }

    while(!q.isEmpty()) {
      int cur = q.poll();

      sb.append(cur+" ");

      for (int nxt : list[cur]) {
        degree[nxt]--;

        if (degree[nxt] == 0) {
          q.add(nxt);
        }
      }
    }
    sb.append("\n");

   


  }
  
}
