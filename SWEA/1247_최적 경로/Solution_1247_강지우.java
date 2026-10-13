import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static int N, homeX, homeY, answer;
	static int[][] customer;
	static boolean[] visited;
		
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int companyX = Integer.parseInt(st.nextToken());
			int companyY = Integer.parseInt(st.nextToken());
			
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());
			
			customer = new int[N][2];
			visited = new boolean[N];
			answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				customer[i][0] = Integer.parseInt(st.nextToken());
				customer[i][1] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, companyX, companyY, 0);
			
			sb.append("#").append(test_case).append(" ")
			  .append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void dfs(int idx, int x, int y, int total) {
		
		// 가지치기: 이미 최솟값 이상이면 탐색 종료
		if (total >= answer) return;
		
		if (idx == N) {
			int dist = Math.abs(x - homeX) + Math.abs(y - homeY);
			
			answer = Math.min(answer, total + dist);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				int dist = Math.abs(x - customer[i][0]) + Math.abs(y - customer[i][1]);
				dfs(idx + 1, customer[i][0], customer[i][1], total + dist);
				visited[i] = false;
			}
		}
		
	}
}