import java.io.*;
import java.util.*;

public class Solution_1249_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static ArrayList<int[]>[] adj; // 노드, 비용
  static int[] history;
  static int[] d;
  static int T,N;
  static int INF = 0x3f3f3f3f;
  static int[] dx={1,0,-1,0};
  static int[] dy={0,1,0,-1};
  static int[] board;

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine());

      // 노드 N*N개
      int nodes = N*N;

      adj = new ArrayList[nodes+1];
      d = new int[nodes+1];
      history = new int[nodes+1];
      board = new int[nodes+1];

      for (int i=1; i<=nodes; i++) {
        adj[i] = new ArrayList<>();
        d[i] = INF;
      }

      int[][] num = new int[N][N];

      // 노드 번호 인덱싱
      int temp = 1;
      for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
          num[i][j] = temp;
          temp++;
        }
      }

      
      for (int i=0; i<N; i++) {
        String s = br.readLine();

        for (int j=0; j<N; j++) {
          int w = s.charAt(j) - '0';
          // history 보면서 1인 보드 개수만큼 답 빼기

          board[num[i][j]] = w;

          // 현재 위치로부터 4방향에 연결하는 간선 생성
          for (int dir=0; dir<4; dir++) {
            int nx = i+dx[dir];
            int ny = j+dy[dir];

            if (nx<0||ny<0||nx>=N||ny>=N) continue;

            // 시작 노드 num[i][j], 도착 노드 num[nx][ny] 비용 w
            int ss = num[i][j];
            int ee = num[nx][ny];
            int ww = w;

            //System.out.println(ss+" "+ee+" "+ww);

            adj[ss].add(new int[] {ee, ww});
            //adj[ee].add(new int[] {ss, ww});


          }

        }
      }

      // 세팅 완료

      // 시작 노드는 1 도착 노드는 nodes
      dijkstra(1);


      //System.out.println(" "+ccc);
      sb.append("#"+t+" "+d[nodes]).append("\n");

      
    }

    // tc 종료
    System.out.print(sb);
  }

  static void dijkstra(int startNode) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1],b[1]));
    d[startNode] = 0;
    pq.add(new int[] {startNode, 0});

    while(!pq.isEmpty()) {
      int[] cur = pq.poll();
      int curN = cur[0];
      int curW = cur[1];

      if (d[curN] != curW) continue;

      for (int[] nxt: adj[curN]) {
        int nxtN = nxt[0];
        int nxtW = nxt[1];

        if (d[nxtN] <= d[curN]+nxtW) continue;
        d[nxtN] = d[curN]+nxtW;

        pq.add(new int[] {nxtN, d[nxtN]});
        history[nxtN] = curN;
      }

      
    }
  }
  
}
