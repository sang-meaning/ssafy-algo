import java.util.*;
import java.io.*;

// time 100 * 2명 * 맨하탄(8) * bc조합완탐(8*8)

public class Solution_5644_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;
    static int M,A; // 총 이동 시간, BC의 개수
    static int N = 10; // 가로 세로
    static int[][] move; // move[1][1] 은 1번 사용자가 1초때 움직이는 방향 index 1부터
    static int[] dx = {0,0,1,0,-1}; // dx[]에 그대로 move 값 넣으면 될듯
    static int[] dy = {0,-1,0,1,0}; 
    static int[][] bc; // bc[1][] : 1번 bc의 정보 bc[1][1] 부터 순서대로 x,y,충전범위, 성능
    static boolean[][] chargable; // chargable[1][1] : 1번 유저가 1번 bc 영역 내에 존재 : 매 time 마다 전부 갱신
    static int result = 0;

    // 상 우 하 좌 : 1 2 3 4 
    // 0은 이동x
    // 문제에서 상 : 0,-1   우 : 1,0    하 : 0,1    좌: -1,0
    // 배열 index 1부터 시작

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            move = new int[3][M+1];
            bc = new int[A+1][5];
            chargable = new boolean[3][A+1];
            result = 0;

            st = new StringTokenizer(br.readLine());
            for (int i=1; i<=M; i++) {
                move[1][i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i=1; i<=M; i++) {
                move[2][i] = Integer.parseInt(st.nextToken());
            }

            for (int i=1; i<=A; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=1; j<=4; j++) {
                    bc[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 세팅 완료

            // 1번 사용자는 (1,1) 2번 사용자는 (10,10)
            int x1=1,y1=1;
            int x2=10,y2=10;

            // 매 time마다 사용자 좌표 갱신, A개의 bc 중심과 거리 측정, 영역에 포함되는지 
            for (int time=0; time<=M; time++) {
                // 사용자 이동
                x1+=dx[move[1][time]];
                y1+=dy[move[1][time]];

                x2+=dx[move[2][time]];
                y2+=dy[move[2][time]];

                // 사용자 1의 chargable 갱신 
                for (int i=1; i<=A; i++) {
                    // bc[1][1] ~ bc[1][4] : x,y,충전범위,성능
                    // 맨하탄 거리 <= bc[1][3] 이면 boolean true
                    if (Math.abs(x1-bc[i][1])+Math.abs(y1-bc[i][2]) <=  bc[i][3]) {
                        chargable[1][i] = true;
                    } else {
                        chargable[1][i] = false;
                    }
                }

                // 사용자 2의 chargable 갱신 
                for (int i=1; i<=A; i++) {
                    if (Math.abs(x2-bc[i][1])+Math.abs(y2-bc[i][2]) <=  bc[i][3]) {
                        chargable[2][i] = true;
                    } else {
                        chargable[2][i] = false;
                    }
                }

                // time 에서의 chargable 갱신 완료

                // chargable[1][1] ~ chargable[1][3] : 1번 사용자가 1~3번 bc 위에 있는지 boolean

                // 최대의 성능 합 구하기

                // 2중 for문으로 전체 탐색 : 최대 64

                int maxVal = 0;
                
                for (int a=1; a<=A; a++) {
                    for (int b=1; b<=A; b++) {
                        boolean bc1 = chargable[1][a]; // 사용자 1의 a번째 bc 사용 가능 여부
                        boolean bc2 = chargable[2][b]; // 사용자 2의 b번째 bc 사용 가능 여부

                        int sum = 0; // 성능 합

                        if (bc1 == false && bc2 == false) continue; // bc1 과 bc2 가 모두 false 인 조합만 아니면 됨

                        // 4가지 상태 존재
                        if (a==b && bc1 == true && bc2 == true) {
                            // 두 사용자가 같은 bc 위에 있을 때
                            sum = bc[a][4];

                        } else if (a != b && bc1 == true && bc2 == true) {
                            // 두 사용자 모두 bc 위에 있지만 다른 bc 일때
                            sum += bc[a][4];
                            sum += bc[b][4];

                        } else if (bc1 == true && bc2 == false) {
                            // 사용자 1만 bc 위에 있을 때
                            sum += bc[a][4];

                        } else if (bc1 == false && bc2 == true) {
                            // 사용자 2만 bc 위에 있을 때
                            sum += bc[b][4];

                        }

                        maxVal = Math.max(maxVal, sum);
                    
                    }
                }

                result += maxVal;


                

            }

            sb.append("#"+t+" "+result).append("\n");
            

        }

        // tc 종료
        System.out.print(sb);
    }
    
}
