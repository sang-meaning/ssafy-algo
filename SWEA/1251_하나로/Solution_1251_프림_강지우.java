import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution
{
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = Integer.parseInt(br.readLine());
			
			long[] x = new long[N];
			long[] y = new long[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				x[i] = Long.parseLong(st.nextToken());
				
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				y[i] = Long.parseLong(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());
			
			
			boolean[] visited = new boolean[N];
			long[] minEdge = new long[N];
			
			Arrays.fill(minEdge, Long.MAX_VALUE);
			
			minEdge[0] = 0;
			
			long total = 0;
			
			// N개의 섬을 모두 선택
			for (int cnt = 0; cnt < N; cnt++) {
								
				int minVertex = -1;
				long min = Long.MAX_VALUE;
				
				// 아직 방문하지 않은 섬 중 최소 비용 섬 찾기
				for (int i = 0; i < N; i++) {
					if (!visited[i] && minEdge[i] < min) {
						min = minEdge[i];
						minVertex = i;
					}
				}
				
				visited[minVertex] = true;
				total += min;
				
				// 새로 선택한 섬을 기준으로 최소 비용 갱신
				for (int i = 0; i < N; i++) {
					if (visited[i]) continue;
					
					long dx = x[minVertex] - x[i];
					long dy = y[minVertex] - y[i];
					
					long dist = dx*dx + dy*dy;
					
					if (dist < minEdge[i]) {
						minEdge[i] = dist;
					}
				}
				
			}
			
			long answer = Math.round(E * total);
			
			
			sb.append("#").append(test_case).append(" ")
			  .append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
}