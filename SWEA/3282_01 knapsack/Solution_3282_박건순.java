import java.util.*;
import java.io.*;

class Solution
{
    static int N, K;
    static int[][] list;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        int T;
		T=Integer.parseInt(br.readLine());
		

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            list = new int[N+1][2];
            for(int i = 1; i<=N;i++){
            	st = new StringTokenizer(br.readLine());
                list[i][0] = Integer.parseInt(st.nextToken());
                list[i][1] = Integer.parseInt(st.nextToken());
            }
            
            int [][] D = new int[N+1][K+1];
            
            for(int i = 1; i<=N;i++) {
            	for(int w = 1; w<=K;w++) {
            		if(list[i][0]<= w) {
            			D[i][w] = Math.max(D[i-1][w], list[i][1] + D[i-1][w-list[i][0]]);
            		}else {
            			D[i][w] = D[i-1][w];
            		}
            	}
            }
            System.out.println("#"+test_case+" "+D[N][K]);
		}
	}
}