import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {
		// Fast Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// Fast Output
		StringBuilder sb = new StringBuilder();
		
		// Execute TC
		 int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			// 변수 저장 및 선언
			int N = Integer.parseInt(br.readLine());
			
			String[] arr = new String[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				arr[i] = st.nextToken();
			}
			
						
			// Add Output
			sb.append('#').append(test_case).append(' ');
			
			for (int i=0; i<N/2; i++) {
				sb.append(arr[i]).append(' ');
				sb.append(arr[i+N/2+N%2]).append(' ');
			}
			if (N%2 == 1)
				sb.append(arr[N/2]);
			
			sb.append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
}