import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;
    static int result;
    static int N,M;
    static int[] arr;
    static int[] visited;
    
    public static void main(String[] args) throws Exception{
        T = Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<=T;test_case++){

            result = 0;
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            arr = new int[N];
            visited = new int[N];
            
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }            

            dfs(0,0,0);
            if(result == 0){
                result = -1;
            }
            
            sb.append("#")
                .append(test_case)
                .append(" ")
                .append(result)
                .append("\n");
        }
        System.out.print(sb);
    }
    private static void dfs(int depth, int start,int count){

        if(depth == 2){
            
            if(count <= M){
                result = Math.max(result,count);
            }
            return;
        }

        for(int i=start;i<N;i++){

            if(visited[i] == 1){
                continue;
            }
            count += arr[i];
            visited[i] = 1;
            
            dfs(depth+1,i,count);

            count -= arr[i];
            visited[i] = 0;
        }
    }
}
