import java.io.*;
import java.util.*;
import java.math.BigInteger;

class Solution
{
	public static void main(String args[]) throws IOException
	{	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine().trim());
        for(int i = 1; i <= T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            BigInteger a = new BigInteger(st.nextToken());
            BigInteger b = new BigInteger(st.nextToken());
            
            BigInteger sum = a.add(b);
            System.out.println("#"+ i + " " + sum);
        }
	}
}