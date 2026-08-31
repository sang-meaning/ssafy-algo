import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 부르트포스
class Solution {
	public static void main(String args[]) throws Exception {
		// Fast Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// Fast Output
		StringBuilder sb = new StringBuilder();
		
		// Execute TC
		// int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			// 변수 저장 및 선언
			int number = Integer.parseInt(br.readLine());
			int[] arr = new int[8];
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<8; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 마지막 사이클까지 스킵
			int mini = Integer.MAX_VALUE;
			for (int i=0; i<8; i++) {
				mini = Math.min(mini, arr[i]/15);
			}
			
			mini = mini == 0 ? mini : mini-1;
			for (int i=0; i<8; i++) {
				arr[i] -= 15 * mini;
			}
			
			// 마지막 사이클 구현
			int i = 0, minus = 1;
			while (arr[i] > 0) {
				arr[i] -= minus;
				
				if (arr[i] <= 0) {
					arr[i] = 0;
					i = (i+1)%8;
					break;
				};
				
				i = (i+1)%8;
				minus = minus == 5 ? 1 : minus+1;
			}
						
			// Add Output
			sb.append('#').append(test_case).append(' ');
			
			for (int idx=i; idx<i+8; idx++) {
				sb.append(arr[idx%8]).append(' ');
			}
			
			sb.append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
}

// 1 2 3 4 5 1 2 3
// 4 5 1 2 3 4 5 1
// 2 3 4 5 1 2 3 4
// 5 1 2 3 4 5 1 2
// 3 4 5 1 2 3 4 5
// 