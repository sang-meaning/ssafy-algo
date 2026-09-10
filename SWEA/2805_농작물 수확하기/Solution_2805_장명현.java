import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			int[][] arr = new int[N][N];
			for (int i=0; i<N; i++) {
				String s = br.readLine();
				for (int j=0; j<N; j++) {
					arr[i][j] = s.charAt(j) - '0';
				}
			}
			
			int answer = 0;
			for (int i=0; i<N/2; i++) {
				for (int j=N/2-i; j<=N/2+i; j++) {
					answer += arr[i][j] + arr[N-1-i][j];
				}
			}
			for (int j=0; j<N; j++) answer += arr[N/2][j];

			sb.append("#").append(test_case).append(' ');
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}