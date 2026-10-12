import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Solution_1247_정영훈 {
    static int minDistance;
    final static int INF=Integer.MAX_VALUE;
    static boolean[] visited;
    static int homeY,homeX;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            minDistance=INF;
            int n=Integer.parseInt(br.readLine());
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int startY=Integer.parseInt(st.nextToken());
            int startX=Integer.parseInt(st.nextToken());
            homeY=Integer.parseInt(st.nextToken());
            homeX=Integer.parseInt(st.nextToken());
            visited=new boolean[n];
            int[][] pos=new int[n][2];
            for (int i = 0; i < n; i++) {
                pos[i][0]=Integer.parseInt(st.nextToken());
                pos[i][1]=Integer.parseInt(st.nextToken());

                
            }
            dfs(startY, startX, pos, 0, n, 0);
            sb.append("#"+test_case+" "+minDistance+"\n");
        }
        System.out.println(sb.toString());
    }
    public static void dfs(int preY, int preX, int[][] pos, int distance,int n, int count){
        if(count==n){
            distance+=calDIst(preX, preY, homeX, homeY);
            minDistance=Math.min(minDistance, distance);
            return;
        }


        for (int i = 0; i < pos.length; i++) {
            int nextY=pos[i][0];
            int nextX=pos[i][1];
            if(visited[i])continue;
            visited[i]=true;
            dfs(nextY, nextX, pos, distance+calDIst(preY, preX, nextY, nextX), n, count+1);
            visited[i]=false;
        }

    }

    public static int calDIst(int y1, int x1, int y2, int x2){
        return Math.abs(x1-x2)+Math.abs(y1-y2);

    }

}
