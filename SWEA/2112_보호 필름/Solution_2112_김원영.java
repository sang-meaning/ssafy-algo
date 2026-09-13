import java.util.Scanner;

public class Solution {

    static int[][] map;
    static int D;
    static int W;
    static int K;
    static int min;

    static void backtracking(int depth, int count) {

        if(count >= min) {
            return;
        }

        if(depth == D) {
            if(check()) {
                min = count;
            }
            return;
        }

        // 1. 현재 막에 약품을 넣지 않는 경우
        backtracking(depth+1, count);

        // 원래 상태 저장
        int[] temp = new int[W];

        for(int i=0; i<W; i++) {
            temp[i] = map[depth][i];
        }

        // 2. 현재 막에 A 약품을 넣는 경우
        for(int i=0; i<W; i++) {
            map[depth][i] = 0;
        }

        backtracking(depth+1, count+1);

        // 3. 현재 막에 B 약품을 넣는 경우
        for(int i=0; i<W; i++) {
            map[depth][i] = 1;
        }

        backtracking(depth+1, count+1);

        // 원상복구
        for(int i=0; i<W; i++) {
            map[depth][i] = temp[i];
        }
    }

    static boolean check() {

        for(int j=0; j<W; j++) {

            int count = 1;
            boolean possible = false;

            for(int i=1; i<D; i++) {

                if(map[i][j] == map[i-1][j]) {
                    count++;
                }else {
                    count = 1;
                }

                if(count >= K) {
                    possible = true;
                    break;
                }
            }

            if(K == 1) {
                possible = true;
            }

            if(!possible) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {

            D = sc.nextInt();
            W = sc.nextInt();
            K = sc.nextInt();

            map = new int[D][W];
            min = K;

            for(int i=0; i<D; i++) {
                for(int j=0; j<W; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            if(check()) {
                System.out.println("#" + tc + " 0");
                continue;
            }

            backtracking(0, 0);

            System.out.println("#" + tc + " " + min);
        }

        sc.close();
    }
}