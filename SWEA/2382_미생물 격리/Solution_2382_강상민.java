import java.util.*;
import java.io.*;

public class Solution_2382_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;
  static int[] dx={0,-1,1,0,0}; // 상 하 좌 우 순서대로 1 2 3 4 값
  static int[] dy={0,0,0,-1,1}; // 
  static int T;
  static int N,M,K;

  // q : 미생물 정보 저장 큐
  // pq : x,y 좌표 동일한 것에 대해 미생물 수로 내림차순 정렬 큐
  // qq : 동일한 x,y를 가지는 미생물 군집 임시 저장 큐

  // 군집의 x,y,크기,방향 q에 담음
  // q에서 순서대로 꺼내고 위치, 방향 업데이트해서 전부 pq에 넣음
  // pq는 x에 대해 정렬, y에 대해 정렬, 군집 "내 미생물 수" 내림차순 이고 peek으로 보며, 동일한 위치인지 판단
  // 동일한 위치는 qq에 다 넣고, 먼저 poll이 미생물 수 가장 크므로 합쳐서 새로운 정보를 q에 넣는다
  


  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      // x,y가 같으면 미생물 수로 내림차순 (x,y의 정렬 순서와 오름/내림은 중요하지 않음)
      PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
        if (a[0]==b[0] && a[1]==b[1]) {
          return Integer.compare(b[2],a[2]);
        } else if (a[0]==b[0]) {
          return Integer.compare(a[1],b[1]);
        } else {
          return Integer.compare(a[0],b[0]);
        }
      });

      Deque<int[]> q = new ArrayDeque<>();



      for (int i=0; i<K; i++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int nums = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        q.add(new int[] {x,y,nums,dir});
      }

      // 세팅 완료

      for (int time=1; time<=M; time++) {
        // 지금은 1초, 미생물 이동 구현 필요
        
        // q의 미생물 이동해서 pq에 넣기
        while(!q.isEmpty()) {
          int[] cur = q.poll();

          int curNum = cur[2];
          int curD = cur[3];
          int nx = cur[0]+dx[curD];
          int ny = cur[1]+dy[curD];

          if (nx==0||ny==0||nx==N-1||ny==N-1) {
            // 가장자리
            curNum /= 2;
            curD = changeD(curD);
          } 

           pq.add(new int[] {nx,ny,curNum,curD});
        }

        // pq에 전부 집어넣기 완료

        // pq에서 x,y 값 같은 것들 단위로 뽑아서 qq에 넣기
        while(!pq.isEmpty()) {
          Deque<int[]> qq = new ArrayDeque<>();

          int[] cur = pq.peek();
          while(!pq.isEmpty() && pq.peek()[0] == cur[0] && pq.peek()[1] == cur[1]) {
            qq.add(pq.poll());
          }

          // qq 에는 좌표가 같은 군집 정보 들어가고, 가장 상단 군집이 몸집 제일 큼
          cur = qq.poll();

          int newNum = cur[2]; // 군집 크기

          while(!qq.isEmpty()) {
            newNum += qq.poll()[2];
          }

          q.add(new int[] {cur[0], cur[1], newNum, cur[3]}); // 합친 새로운 군집 q에 넣기
        }

        // pq 전부 훑어서 q 갱신 완료
      }

      int result = 0;

      while(!q.isEmpty()) {
        int[] cur = q.poll();
        result += cur[2];
      }

      sb.append("#"+t+" "+result).append("\n");

    }

    // tc 완료
    System.out.print(sb);
    
  }

  static int changeD(int dir) {

    if (dir==1) return 2;
    else if (dir==2) return 1;
    else if (dir==3) return 4;
    else if (dir==4) return 3;
    else return -1;
  }

  
}
