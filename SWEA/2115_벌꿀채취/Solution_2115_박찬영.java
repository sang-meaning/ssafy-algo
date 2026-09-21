import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {
    static int n, m, c;
    static int[][] board;
    static int[][] maxProfit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            
            board = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            maxProfit = new int[n][n - m + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= n - m; j++) {
                    calcMaxProfit(i, j, 0, 0, 0);
                }
            }
            
            int answer = 0;
            for (int i1 = 0; i1 < n; i1++) {
                for (int j1 = 0; j1 <= n - m; j1++) {
                    for (int i2 = i1; i2 < n; i2++) {
                        int j2Start = (i1 == i2) ? j1 + m : 0;
                        for (int j2 = j2Start; j2 <= n - m; j2++) {
                            answer = Math.max(answer, maxProfit[i1][j1] + maxProfit[i2][j2]);
                        }
                    }
                }
            }
            
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
    
    static void calcMaxProfit(int r, int cIdx, int depth, int sum, int profitSum) {
        if (sum > c) return;
        
        maxProfit[r][cIdx] = Math.max(maxProfit[r][cIdx], profitSum);
        
        if (depth == m) return;
        
        calcMaxProfit(r, cIdx, depth + 1, sum + board[r][cIdx + depth], profitSum + board[r][cIdx + depth] * board[r][cIdx + depth]);
        calcMaxProfit(r, cIdx, depth + 1, sum, profitSum);
    }
}