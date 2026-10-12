
import java.util.*;
import java.io.*;

class Solution {

    static int[][] map;
    static boolean[][] isEat;
    static boolean[][] visited;
    final static int[] dy=new int[]{0,1,0,-1};
    final static int[] dx=new int[]{1,0,-1,0};
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int n=Integer.parseInt(br.readLine());
            map=new int[n][n];
            isEat=new boolean[n][n];
            StringTokenizer st;

            for (int i = 0; i < n; i++) {
                st=new StringTokenizer(br.readLine()," ");
                for (int j = 0; j < n; j++) {
                    map[i][j]=Integer.parseInt(st.nextToken());

                    
                }
                
            }
            int answer=1;
            for (int i = 1; i <= 100; i++) {
                visited=new boolean[n][n];
                int count=0;
                boolean isEnd=true;
                eat(i,n);
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {
                        if(visited[r][c] || isEat[r][c])continue;
                        isEnd=false;
                        bfs(r, c, n);
                        count++;
                    }
                    
                }
                if(isEnd)break;
                answer=Math.max(answer, count);

            }
            sb.append("#"+test_case+" "+answer+"\n");
        } 
        System.out.println(sb.toString());
    }
    public static void eat(int X, int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j]==X) isEat[i][j]=true;
                
            }
            
        }

    }
    public static void bfs(int y, int x, int n){
        Queue<int[]> queue=new ArrayDeque<>();
        visited[y][x]=true;
        queue.offer(new int[]{y,x});
        while (!queue.isEmpty()) {
            int[] current=queue.poll();
            int cy=current[0];
            int cx=current[1];
            for (int i = 0; i < 4; i++) {
                int ny=cy+dy[i];
                int nx=cx+dx[i];
                if(ny< 0 || nx<0 || ny>=n || nx>=n)continue;
                if(visited[ny][nx] || isEat[ny][nx]) continue;
                queue.offer(new int[]{ny, nx});
                visited[ny][nx]=true;
            }
            
        }
    }
    

}