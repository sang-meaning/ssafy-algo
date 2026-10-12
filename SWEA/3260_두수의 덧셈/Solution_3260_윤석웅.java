import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {
	public static StringTokenizer st;
	public static StringBuilder sb;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		int tc = nextInt();
		BigInteger bi1;
		BigInteger bi2;
		for (int i = 0; i< tc;i++) {
			bi1 = new BigInteger(next());
			bi2 = new BigInteger(next());
			
			BigInteger bi = bi1.add(bi2);
			
			sb.append("#"+(i+1) + " ");
			sb.append(bi.toString());
			sb.append("\n");
		}
		System.out.println(sb);
	}
	public static String next() throws IOException{
		if (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine());
		}
		return st.nextToken();
	}
	public static int nextInt() throws IOException{
		return Integer.parseInt(next());
	}
}
