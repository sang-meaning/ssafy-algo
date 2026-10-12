package swea;

import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int result;
	
	public static void main(String[] args) throws Exception{
		
		for(int test_case=1;test_case<=10;test_case++) {
			N = Integer.parseInt(br.readLine());

			result = 1;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());

				st.nextToken();
				int n = st.countTokens();
				if(n == 3) {
				
					String giho = st.nextToken();
					if(giho.equals("*") || giho.equals("-") || giho.equals("/") || giho.equals("+")) {
						
					}else {
						result = 0;
					}
				}
				else if(n == 1) {
					String giho = st.nextToken();
					if(giho.equals("*") || giho.equals("-") || giho.equals("/") || giho.equals("+")) {
						result = 0;
					}
				}
			}
			
			sb.append("#"+test_case+" "+result+"\n");
		}
		System.out.print(sb);
		
	}
}