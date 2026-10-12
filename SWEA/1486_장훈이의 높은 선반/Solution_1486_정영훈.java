import java.util.*;
import java.io.*;

public class Solution_1486_정영훈 {


    static boolean[] visited;
    static int[] people;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            people = new int[n];
            answer=Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                int value = Integer.parseInt(st.nextToken());
                people[i] = value;
            }
            dfs(n, b, 0, 0);
            int result=answer-b;
            
            
            sb.append("#"+test_case+" "+result+"\n");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int n, int b, int depth, int sum){
        if(sum>=b){
            answer=Math.min(answer, sum);
        }

        if(depth==n) return;


        dfs(n, b, depth+1, sum);
        dfs(n,b,depth+1,sum+people[depth]);



    }
}