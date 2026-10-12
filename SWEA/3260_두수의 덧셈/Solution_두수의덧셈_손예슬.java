
import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			BigInteger a= new BigInteger(st.nextToken());
			BigInteger b = new BigInteger(st.nextToken());

			System.out.println("#"+testcase + " "+ a.add(b));
		}
	}
}