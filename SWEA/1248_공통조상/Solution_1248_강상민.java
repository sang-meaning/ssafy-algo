import java.util.*;
import java.io.*;

public class Solution_1248_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int V,E,A,B; // 노드수, 간선수
  static int[] parent;
  static ArrayList<int[]>[] adj;

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());
      V = Integer.parseInt(st.nextToken());
      E = Integer.parseInt(st.nextToken());
      A = Integer.parseInt(st.nextToken());
      B = Integer.parseInt(st.nextToken());

      parent = new int[V+1];
      adj = new ArrayList[V+1];
      for (int i=1; i<=V; i++) {
        adj[i] = new ArrayList<>();
      }

      st = new StringTokenizer(br.readLine());

      for (int i=0; i<E; i++) {
        // 부모, 자식 받기
        int p = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // parent[] 로, 부모를 찾을 수 있는 배열과
        // adj[]로, 자식을 찾을 수 있게

        parent[c] = p;
        adj[p].add(new int[] {c}); 
      }

      // 세팅 완료

      // B의 부모를 1개씩 접근 : parent[] 가 0이 나오기 전에 반드시 찾아진다
      // B의 부모를 놓고, A의 부모를 parent[] 0 나올때까지 수행
      // 공통 부모를 찾으면, adj로 서브트리 크기 구하기

      int bp = parent[B];
      int ap = parent[A];
      boolean found = false;
      int root = -1;

      while(true) {
        if (found == true) {
          root = ap;

          break;
        }

        ap = parent[A];


        while(true) {
          if (ap == bp) {
            found = true;
            //System.out.println("ap:"+ap+" "+"bp:"+bp);
            break; // 공통부모 찾음
          }
           
          if (ap == 0) break; // 다 봤는데 공통부모 못찾음

          ap = parent[ap];
        }

        bp = parent[bp];
        //ap = parent[A];
      }





      // root 찾음
      sb.append("#"+t+" "+root+" ");

      Deque<Integer> q = new ArrayDeque<>();
      int count = 1;

      q.add(root);

      while(!q.isEmpty()) {
        int cur = q.poll();

        if (adj[cur] == null) continue;

        for (int[] nxt : adj[cur]) {
          int iter = 0;
          for (int ii=0; ii<nxt.length; ii++) {
            count++;
            q.add(nxt[iter]);
            iter++;
          }
        }
      }

      sb.append(count).append("\n");


    }

    // tc 종료

    System.out.print(sb);
    
  }
  
}
