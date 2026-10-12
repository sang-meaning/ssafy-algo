import java.io.BufferedReader;
import java.io.InputStreamReader;
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
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int maxi = -1;
			for (int i=0; i<N-1; i++) {
				if (arr[i] > M) continue;
				for (int j=i+1; j<N; j++) {
					if (arr[i] + arr[j] > M) continue;
					if (arr[i] + arr[j] > maxi) {
						maxi = arr[i] + arr[j];
					}
				}
			}
			
			// Add Output
			sb.append('#').append(test_case).append(' ');
			sb.append(maxi).append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
}