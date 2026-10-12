import java.util.*;

public class Solution {

    static int N;
    static int M;
    static int C;

    static int[][] map;
    static int[] honey;
    static int maxProfit;
    static int answer;

    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            C = sc.nextInt();

            map = new int[N][N];

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    map[x][y] = sc.nextInt();
                }
            }

            answer = 0;

            selectWorkers();

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }
 
    
    
    
    static void selectWorkers() {
    	
        for (int x1 = 0; x1 < N; x1++) {
            for (int y1 = 0; y1 <= N - M; y1++) {
                int profit1 = getMaxProfit(x1, y1);

                for (int x2 = x1; x2 < N; x2++) {
 
                	int startY;

                    if (x1 == x2) {
                        startY = y1 + M;
                    }

                    else {
                        startY = 0;
                    }

                    for (int y2 = startY; y2 <= N - M; y2++) {
                        int profit2 = getMaxProfit(x2, y2);

                        answer = Math.max(answer,profit1 + profit2);
                    }
                }
            }
        }
    }

 
    static int getMaxProfit(int x, int y) {

        honey = new int[M];

        for (int i = 0; i < M; i++) {
            honey[i] = map[x][y + i];
        }

        maxProfit = 0;

        dfs(0, 0, 0);

        return maxProfit;
    }

    
    static void dfs(int index, int sum, int profit) {

        if (sum > C) {
            return;
        }

        if (index == M) {
            maxProfit = Math.max(maxProfit,profit);
            return;
        }

        int currentHoney = honey[index];

        dfs(index + 1, sum + currentHoney, profit + currentHoney * currentHoney);
        dfs(index + 1, sum, profit );
    }
}