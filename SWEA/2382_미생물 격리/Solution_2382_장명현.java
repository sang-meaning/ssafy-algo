import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// 빡구현
class Solution {

	// TC 내부 변수
	public static int N, M, K, answer;
	public static int[] dx = {0, -1, 1, 0, 0};
	public static int[] dy = {0, 0, 0, -1, 1};
	public static int[][] g;
	public static Map<Integer, List<Integer>> map;
	
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
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			g = new int[K][4];
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				g[i][0] = Integer.parseInt(st.nextToken());
				g[i][1] = Integer.parseInt(st.nextToken());
				g[i][2] = Integer.parseInt(st.nextToken());
				g[i][3] = Integer.parseInt(st.nextToken());				
			}

			// 각 초당 미생물 군집 시뮬레이션 (이동 -> 타노스 -> 합체)
			for (int t=0; t<M; t++) {
				// 미생물 위치 파악 맵
				map = new HashMap<>();
				
				// 각 미생물
				for (int i=0; i<K; i++) {
					if (g[i][2] == 0) continue;
					// 이동
					g[i][0] += dx[g[i][3]];
					g[i][1] += dy[g[i][3]];
					
					// 외곽이면 타노스(방향 변경 & 절반)
					if (g[i][0] == 0 || g[i][0] == N-1 || g[i][1] == 0 || g[i][1] == N-1) {
						if (g[i][3] % 2 == 1) {
							g[i][3]++;
						} else {
							g[i][3]--;
						}
						g[i][2] /= 2;
					}
					
					// 위치 정보 저장
					map.putIfAbsent(g[i][0]*100 + g[i][1], new ArrayList<>());
					map.get(g[i][0]*100 + g[i][1]).add(i);
				}
				
				// 각 위치에서
				for (Map.Entry <Integer, List<Integer>> entry : map.entrySet()) {
					int maxi = 0, suma = 0;
					
					// 미생물 수가 가장 많은 군집을 찾고
					for (int x : entry.getValue()) {
						suma += g[x][2];
						if (maxi < g[x][2]) {
							maxi = g[x][2];
						}
					}
					
					// 미생물 수 병합, 나머지 미생물 군집 무시
					for (int x : entry.getValue()) {
						if (g[x][2] == maxi) {
							g[x][2] = suma;
						} else {
							g[x][2] = 0;
						}
					}
				}
			}
			
			// 생존 군집 전부 합산
			int answer = 0;
			for (int i=0; i<K; i++) {
				answer += g[i][2];
			}
			
			// Add Output
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
}