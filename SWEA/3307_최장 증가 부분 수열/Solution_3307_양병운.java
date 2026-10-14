import java.util.*;
import java.io.*;
class Solution {
	public static void main(String args[]) throws Exception	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)	{
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            long[] arr = new long[N];
            for(int i=0; i<N; i++) arr[i] = Long.parseLong(st.nextToken());
            long[] result = new long[N];
            int len = 0;
            for(int i=0; i<N; i++){
                int p = Arrays.binarySearch(result, 0, len, arr[i]);
                if(p < 0) p = -p-1; 
                result[p] = arr[i];
                if(p == len) len++;
            }
            System.out.println("#"+test_case+" "+len);
		}
	}
}