import java.util.*;
import java.lang.*;
import java.io.*;
 
// The main method must be in a class named "Main".
class Solution  {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
 
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int result;
    public static void main(String[] args) throws Exception{
        int T = 10;
        int N = 100;
        for(int tc=1;tc<=T;tc++){
            //변수 초기화
            int[][] map = new int[N][N];
            int[][] visited = new int[N][N];
            int endx = 0;
            int endy = 0;
            result = 0;
            Deque<int[]> q = new ArrayDeque<>();
            String a = br.readLine();
             
            for(int i=0;i<N;i++){
                String temp = br.readLine();
                for(int j=0;j<N;j++){
                    map[i][j] = temp.charAt(j) - '0';
                    if(map[i][j] == 2){
                        q.add(new int[] {i,j});
                        visited[i][j] = 1;
                    }else if(map[i][j] == 3){
                        endx = i;
                        endy = j;
                    }
                }
            }
 
            while(!q.isEmpty()){
                int temp[] = q.poll();
                int cx = temp[0];
                int cy = temp[1];
 
                 
                 
                for(int dir=0;dir<4;dir++){
                    int nx = cx + dx[dir];
                    int ny = cy + dy[dir];
 
                    if(nx < 0 || ny < 0 || nx >= N || ny >= N){
                        continue;
                    }
                    if(map[nx][ny] == 1){
                        continue;
                    }
                    if(visited[nx][ny] == 1){
                        continue;
                    }
 
                    if(nx == endx && ny == endy){
                        q.clear();
                        result = 1;
                        break;
                    }
 
                    visited[nx][ny] = 1;
                    q.add(new int[] {nx,ny});
                }
            }
 
            sb.append("#"+tc+" "+result+"\n");
        }
         
        System.out.print(sb);
    }
}