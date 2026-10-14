import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution
{
	static class House {
		int x, y;

		public House(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			ArrayList<House> list = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int value = Integer.parseInt(st.nextToken());
					
					if (value == 1) {
						list.add(new House(i, j));
					}
				}
			}
			
			int answer = 0;
			
			// 1. 서비스 영역 크기 K 선택
			for (int K = 1; K <= N + 1; K++) {
				int cost = K * K + (K - 1) * (K - 1);
				
				// 2. 서비스 중심 좌표 선택
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						
						int count = 0;
						
						// 3. 서비스 영역 안에 있는 집 개수 확인
						for (House h : list) {
							
							int dist = Math.abs(i - h.x) + Math.abs(j - h.y);
							
							if (dist < K) count++;
						}
						
						// 4. 손해를 보지 않으면 정답 갱신
						if (count * M >= cost) {
							answer = Math.max(answer, count);
						}
					}
				}
			}
			
			sb.append("#").append(test_case).append(" ")
			  .append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
	
}