import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    static int M;
    static int A;

    static int[][] move;
    static int[][] bc;

    static int[] dr = {0, -1, 0, 1, 0};
    static int[] dc = {0, 0, 1, 0, -1};

    static int charge(int r1, int c1, int r2, int c2) {

        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();

        // 사용자 A가 사용할 수 있는 BC 찾기
        for(int i=0; i<A; i++) {

            int bc_c = bc[i][0];
            int bc_r = bc[i][1];
            int range = bc[i][2];

            int distance = Math.abs(c1-bc_c) + Math.abs(r1-bc_r);

            if(distance <= range) {
                first.add(i);
            }
        }

        // 사용자 B가 사용할 수 있는 BC 찾기
        for(int i=0; i<A; i++) {

            int bc_c = bc[i][0];
            int bc_r = bc[i][1];
            int range = bc[i][2];

            int distance = Math.abs(c2-bc_c) + Math.abs(r2-bc_r);

            if(distance <= range) {
                second.add(i);
            }
        }

        // 아무 BC도 사용할 수 없는 경우까지 처리하기 위해 -1 추가
        first.add(-1);
        second.add(-1);

        int max = 0;

        for(int i=0; i<first.size(); i++) {
            for(int j=0; j<second.size(); j++) {

                int first_bc = first.get(i);
                int second_bc = second.get(j);

                int sum = 0;

                if(first_bc == -1 && second_bc == -1) {
                    sum = 0;
                }else if(first_bc == -1) {
                    sum = bc[second_bc][3];
                }else if(second_bc == -1) {
                    sum = bc[first_bc][3];
                }else if(first_bc == second_bc) {
                    sum = bc[first_bc][3];
                }else {
                    sum = bc[first_bc][3] + bc[second_bc][3];
                }

                max = Math.max(max, sum);
            }
        }

        return max;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {

            M = sc.nextInt();
            A = sc.nextInt();

            move = new int[2][M];

            for(int i=0; i<2; i++) {
                for(int j=0; j<M; j++) {
                    move[i][j] = sc.nextInt();
                }
            }

            bc = new int[A][4];

            for(int i=0; i<A; i++) {
                bc[i][0] = sc.nextInt();
                bc[i][1] = sc.nextInt();
                bc[i][2] = sc.nextInt();
                bc[i][3] = sc.nextInt();
            }

            int r1 = 1;
            int c1 = 1;

            int r2 = 10;
            int c2 = 10;

            int answer = 0;

            // 0초 위치에서도 충전 가능
            answer += charge(r1, c1, r2, c2);

            for(int i=0; i<M; i++) {

                int first_move = move[0][i];
                int second_move = move[1][i];

                r1 += dr[first_move];
                c1 += dc[first_move];

                r2 += dr[second_move];
                c2 += dc[second_move];

                answer += charge(r1, c1, r2, c2);
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }
}