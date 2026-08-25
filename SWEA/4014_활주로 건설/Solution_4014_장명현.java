import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연산자 순열 재귀 완탐
class Solution {
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
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int answer = 0;			
			for (int i=0; i<N; i++) {                             // 가로 검사
				boolean can = true;
				boolean[] visited = new boolean[N];
				for (int j=0; j<N-1; j++) {								          // 왼쪽에서 오른쪽 쓸기
					if (arr[i][j] == arr[i][j+1]) continue; 		      // 다음 칸과 같으면 통과
					if (arr[i][j] >= arr[i][j+1] + 2) {					      // 다음 칸과 2칸 이상 차이나면 불가능
						can = false;
						break;
					}
					if (arr[i][j] < arr[i][j+1]) continue;				    // 반대 경우는 무시
					
					for (int d=2; d<=X; d++) {							          // 다음 칸과 1칸 차이만 났을 때,
						if (j+d >= N || arr[i][j] - arr[i][j+d] != 1) { // 활주로를 넘어가지 않으면서, X칸 뒤까지 전부 1 차이나는지 확인
							can = false;
							break;
						}
					}
					if (!can) break;
					for (int d=1; d<=X; d++) visited[j+d] = true;		  // 경사로 표시
				}
				
				for (int j=N-1; j>0; j--) {								          // 오른쪽에서 왼쪽 쓸기
					if (arr[i][j] == arr[i][j-1]) continue;
					if (arr[i][j] >= arr[i][j-1] + 2) {
						can = false;
						break;
					}
					if (arr[i][j] < arr[i][j-1]) continue;
					
					for (int d=2; d<=X; d++) {							           // 활주로 넘어가지 않으면서, 전부 1차이남과 동시에, 위에서 깔린건 없는지
						if (j-d < 0 || arr[i][j] - arr[i][j-d] != 1 || visited[j-d]) {
							can = false;
							break;
						}
					}
					if (!can) break;
				}
				
				if (can) answer++;
			}
			
			for (int j=0; j<N; j++) {                             // 세로 검사
				boolean can = true;
				boolean[] visited = new boolean[N];
				for (int i=0; i<N-1; i++) {								          // 위에서 아래로 쓸기
					if (arr[i][j] == arr[i+1][j]) continue; 			    // 다음 칸과 같으면 통과
					if (arr[i][j] >= arr[i+1][j] + 2) {					      // 다음 칸과 2칸 이상 차이나면 불가능
						can = false;
						break;
					}
					if (arr[i][j] < arr[i+1][j]) continue;
					
					for (int d=2; d<=X; d++) {							          // 다음 칸과 1칸 차이만 났을 때,
						if (i+d >= N || arr[i][j] - arr[i+d][j] != 1) { // 활주로를 넘어가지 않으면서, X칸 뒤까지 전부 1 차이나는지 확인
							can = false;
							break;
						}
					}
					if (!can) break;
					for (int d=1; d<=X; d++) visited[i+d] = true;		  // 경사로 표시
				}
				
				for (int i=N-1; i>0; i--) {								          // 아래에서 위로 쓸기
					if (arr[i][j] == arr[i-1][j]) continue;
					if (arr[i][j] >= arr[i-1][j] + 2) {
						can = false;
						break;
					}
					if (arr[i][j] < arr[i-1][j]) continue;
					
					for (int d=2; d<=X; d++) {							          // 활주로 넘어가지 않으면서, 전부 1차이남과 동시에, 위에서 깔린건 없는지
						if (i-d < 0 || arr[i][j] - arr[i-d][j] != 1 || visited[i-d]) {
							can = false;
							break;
						}
					}
					if (!can) break;
				}
				
				if (can) answer++;
			}
			
			
			// Add Output
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
}