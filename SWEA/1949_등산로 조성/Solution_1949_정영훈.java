import java.util.*;
import java.io.*;

public class Solution_1949_정영훈
{
    //1. 최고 봉우리의 좌표값 기준으로 dfs
    //2. 현위치-다음위치가 k값보다 크다면 return
    //3. max 등산로 길이 update;
    final static int[] dy={0,1,0,-1};
    final static int[] dx={1,0,-1,0};
    static int[][] map;
    static boolean[][] visited;
    static int maxLength;
	public static void main(String args[]) throws Exception
	{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int n=Integer.parseInt(st.nextToken());
            int k=Integer.parseInt(st.nextToken());
            map=new int[n][n];
            int maxHigh=0;
            for(int i=0; i<n; i++){
                st=new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int high=Integer.parseInt(st.nextToken());
                    maxHigh=Math.max(maxHigh, high);
                    map[i][j]=high;
                }
            }
            List<int[]> maxPosList=findPos(maxHigh, n);
            int answer=0;
            for (int[] pos : maxPosList) {
                int y=pos[0];
                int x=pos[1];
                maxLength=0;
                visited=new boolean[n][n];
                visited[y][x]=true;
                dfs(y,x,map[y][x], n, 1, k, false);
                visited[y][x]=false;
                answer=Math.max(answer, maxLength);
            }
            System.out.println("#"+test_case+" "+answer);
        }
        
    }
    public static List<int[]> findPos(int maxHigh, int n){
        List<int[]> ret=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j]==maxHigh){
                    ret.add(new int[]{i,j});
                }
                
            }
            
        }
        return ret;

    }

    public static void dfs(int y, int x, int cHigh, int n, int count, int k, boolean isTrim){
        maxLength=Math.max(maxLength, count);
        for(int i=0; i<4; i++){
            int ny=y+dy[i];
            int nx=x+dx[i];
            if(ny<0 || nx<0 || ny>=n || nx>=n) continue;
            if(visited[ny][nx]) continue;
            
            if(map[ny][nx]>=cHigh && !isTrim){
                int validK=(map[ny][nx]-cHigh)+1;
                if(validK<=k){
                    visited[ny][nx]=true;
                    dfs(ny, nx, map[ny][nx]-validK, n, count+1, k, true);
                    visited[ny][nx]=false;
                }
            }else if(map[ny][nx]<cHigh && !isTrim){
                visited[ny][nx]=true;
                dfs(ny, nx, map[ny][nx], n, count+1, k, false);
                visited[ny][nx]=false;
            }else if(map[ny][nx]<cHigh && isTrim){
                visited[ny][nx]=true;
                dfs(ny, nx, map[ny][nx], n, count+1, k, true);
                visited[ny][nx]=false;
            }
        }


    }
  


    


}