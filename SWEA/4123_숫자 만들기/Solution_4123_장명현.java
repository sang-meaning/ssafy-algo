import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연산자 순열 재귀 완탐
class Solution {
	public static int N, maxi, mini;
	public static int[] op_cnt, op, num, visited;
	public static void main(String args[]) throws Exception {
		// Fast Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// Fast Output
		StringBuilder sb = new StringBuilder();
		
		// Execute TC
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			op_cnt = new int[4];
			num = new int[N];
			
			for (int l=0; l<2; l++) {
				st = new StringTokenizer(br.readLine());
				if (l == 0) for (int i=0; i<4; i++) op_cnt[i] = Integer.parseInt(st.nextToken());
				if (l == 1) for (int i=0; i<N; i++)    num[i] = Integer.parseInt(st.nextToken());
			}
			
			// 탐색 전 변수 초기화
			maxi = Integer.MIN_VALUE;
			mini = Integer.MAX_VALUE;
			visited = new int[N];
			
			// 순열 탐색
			perm(1, num[0]);
			
			// Add Output
			sb.append('#').append(test_case).append(' ').append(maxi - mini).append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
	
	public static void perm(int size, int cal) {
		if (size == N) {
			maxi = Math.max(maxi, cal);
			mini = Math.min(mini, cal);
			return;
		}
		
		for (int i=0; i<4; i++) {
			if (op_cnt[i] == 0) continue;
			
			op_cnt[i]--;
			if (i == 0) perm(size+1, cal + num[size]);
			if (i == 1) perm(size+1, cal - num[size]);
			if (i == 2) perm(size+1, cal * num[size]);
			if (i == 3) perm(size+1, cal / num[size]);
			op_cnt[i]++;
		}
	}
}