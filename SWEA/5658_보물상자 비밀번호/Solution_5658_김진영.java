package swea;

import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N,K,M;
	static long result;
	
	static TreeSet<Long> set;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			result = 0;
			set = new TreeSet<>(Collections.reverseOrder());
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			String temp = br.readLine();
			M = N/4;
			for(int j=0;j<M;j++) {
				for(int i=0;i<N;i+=M) {
					String part = temp.substring(i,i+M);
					
					long value = Long.parseLong(part,16);
					
					set.add(value);
				}
				temp = temp.substring(1) + temp.charAt(0);
			}

			int index = 0;
			for(long t : set) {
				if(index == K-1) {
					result = t;
				}
				index++;				
			}

			
			sb.append("#")
			.append(test_case)
			.append(" ")
			.append(result)
			.append("\n");
		}
		System.out.print(sb);
	}
}