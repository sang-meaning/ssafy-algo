import java.util.*;
import java.io.*;

public class Solution{


    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    static int sX, sY;
    static boolean ispossible;
    static boolean[][] visited;

    public static void main(String[] args)throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int T =1 ; T<=10; T++){

            int tc = Integer.parseInt(br.readLine());

            ispossible = false;

            map = new int[100][100];
            visited = new boolean[100][100];

            for(int y = 0; y<100 ; y++){
                Stirng route = br.readLine();

                for(int x =0 ; x<100; x++){
                    map[y][x] = route.charAt(x)-'0';
                    if(map[y][x]==2){
                        sX=x;
                        sY=y;
                    }
                }
            }
            dfs(sX,sY);
            System.out.println("#"+tc+" "+(ispossible ? 1:0));
        }
    }
        
    static void dfs(int x, int y) {

        if (ispossible) {
            return;
        }

        if (map[y][x] == 3) {
            ispossible = true;
            return;
        }

        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= 100 || ny >= 100) {
                continue;
            }

            if (visited[ny][nx] || map[ny][nx] == 1) {
                continue;
            }

            dfs(nx, ny);
        }
    }
}