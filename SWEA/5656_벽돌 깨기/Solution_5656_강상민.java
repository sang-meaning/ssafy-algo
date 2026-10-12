import java.util.*;
import java.io.*;

// W^N = 12^4 가짓수에 대해 벽돌 깨고, 아래로 내림
// 깰 맨 위 벽돌 찾고, 큐에 넣어서 연쇄적으로 깨부숨
// 다 부수고 board 값 아래로 내리기

// 처음 풀 때 down() 메서드 작성에 오류 있었음 
// 다음 시행에서 내릴 수 있는 도착점을 int cur = H-1라는 초깃값으로 세팅하고 갱신하는 것으로 피드백 받음

public class Solution_5656_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};
    static int T;
    static int N,W,H;
    static int board[][];
    static int b_min = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            board = new int[H][W];
            b_min = Integer.MAX_VALUE;

            for (int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<W; j++) {
                    // 0빈칸 
                    board[i][j] = Integer.parseInt(st.nextToken());

                }
            }

            // board 초기화 완료

            // W^N 모든 경우에 대해 (12^4) 벽돌 떨어뜨리기

            dfs(0);

            sb.append("#"+t+" "+b_min).append("\n");


        }
        // tc 완료
        System.out.print(sb);


        
    }

    // depth는 최대 N, 
    static void dfs(int depth) {

  
            // 벽돌 최소 수 갱신
            int count = 0;
            for (int i=0; i<H; i++) {
                for (int j=0; j<W; j++) {
                    if (board[i][j] != 0) count++;
                }
            }

            b_min = Math.min(b_min, count);
            if (depth == N || b_min == 0) return;
        

        for (int j=0; j<W; j++) { // 가로(열)

            // 1) 보드 백업
            int[][] backup = new int[H][W];
             for (int a=0; a<H; a++) {
                for (int b=0; b<W; b++) {
                    backup[a][b] = board[a][b];
                }
            }


            // 2) 연쇄반응으로 벽돌 다 부수기

            // 맨 위 터뜨릴 벽돌 찾기
            int start = -1;
            for (int a=0; a<H; a++) {
                if (board[a][j] == 0) continue;
                else {
                    start = a;
                    break;
                }
            }

            if (start == -1) continue; // 해당 공간에 터뜨릴 벽돌이 없음

            Deque<int[]> q = new ArrayDeque<>(); // {x좌표, y좌표, 벽돌숫자}
            q.add(new int[] {start, j, board[start][j]});
            board[start][j] = 0;

            // 더이상 터뜨릴게 없을때까지 while

            while(!q.isEmpty()) {
                int[] cur = q.poll();
                int power = cur[2]-1; // power 만큼 4방향 터뜨리기
                
                for (int dir=0; dir<4; dir++) {
                    for (int dep = 1; dep<=power; dep++) {
                        int nx = cur[0]+dx[dir]*dep;
                        int ny = cur[1]+dy[dir]*dep;

                        if (nx<0||ny<0||nx>=H||ny>=W) continue;
                        if (board[nx][ny] == 0) continue;

                        q.add(new int[] {nx,ny,board[nx][ny]});
                        board[nx][ny] = 0;
                    }
                }
            }

            // 3) 벽돌 맨 아래로 이동
            down();


            dfs(depth+1);

            // 연쇄반응 전으로 되돌리기 (백트래킹) : 초기 back[][] 배열 따로 저장해뒀다가 롤백

            for (int a=0; a<H; a++) {
                for (int b=0; b<W; b++) {
                    board[a][b] = backup[a][b];
                }
            }


        }

    }

    // board 각 칸 아래로 내리기
    static void down() {
        for (int j=0; j<W; j++) { // 가로(열)
            int cur = H-1; // 초깃값 맨 아래, +1하며 갱신해서 윗칸 바라보도록
            for (int i=H-1; i>=0; i--) {
                if (board[i][j] != 0) { // 내려야하는 칸
                    int val = board[i][j];
                    board[i][j] = 0; // 내렸으니 빈칸됨
                    board[cur][j] = val; // 아래쪽에 갱신
                    cur--;
                }
            }

        }
       
    }

    
}
