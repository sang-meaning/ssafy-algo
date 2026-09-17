import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {

    final static int[] dy={0,1,0,-1};
    final static int[] dx={1,0,-1,0};
    final static int END=3, START=2, WALL=1, MAX_SIZE=100;
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        for (int test_case = 1; test_case <= 10; test_case++) {
            int t=Integer.parseInt(br.readLine());
            map=new int[MAX_SIZE][MAX_SIZE];
            int[] sPos=new int[2];
            int[] ePos=new int[2];
            for (int i = 0; i < MAX_SIZE; i++) {
                String s=br.readLine();
                for (int j = 0; j < MAX_SIZE; j++) {
                    int value=s.charAt(j)-'0';
                    map[i][j]=value;
                    if(value==START){
                        sPos[0]=i;
                        sPos[1]=j;
                    }

                    if(value==END){
                        ePos[0]=i;
                        ePos[1]=j;
                    }
                }
            }

            boolean isEnd=bfs(sPos, ePos);
            sb.append("#"+test_case+" ");
            if(isEnd){
                sb.append("1\n");
            }else{
                sb.append("0\n");
            }     
        }
        System.out.println(sb.toString());
    }

    public static boolean bfs(int[] sPos, int[] ePos){
        Queue<int[]> queue=new LinkedList<>();
        boolean[][] visited=new boolean[MAX_SIZE][MAX_SIZE];
        queue.offer(new int[]{sPos[0], sPos[1]});
        visited[sPos[0]][sPos[1]]=true;

        while(!queue.isEmpty()){
            int[] cPos=queue.poll();
            int y=cPos[0];
            int x=cPos[1];

            for (int i = 0; i < 4; i++) {
                int ny=y+dy[i];
                int nx=x+dx[i];
                if(ny<0 || nx<0 || ny>=MAX_SIZE || nx>=MAX_SIZE)continue;
                if(visited[ny][nx] || map[ny][nx]==WALL)continue;
                if(map[ny][nx]==END) return true;
                queue.offer(new int[]{ny,nx});
                visited[ny][nx]=true;

                
            }
        }

        return false;

    }
  
}
