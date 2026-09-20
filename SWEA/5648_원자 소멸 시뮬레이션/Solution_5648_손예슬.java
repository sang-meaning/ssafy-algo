package ysson.SWEA.모의sw역량테스트;
import java.util.*;
import java.io.*;
public class 원자소멸시뮬레이션 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int testcase=1; testcase <=T; testcase++){
            int N = Integer.parseInt(br.readLine());
            // 원자 정보담는 배열 -> x, y, dir, e, isBoom
            int[][] atom = new int[N][5];
            // 충돌관리 좌표
            int[][] board = new int[4001][4001];

            for(int j, i=0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(j = 0; j < 4; j++){
                    atom[i][j] = Integer.parseInt(st.nextToken());
                }

                // 값 보정 -> index에 넣기 위해
                atom[i][0] = atom[i][0] * 2 + 2000;
                atom[i][1] = atom[i][1] * 2 + 2000;
                atom[i][j] = 1;

                // 초기 에너지
                board[atom[i][1]][atom[i][0]] = atom[i][3];
            }

            int[] dr = {1,-1,0,0};
            int[] dc = {0,0,-1,1};
            int aliveCnt = N;
            int answer = 0;

            // 한 텀
            while(aliveCnt > 0){
                
                // 한 원자씩
                for(int i=0; i< N; i++){
                    // 이미 죽은 거 건너뛰어
                    if(atom[i][4] == 0) continue;

                    // 이동 전, 이전 타임에 충돌난 거 out
                    if(board[atom[i][1]][atom[i][0]] != atom[i][3]){
                        answer += board[atom[i][1]][atom[i][0]];
                        atom[i][4] = 0;
                        board[atom[i][1]][atom[i][0]] = 0;
                        aliveCnt--;
                        continue;
                    }
                    board[atom[i][1]][atom[i][0]] = 0;

                    // 이동
                    int d = atom[i][2];
                    int nr = atom[i][1] + dr[d];
                    int nc = atom[i][0] + dc[d];
                    
                    // 범위 안되는 거 out
                    if(nr > 4000 || nr < 0 || nc > 4000 || nc < 0){
                        atom[i][4] = 0;
                        aliveCnt--;
                        continue;
                    }

                    // board에 기록
                    atom[i][1] = nr;
                    atom[i][0] = nc;
                    board[nr][nc] += atom[i][3];
                }

            }
            System.out.println("#" + testcase + " " + answer);

        }
    }
}
// 한텀 -> 원자 이동
// 원자끼리 같은 좌표일 때 다같이 아웃
// 표 넘어가면 아웃
// 아니면 계속 이동
// 살아있는 원자 관리 -> cnt+상태 관리 -> 큐가 더 간단하지만..
// 중복된 좌표인지 -> 배열 -> 좌표를 양수로 다 바꿔야함
//             -> map + string -> map.put(pos, map.getOrDefault(pos, 0) + energy)
// 원자 정보 -> 배열 또는 클래스 객체