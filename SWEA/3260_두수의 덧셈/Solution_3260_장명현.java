import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 부르트포스
class Solution {

	// TC 내부 변수
	public static int carry;
	public static String a, b;
	public static int[] answer;
	
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
			st = new StringTokenizer(br.readLine());
			a = st.nextToken();
			b = st.nextToken();

			// 패딩
			int msize = Math.max(a.length(), b.length());
			for (int i=a.length(); i<msize; i++) a = "0" + a;
			for (int i=b.length(); i<msize; i++) b = "0" + b;
			
			// 캐리 구현
			carry = 0;
			answer = new int[msize];
			for (int i=msize-1; i>=0; i--) {
				int x = a.charAt(i) - '0';
				int y = b.charAt(i) - '0';
				
				carry += (x+y);
				if (carry >= 10) {
					answer[i] = carry%10;
					carry = 1;
				} else {
					answer[i] = carry;
					carry = 0;
				}
			}
						
			// Add Output
			sb.append('#').append(test_case).append(' ');
			
			if (carry == 1) sb.append("1");
			for (int i=0; i<msize; i++) sb.append(answer[i]);
			
			sb.append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
}