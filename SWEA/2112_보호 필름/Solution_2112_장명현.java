import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static int N, M, K, limit, answer;
	public static int[][] arr, input;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			input = new int[N][M];
			arr = new int[N][M];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<M; j++) {
					input[i][j] = Integer.parseInt(st.nextToken());
					arr[i][j] = input[i][j];
				}
			}
			
			answer = -1;
			for (limit=0; limit<=K; limit++) {
				comb(-1, 0);
				if (answer != -1)
					break;
			}
			
			sb.append("#").append(test_case).append(' ');
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void comb(int last, int count) {
		// 색칠 다하고 검사
		if (count == limit) {
			for (int j=0; j<M; j++) {				
				int cnt = 1;
				int maxi = 1;
				for (int i=1; i<=N; i++) {
					if (i<N && arr[i][j] == arr[i-1][j]) {
						cnt++;
					} else {
						if (maxi < cnt) {
							maxi = cnt;
						}
						cnt = 1;
					}
				}
				
				if (maxi < K)  {
					return;
				}
			}
			
			answer = limit;
			return;
		}
		
		if (last+1 == N) return;
		
		// 0으로 색칠
		for (int j=0; j<M; j++) arr[last+1][j] = 0;
		comb(last+1, count+1);
		for (int j=0; j<M; j++) arr[last+1][j] = input[last+1][j];
		
		// 1로 색칠
		for (int j=0; j<M; j++) arr[last+1][j] = 1;
		comb(last+1, count+1);
		for (int j=0; j<M; j++) arr[last+1][j] = input[last+1][j];
		
		// 색칠 안하고 패스
		comb(last+1, count);
	}
}