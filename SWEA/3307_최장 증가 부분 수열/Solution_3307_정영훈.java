import java.util.*;
import java.io.*;

class Solution
{   
    static int parent[];
	public static void main(String args[]) throws Exception
	{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n=Integer.parseInt(br.readLine());
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int[] arr=new int[n];
            int[] dp=new int[n];
            for (int i = 0; i < n; i++) {
                    arr[i]=Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < n; i++) {
                dp[i]=1;
                
            }
            int result=0;
            for (int i = 1; i < n; i++) {
                for (int j = i-1; j >=0; j--) {
                    if (arr[i]>arr[j]) {
                        dp[i]=Math.max(dp[i], dp[j]+1);
                        result=Math.max(result, dp[i]);
                    }
                }
                
            }
            sb.append("#"+test_case+" "+result+"\n");

        }
        System.out.println(sb.toString());
    }

    static int find(int child){
        if (parent[child]==child) {
            return child;
        }
        return parent[child]=find(parent[child]);
    }


    
}
    