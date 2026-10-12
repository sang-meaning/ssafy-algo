import java.util.*;
import java.io.*;

/*

  0 0 0 0 0
  0 0 0 0 0

N=2, M=5, 끝점
k시간 뒤 최대 N+2k, M+2k 좌표공간
650 * 650에서 time : 0~k까지 실행,  650 * 650 * 300 * 4 = 5억
매 시간 board 완전탐색 활성화 상하좌우 번식하되, 한 시행에서 빈공간에 퍼질경우
X 큰것으로부터 번식된 것으로 덮어씌우기 - 한 time에서 빈공간을 처음 채운 경우에 대해
상태 관리 필요 - 다음 time되면 확정되는 식으로(이걸 어케하지?)
*/

public class Solution_5653_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T;
  static int N,M,K; // 세로, 가로, 배양 시간
  static int[][] board;
  static int[] dx={1,0,-1,0};
  static int[] dy={0,1,0,-1};

  public static void main(String[] args) throws IOException {
   
    // X시간동안 살아있다고 하지만, 활성화되면 어차피 상하좌우 세포 생기니, 기준 세포는 더이상 고려 대상이 아님
    // 초기 상태의 {i,j,x,t} pq에 넣음 (t는 번식 시작하는 시각)
    // 대충 (0,0,1,2),(0,1,1,2),(1,1,2,3) 넣었다고 생각해보자, 현재 time=0
    //      여기서, (0,0,1,2) 는 x=1이며, t=2 이므로,  t+x 에서 비활성화 됨을 알 수 있다 => 최종 답 구할때 사용
    // time = 1이 되었을 때 x가 1인 것들 활성화
    // time = 2가 되었을 때 x가 1인 것들 번식 : (0,0,1,2)와 (0,1,1,2) 번식, (, , 1, 4) 여러개 생성 : x는 그대로 계승되고 t는 t+x+1로 전달
    //                     x가 2인 것들 활성화
    // time = 3이 되었을 때 t가 3인 것들 번식 (, , 2, 6)
    // 활성화는 신경 안써도 되고 <번식 시작 시각> 만 보면 된다, 번식 시작 시각은 기준 세포의 t 에 x+1을 더한다

    // 그럼 큐에 넣을 때 t를 오름차순, x 내림차순 하는 pq 생성, (x 높은 것에 우선순위 주므로), 
    // 4방향 탐색 끝나고 큐에 넣을 때 board 갱신

    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      board = new int[N+2*K][M+2*K]; // 입력받는 것 k,k 위치부터 받기

      // (x좌표, y좌표, 체력, 번식 시작 시각)
      PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->
        a[3] == b[3] ? Integer.compare(b[2], a[2]) : Integer.compare(a[3], b[3]));


      // 정답 출력용 모든 좌표의 pq 정보 히스토리
      // if (cur[3]+cur[2] > K+1) count++ 로 정답 세기
      Deque<int[]> re = new ArrayDeque<>();

        for (int i=0; i<N; i++) {
          st = new StringTokenizer(br.readLine());
          for (int j=0; j<M; j++) {
              int val = Integer.parseInt(st.nextToken());
              board[i+K][j+K] = val;
              if (val != 0) {
                pq.add(new int[] {i+K, j+K, val, val+1});
                re.add(new int[] {i+K, j+K, val, val+1}); // 결과 추적용
              }

          }
        }

        // pq 초깃값 세팅 및 board 채우기 완료

        for (int time=1; time<=K; time++) {
          // pq에서 t = time인 것들 뽑아 deque에 넣고, 각각 4방향 탐색 적용
          
          Deque<int[]> q = new ArrayDeque<>(); // pq[3] == time인 (번식 시작하려는 세포 정보)
         
          // pq는 먼저 <번식 시작 시각> 오름차순하고, <생명력> 내림차순인데,
          // poll 해서 Deque에 넣으므로, <생명력> 큰 세포가 먼저 board 갱신한다
          while(!pq.isEmpty() && pq.peek()[3] == time) {
            q.add(pq.poll());
            //System.out.println("say hello");
          }

          // q 순서대로 뽑아서 4방향 탐색
          while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            for (int dir=0; dir<4; dir++) {
              int nx = cur[0]+dx[dir];
              int ny = cur[1]+dy[dir];

              if (nx<0||ny<0||nx>=N+2*K||ny>=N+2*K) continue;
              if (board[nx][ny] != 0) continue;

              // 번식 가능한 좌표
              // (0,0,1,2) 를 time=2에서 뽑는다면, 4방향 x=2로 만들고, (, , 1, 2+x+1) pq에 넣고, board 갱신
              board[nx][ny] = cur[2];
              
              pq.add(new int[] {nx,ny,cur[2], cur[3]+cur[2]+1});
              re.add(new int[] {nx,ny,cur[2], cur[3]+cur[2]+1}); // 결과 추적용 
            }

          }
        }

        // board 갱신 완료

        int count=0;
        while(!re.isEmpty()) {
          int[] cur = re.poll();

          // K번째 시행이 끝나면 time = K+1 이 되고, cur[3]+cur[2]는 번식 시작시각 + 체력
          if (cur[3]+cur[2] > K+1) count++; // 아직 죽지 않은 세포

        }

        sb.append("#"+t+" "+count).append("\n");

    }

    //tc 종료
    System.out.print(sb);
    
  }
  
}