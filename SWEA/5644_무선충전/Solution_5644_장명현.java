import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	
	public static int[] dx = {0, 0, 1, 0, -1};
	public static int[] dy = {0, -1, 0, 1, 0};
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
			int M = Integer.parseInt(st.nextToken());
			int BC = Integer.parseInt(st.nextToken());
			
			int[] moveA = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] moveB = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			
			List <int[]> bc_list = new ArrayList<>();
			for (int i=0; i<BC; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bc_list.add(new int[] {x, y, c, p});
			}
			
			// 각 초마다 충전할 수 있는 최대 충전을 더하면, 이는 곧 총 충전량 합의 최대
			// 모든 BC 목록을 각자가 순회하며, 해당 BC를 접근 했을 때 충전량을 계산.
			int ax = 1, ay = 1, bx = 10, by = 10, maxi = 0, answer = 0;
			
			// 초기 충전
			for (int i=0; i<BC; i++) {
				for (int j=0; j<BC; j++) {
					int[] bc_a = bc_list.get(i); // x, y, c, p
					int[] bc_b = bc_list.get(j);
					
					int da = Math.abs(bc_a[0] - ax) + Math.abs(bc_a[1] - ay);
					int db = Math.abs(bc_b[0] - bx) + Math.abs(bc_b[1] - by);
					
					if (bc_a[2] >= da && bc_b[2] >= db) {
						if (i == j) {
							maxi = Math.max(maxi, bc_a[3]);
						} else {
							maxi = Math.max(maxi, bc_a[3] + bc_b[3]);
						}
					} else if (bc_a[2] >= da) {
						maxi = Math.max(maxi, bc_a[3]);
					} else if (bc_b[2] >= db) {
						maxi = Math.max(maxi, bc_b[3]);
					}
				}
			}
			answer += maxi;
			
			// 움직이면서 합의 최대를 갱신
			for (int t=0; t<M; t++) {
				maxi = 0;
				
				// 움직이고
				ax += dx[moveA[t]];
				ay += dy[moveA[t]];
				bx += dx[moveB[t]];
				by += dy[moveB[t]];
				
				// 해당 시간의 합 최대 갱신 (초기 충전과 같은 코드)
				for (int i=0; i<BC; i++) {
					for (int j=0; j<BC; j++) {
						int[] bc_a = bc_list.get(i);
						int[] bc_b = bc_list.get(j);
						
						int da = Math.abs(bc_a[0] - ax) + Math.abs(bc_a[1] - ay);
						int db = Math.abs(bc_b[0] - bx) + Math.abs(bc_b[1] - by);
						
						if (bc_a[2] >= da && bc_b[2] >= db) {
							if (i == j) {
								maxi = Math.max(maxi, bc_a[3]);
							} else {
								maxi = Math.max(maxi, bc_a[3] + bc_b[3]);
							}
						} else if (bc_a[2] >= da) {
							maxi = Math.max(maxi, bc_a[3]);
						} else if (bc_b[2] >= db) {
							maxi = Math.max(maxi, bc_b[3]);
						}
						
					}
				}
				
				answer += maxi;
			}

			// Add Output
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
}