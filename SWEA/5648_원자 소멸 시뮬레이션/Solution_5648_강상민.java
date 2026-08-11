import java.util.*;
import java.io.*;

/*
1만큼 떨어져 있으면서 교차해서 터지는 원자 : 0.5거리만큼 이동하는 순간에 터짐

남
동 서

위의 예시에서 동과 서가 0.5칸 이동하는 순간 터지고 남은 안전하게 남쪽으로 이동

모든 x,y 좌표를 +1000 하고, 2를 곱한 후, 한번의 시행을 두 번의 시행으로 나눈다

먼저 모든 점 1씩 이동시키고 board에 원자의 value 더해줌
모든 원자에 대해 순회하며, 그 원자 위치의 board값 유형에 따라 3가지 경우의 수 발생

1. board > 원자값
2. board == 원자값
3. board == 0

1은 여러 원자가 그 위치에 모였음, 2는 한 원자만 그 자리에 도착함, 3은 1번상태에 대해 처리가 끝났는데 그 위치로 간 다른 원자가 또 있음
board와 atom을 적절히 갱신 후 반복

더이상 터질 원자가 없을 때까지 반복 : 최대 4000번 시행


상 하 좌 우 0 1 2 3 인데,
상 하 좌 우 는 y증, y감, x감, x증 이다


처음에 충돌 위치를 큐에 넣고, 원자 순회하며 큐의 위치인 경우~~ 로 접근해서 시간초과 떴음
두번째 풀땐 (좌표 +1000)*2 이므로 board의 index는 4000까지 가능한데, board[4000][4000] 선언해서 런타임 에러 뜸

*/

public class Solution_5648_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T, N;
  static int[] dx={0,0,-1,1};
  static int[] dy={1,-1,0,0};
  static int[][] atom;
  static int result = 0;


  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    int[][] board = new int[4003][4003]; // 1이면 터진 위치로 간주

    for (int t=1; t<=T; t++) {
      N = Integer.parseInt(br.readLine());

      atom = new int[N][5]; //x,y,dir,value,dead(0이면 살음)
      result = 0;


      for (int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());

        int x = (Integer.parseInt(st.nextToken())+1000)*2;
        int y = (Integer.parseInt(st.nextToken())+1000)*2;
        int dir = Integer.parseInt(st.nextToken());
        int val = Integer.parseInt(st.nextToken());

        atom[i] = new int[] {x,y,dir,val,0};

      }

     
      int leftAtom = N;  // 남아있는 원자수

      while(leftAtom > 0) {
        // 범위 벗어나면 leftAtom--
        
        for (int i=0; i<N; i++) {
          // atom[i] 에 대해 board 채우기

          if (atom[i][4] == 1) continue; // 이미 터졌거나 oob인 원자

          int curX = atom[i][0];
          int curY = atom[i][1];
          int curD = atom[i][2]; // 방향

          curX += dx[curD];
          curY += dy[curD];

          if (curX<0||curY<0||curX>=4000||curY>=4000)  {
            // 영원히 터질 일 없음
            leftAtom--;
            atom[i][4] = 1; // 볼 필요 없기에 1로
            continue;
          }

          atom[i][0] = curX;
          atom[i][1] = curY;
          board[curX][curY] += atom[i][3]; // 원자의 value 더해주기

        }

        // 터진 원자 value 정산과 board 초기화
        for (int i=0; i<N; i++) {
          // 각 원자 위치의 board 값을 보고 3개 경우의 수로 나뉘어짐

          if (atom[i][4] == 1) continue;

          int ax = atom[i][0];
          int ay = atom[i][1];

          if (board[ax][ay] > atom[i][3]) {
            // 1) 나말고 다른 원자도 이 위치에 왔다는 의미
            result += board[ax][ay];
            atom[i][4] = 1;
            board[ax][ay] = 0; // 해당 위치 초깃값으로, 이 후 위치에 걸린 원자 있더라도 미리 모든 값 result에 더했음 : 이 땐 leftAtom만 --
            leftAtom--;
          } else if (board[ax][ay] == atom[i][3]) {
            // 2) 나만 이 자리에 왔단 의미
            board[ax][ay] = 0;
          } else if (board[ax][ay] == 0) {
            // 3) 충돌이 발생하여 첫 if문 실행 되었음
            atom[i][4] = 1;
            leftAtom--;
          }
        }

        


        // 여기까지가 한칸 전부 움직인 후
      }

      sb.append("#"+t+" "+result).append("\n");


    }
    // tc 완료
    System.out.print(sb);
  }

 
  
}
