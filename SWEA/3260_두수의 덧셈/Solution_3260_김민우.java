import java.util.*;
import java.io.*;
import java.math.BigInteger;

class Solution
{
    static BufferedReader br;
    static StringTokenizer st;
    
	public static void main(String args[]) throws Exception
	{
		br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        
        for(int test_case =1; test_case <= T; test_case++){
        	st = new StringTokenizer(br.readLine());
            BigInteger a = new BigInteger(st.nextToken());
            BigInteger b = new BigInteger(st.nextToken());
            System.out.printf("#%d %d\n", test_case, a.add(b));
        
        } 
	}
}