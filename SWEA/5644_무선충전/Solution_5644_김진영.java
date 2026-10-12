import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int T, M, A;

    // 이동 명령
    static int[] user1;
    static int[] user2;

    /*
     * BC 정보
     * bc[i][0] = x
     * bc[i][1] = y
     * bc[i][2] = 범위 C
     * bc[i][3] = 성능 P
     */
    static int[][] bc;

    /*
     * 0 : 이동 없음
     * 1 : 상
     * 2 : 우
     * 3 : 하
     * 4 : 좌
     *
     * 좌표를 (x,y)가 아니라
     * 배열 기준 (row,col)로 사용
     */
    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};


    public static void main(String[] args) throws Exception {

        T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            user1 = new int[M];
            user2 = new int[M];

            // 사용자 A 이동
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < M; i++) {
                user1[i] = Integer.parseInt(st.nextToken());
            }

            // 사용자 B 이동
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < M; i++) {
                user2[i] = Integer.parseInt(st.nextToken());
            }


            bc = new int[A][4];

            for(int i = 0; i < A; i++) {

                st = new StringTokenizer(br.readLine());

                /*
                 * 문제 입력은
                 * X Y C P
                 *
                 * 배열에서는
                 * row = Y
                 * col = X
                 *
                 * 로 사용하는 게 편함
                 */
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                bc[i][0] = y;   // row
                bc[i][1] = x;   // col
                bc[i][2] = c;
                bc[i][3] = p;
            }


            // 사용자 A 시작 위치
            int ax = 0;
            int ay = 0;

            // 사용자 B 시작 위치
            int bx = 9;
            int by = 9;

            int result = 0;


            /*
             * 총 M번 이동하지만
             * 충전은 처음 위치에서도 이루어짐.
             *
             * 따라서 총 M+1번 충전
             */
            for(int time = 0; time <= M; time++) {

                // 현재 위치에서 최대 충전량 계산
                result += charge(ax, ay, bx, by);

                // 마지막 충전이면 더 이상 이동 안 함
                if(time == M) {
                    break;
                }

                // 사용자 A 이동
                int commandA = user1[time];

                ax += dx[commandA];
                ay += dy[commandA];


                // 사용자 B 이동
                int commandB = user2[time];

                bx += dx[commandB];
                by += dy[commandB];
            }


            sb.append("#")
              .append(tc)
              .append(" ")
              .append(result)
              .append("\n");
        }

        System.out.print(sb);
    }


    /*
     * 현재 두 사용자의 위치에서
     * 얻을 수 있는 최대 충전량
     */
    static int charge(
            int ax, int ay,
            int bx, int by) {

        int max = 0;

        /*
         * -1은 "BC를 사용하지 않는다"는 의미
         *
         * 한 사용자가 아무 BC에도 연결되지 않는 경우도
         * 처리하기 위해 -1부터 시작
         */
        for(int a = -1; a < A; a++) {

            // A 사용자가 a번 BC 사용 가능한지
            if(a != -1 &&
               !canConnect(ax, ay, a)) {

                continue;
            }


            for(int b = -1; b < A; b++) {

                // B 사용자가 b번 BC 사용 가능한지
                if(b != -1 &&
                   !canConnect(bx, by, b)) {

                    continue;
                }


                int sum = 0;

                /*
                 * 둘 다 BC를 사용하지 않음
                 */
                if(a == -1 && b == -1) {

                    sum = 0;
                }

                /*
                 * A만 BC 사용
                 */
                else if(b == -1) {

                    sum = bc[a][3];
                }

                /*
                 * B만 BC 사용
                 */
                else if(a == -1) {

                    sum = bc[b][3];
                }

                /*
                 * 서로 다른 BC 사용
                 */
                else if(a != b) {

                    sum = bc[a][3]
                        + bc[b][3];
                }

                /*
                 * 같은 BC 사용
                 *
                 * 두 사람이 공유해도
                 * 두 사람의 충전량 합은 BC 성능 P
                 */
                else {

                    sum = bc[a][3];
                }

                max = Math.max(max, sum);
            }
        }

        return max;
    }


    /*
     * 사용자 (x,y)가
     * index번 BC 범위 안에 있는지 확인
     *
     * 맨해튼 거리:
     *
     * |x1-x2| + |y1-y2|
     */
    static boolean canConnect(
            int x, int y,
            int index) {

        int bx = bc[index][0];
        int by = bc[index][1];

        int coverage = bc[index][2];

        int distance =
                Math.abs(x - bx)
              + Math.abs(y - by);

        return distance <= coverage;
    }
}