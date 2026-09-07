import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] taste;
    static int[] calory;
    static int L, N ;
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T  = Integer.parseInt(br.readLine());
        for(int t = 1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            answer= 0;
            N = Integer.parseInt(st.nextToken());
            L  =Integer.parseInt(st.nextToken());
            taste = new int[N];
            calory = new int[N];
            for(int j = 0 ; j< N; j++){
                st = new StringTokenizer(br.readLine());
                int t1 =Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                taste[j] = t1;
                calory[j] =c1;
            }
            dfs(0,0,0);

            System.out.println("#"+t+" "+answer);
        }
    }
    static void dfs(int depth , int tastSum, int calorySum){
        if(calorySum>L) return;

        if(depth == N) {
            answer = Math.max(answer,tastSum);
            return;
        }
        dfs(depth+1,tastSum+taste[depth],calorySum+calory[depth]);
        dfs(depth+1, tastSum,calorySum);

    }