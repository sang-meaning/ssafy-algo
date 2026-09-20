
import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int testcase = 1; testcase <= 10; testcase++){
            br.readLine();
            int[] startIdx = new int[2];
            int[][] board = new int[100][100];
            for(int i = 0; i < 100; i++){
                String str = br.readLine();
                for(int j = 0; j<100; j++){
                    if(str.charAt(j) == '2'){
                        startIdx[0] = i;
                        startIdx[1] = j;
                    }

                    board[i][j] = str.charAt(j) - '0';
                }
            }

            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{startIdx[0], startIdx[1]});
            int[] dr = {-1,1,0,0};
            int[] dc = {0,0,-1,1};
            int answer = 0;
            while(!q.isEmpty()){
                int[] c = q.poll();
                int cr = c[0];
                int cc = c[1];

                for(int d = 0; d<4; d++){
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];
                    if(nr < 0 || nc < 0 || nr >= 100 || nc >= 100) continue;
                    if(board[nr][nc] == 1 || board[nr][nc] == 2) continue;

                    if(board[nr][nc] == 3){
                        answer = 1;
                        break;
                    }

                    q.add(new int[]{nr, nc});
                    board[nr][nc] = 1;
                }

                if(answer == 1) break;
            }
            System.out.println("#" + testcase + " " + answer);
        }
    }
}
