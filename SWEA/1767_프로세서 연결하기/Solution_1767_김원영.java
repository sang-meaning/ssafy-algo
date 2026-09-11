package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    static int[][] map;
    static int N;
    static int max;
    static int min;
    static int sum;
    static int count;

    static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1};

    static List<int[]> point = new ArrayList<>();

    static void backtracking(int start) {
        if(start == point.size()) {
            if(count > max) {
                max = count;
                min = sum;
            }else if(count == max) {
                min = Math.min(min, sum);
            }
            return;
        }

        int curr_r = point.get(start)[0];
        int curr_c = point.get(start)[1];

        for(int j=0; j<4; j++) {
            int next_r = curr_r;
            int next_c = curr_c;
            int length = 0;
            boolean possible = true;

            // 현재 방향으로 전선을 연결할 수 있는지 확인
            while(true) {
                next_r += dr[j];
                next_c += dc[j];

                if(next_r < 0 || next_r >= N || next_c < 0 || next_c >= N) {
                    break;
                }

                if(map[next_r][next_c] != 0) {
                    possible = false;
                    break;
                }

                length++;
            }

            if(!possible) {
                continue;
            }

            // 전선 설치
            next_r = curr_r;
            next_c = curr_c;

            for(int k=0; k<length; k++) {
                next_r += dr[j];
                next_c += dc[j];
                map[next_r][next_c] = 2;
            }

            count++;
            sum += length;

            backtracking(start+1);

            count--;
            sum -= length;

            // 전선 원상복구
            next_r = curr_r;
            next_c = curr_c;

            for(int k=0; k<length; k++) {
                next_r += dr[j];
                next_c += dc[j];
                map[next_r][next_c] = 0;
            }
        }

        // 현재 코어를 연결하지 않는 경우
        backtracking(start+1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {
            N = sc.nextInt();

            map = new int[N][N];
            max = 0;
            min = Integer.MAX_VALUE;
            sum = 0;
            count = 0;
            point.clear();

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    map[i][j] = sc.nextInt();

                    if(map[i][j] == 1 && i>0 && i<N-1 && j>0 && j<N-1) {
                        point.add(new int[] {i, j});
                    }
                }
            }

            backtracking(0);

            System.out.println("#" + tc + " " + min);
        }

        sc.close();
    }
}