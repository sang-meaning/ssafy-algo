import java.io.*;
import java.util.*;

public class Solution1227 {
    static int N, ans;
    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;

    static void BFS(int r, int c){
        Queue<int[]> worQ = new ArrayDeque<>();
        worQ.offer(new int[]{r,c});
        map[r][c] = 1;
        while(!worQ.isEmpty()){
            int[] idx = worQ.poll();

            for(int i = 0; i<4;i++){
                int nr = idx[0]+dx[i]; int nc = idx[1]+ dy[i];
                if (nr >=100 || nr <0 || nc >= 100 || nc < 0){
                    continue;
                }
                if (map[nr][nc] == 1){
                    continue;
                }
                if (map[nr][nc] == 3){
                    ans = 1;
                    return;
                }
                worQ.offer(new int[]{nr, nc});

                map[nr][nc] = 1;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        for (int n = 1; n<=10; n++){
            int rstart=0; int cstart=0;
            ans = 0;
            N = Integer.parseInt(next());
            map = new int[100][100];
            for (int i =0; i<100;i++){
                String s = next();
                for (int j =0; j<100;j++){
                    map[i][j] = s.charAt(j)- '0';
                    if (map[i][j] == 2){
                        rstart = i; cstart = j;
                    }
                }
            }

            BFS(rstart, cstart);
            sb.append("#").append(N).append(" ").append(ans).append('\n');
        }
        System.out.print(sb);
    }    

    static String next() throws IOException{
        if (st == null || !st.hasMoreTokens()){
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
}
