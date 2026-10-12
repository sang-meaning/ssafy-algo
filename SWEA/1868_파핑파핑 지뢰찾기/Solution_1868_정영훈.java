import java.util.*;
import java.io.*;

class Solution {

    static String[][] map;
    static boolean[][] visited;
    final static int[] dy=new int[]{-1,0,1,1,1,0,-1,-1};
    final static int[] dx=new int[]{1,1,1,0,-1,-1,-1,0};
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int n=Integer.parseInt(br.readLine());
            map=new String[n][n];
            visited=new boolean[n][n];
            int answer=0;

            for (int r = 0; r < n; r++) {
                String s=br.readLine();
                for (int c = 0; c < n; c++) {
                    map[r][c]=String.valueOf(s.charAt(c));
                }
                
            }


            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if(visited[r][c] || map[r][c].equals("*"))continue;
                    int count=0;
                    for(int i=0; i<8; i++){
                        int ny=r+dy[i];
                        int nx=c+dx[i];
                        if(ny<0 || nx<0 || ny>=n || nx>=n)continue;
                        if(map[ny][nx].equals("*")) count++;
                }
                if(count>0)continue;
                    answer++;
                    bfs(r,c,n);
                }
                
            }



            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if(visited[r][c] || map[r][c].equals("*"))continue;
                    answer++;
                    bfs(r,c,n);
                }
                
            }

            sb.append("#"+test_case+" "+answer+"\n");

        }
        System.out.println(sb.toString());
        

    }


    public static void bfs(int y, int x, int n){
        Queue<int[]> queue=new ArrayDeque<>();
        visited[y][x]=true;
        queue.offer(new int[]{y,x});

        while(!queue.isEmpty()){
            int[] current=queue.poll();
            int cy=current[0];
            int cx=current[1];
            int count=0;
            for(int i=0; i<8; i++){
                int ny=cy+dy[i];
                int nx=cx+dx[i];
                if(ny<0 || nx<0 || ny>=n || nx>=n)continue;
                if(visited[ny][nx]) continue;
                if(map[ny][nx].equals("*")){
                    count++;
                }
            }
            if(count==0){
                for(int i=0; i<8; i++){
                    int ny=cy+dy[i];
                    int nx=cx+dx[i];
                    if(ny<0 || nx<0 || ny>=n || nx>=n)continue;
                    if(visited[ny][nx] || map[ny][nx].equals("*")) continue;
                    queue.offer(new int[]{ny, nx});
                    visited[ny][nx]=true;
                }
            }else{
                map[cy][cx]=String.valueOf(count);
            }
            

        }

    }
    

}