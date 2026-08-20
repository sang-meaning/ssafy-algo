import java.util.*;
import java.io.*;
 
class Solution
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
     
    static int T,N;
    static int result;
     
    static int[] customx;
    static int[] customy;
    static boolean[] visited;
     
    static int comx,comy;
    static int hx, hy;
     
    public static void main(String[] args) throws Exception {
         
        T = Integer.parseInt(br.readLine());
         
        for(int test_case=1;test_case<=T;test_case++) {
             
            N = Integer.parseInt(br.readLine());
             
            customx = new int[N];
            customy = new int[N];
            visited = new boolean[N];
            st = new StringTokenizer(br.readLine());
             
            comx = Integer.parseInt(st.nextToken());
            comy = Integer.parseInt(st.nextToken());
             
            hx = Integer.parseInt(st.nextToken());
            hy = Integer.parseInt(st.nextToken());
             
            for(int i=0;i<N;i++) {
                customx[i] = Integer.parseInt(st.nextToken());
                customy[i] = Integer.parseInt(st.nextToken());
            }
             
             
            result = Integer.MAX_VALUE;
            dfs(0,comx,comy,0); // depth, cx,cy,total
 
            sb.append("#")
            .append(test_case)
            .append(" ")
            .append(result)
            .append("\n");
        }
        System.out.print(sb);
         
    }
 
    private static void dfs(int depth, int cx, int cy, int total) {
         
        //가지치기
        if(total > result) {
            return;
        }
         
        if(depth == N) {
            total += dist(cx,hx,cy,hy);
            result = Math.min(result, total);
            return;
        }
         
        for(int i=0;i<N;i++) {
            if(visited[i] == true) {
                continue;
            }
             
            visited[i] = true;
             
            int nx = customx[i];
            int ny = customy[i];
             
            int d = dist(cx,nx,cy,ny);
             
            dfs(depth+1,nx,ny,total+d);
             
            visited[i] = false;
        }
         
    }
 
    private static int dist(int x1, int x2, int y1, int y2) {
        int dist = Math.abs(x1-x2) + Math.abs(y1-y2);
        return dist;
    }
 
}