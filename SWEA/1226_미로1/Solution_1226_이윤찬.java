import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution{
    static int[][] map;
    static int[] dx = {1,-1,0,0}; // 오른쪽, 왼쪽, 위 , 아래
    static int[] dy = {0,0,1,-1};
    static int sX, sY;
    static boolean ispossible;
    static boolean[][] visited;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int T = 1; T<=10; T++){
            int N = Integer.parseInt(br.readLine());
            ispossible = false;
            map = new int[16][16];
            visited = new boolean[16][16];
            for(int y = 0; y<16; y++){
                String route = br.readLine();
                for(int x = 0; x<16; x++){
                    map[y][x] = route.charAt(x)-'0';
                    if(map[y][x]==2){
                        sX= x;
                        sY =y;
                    }
                }
            }
            dfs(sX,sY);
            if(ispossible){
                System.out.println("#"+T+" "+1);
            }else{
                System.out.println("#"+T+" "+0);
            }
        }
    }
    static void dfs(int startX, int startY){
        if(map[startY][startX]==3 && !visited[startY][startX]){
            ispossible = true;
            return;
        }
        visited[startY][startX] = true;

        for(int i = 0; i <4; i++){
            int Dx = startX+ dx[i];
            int Dy = startY + dy[i];
            if(Dx<0|| Dy<0 || Dx>16|| Dy>16|| visited[Dy][Dx]|| map[Dy][Dx]==1){
                continue;
            }
            else{
                dfs(Dx,Dy);
                visited[Dy][Dx] = false;
            }
        }
    }