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
	public static int[][] magnet, rotate;
	
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
			K = Integer.parseInt(br.readLine());
			
			magnet = new int[4][8];
			for (int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			rotate = new int[K][2];
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				rotate[i][0] = Integer.parseInt(st.nextToken()) - 1;
				rotate[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 매 초마다 자석 돌리기
			for (int t=0; t<K; t++) {
				// 기준 자석으로부터
				int start = rotate[t][0];
				int dir   = rotate[t][1];
				
				// 각 자석이 돌아가는 방향을 살펴보자.
				int[] now = new int[4];
				now[start] = dir;
				
				// 오른쪽 살펴보기
				for (int i=start; i<4-1; i++) {
					if (magnet[i][2] == magnet[i+1][6]) break;
					now[i+1] = now[i] == -1 ? 1 : -1;
				}
				
				// 왼쪽 살펴보기
				for (int i=start; i>0; i--) {
					if (magnet[i][6] == magnet[i-1][2]) break;
					now[i-1] = now[i] == -1 ? 1 : -1;
				}
				
				// 돌아가야 하는 방향에 맞춰서 돌리기
				for (int i=0; i<4; i++) {
					if (now[i] == 1) {         // 시계방향
						int temp = magnet[i][7];
						for (int j=7; j>0; j--) magnet[i][j] = magnet[i][j-1];
						magnet[i][0] = temp;
					} else if (now[i] == -1) { // 반시계 방향
						int temp = magnet[i][0];
						for (int j=0; j<7; j++) magnet[i][j] = magnet[i][j+1]; 
						magnet[i][7] = temp;
					}
				}
			}
			
			// 점수는 첫번째 이들의 합
			answer = magnet[0][0] + (magnet[1][0] * 2) + (magnet[2][0] * 4) + (magnet[3][0] * 8);
			
			// Add Output
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
}