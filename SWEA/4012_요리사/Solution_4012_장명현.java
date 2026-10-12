import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	public static int N, answer;
	public static int[] sel;
	public static int[][] arr;
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
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			sel = new int[N/2];
			answer = Integer.MAX_VALUE;
			
			comb(0, -1);
			
			// Add Output
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
	
	public static void comb(int size, int last) {
		if (size == N/2) {
			boolean[] selected = new boolean[N];
			for (int x: sel) selected[x] = true;
			
			int a = 0, b = 0;
			for (int i=0; i<N; i++) {
				for (int j=i+1; j<N; j++) {
					if (selected[i] && selected[j]) a += arr[i][j] + arr[j][i];
					if (!selected[i] && !selected[j]) b += arr[i][j] + arr[j][i];
				}
			}
			
			answer = Math.min(answer, Math.abs(a - b));
			return;
		}
		
		for (int i=last+1; i<N; i++) {
			sel[size] = i;
			comb(size+1, i);
		}
	}
}
