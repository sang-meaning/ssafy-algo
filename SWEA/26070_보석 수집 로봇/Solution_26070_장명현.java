import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Greedy

// 입력 조건 중에 "연속된 두 보석(i번과 i+1번)은 같은 행 또는 같은 열에 위치하지 않는다" 존재
// 즉, 모든 보석은 같은 행 또는 같은 열 위에 위치하지 않음
// 이는 로봇이 출발하면 바로 가장자리로 갈 수 있다는 의미
// 또한, 가장자리에는 보석이 나타나지 않으므로
// 가장자리를 빙글빙글 돌다가 해당 보석이 있는 열로 바로 진입 가능
// 이로 인해, 로봇은 중간에 다른 보석과 겹치지 않음.
// 따라서, 로봇은 현재 위치에서 다음 보석의 상대적 위치관계(현재 위치를 원점이라면 다음 보석이 어떤 사분면에 있는지)에 따라
// 회전 횟수와 다음 보석에서의 출발 방향을 바로 계산 가능

// 오른쪽 4개에서 괄호 왼쪽은 꺾을 횟수, 괄호 안은 방향
// 1(오른쪽) : 3(4), 1(2), 2(3) ,3(4)
// 2(아래쪽) : 3(1), 3(1), 1(3), 2(4)
// 3(왼쪽) : 2(1), 3(3), 3(3), 1(4)
// 4(위쪽) : 1(1), 2(3), 3(4), 3(4)

// 예를 들어 첫번째 줄은,
// 현재 점에서 오른쪽을 바라볼 때, 다음 점이 현재 점 기준 1사분면에 위치하면 3번 꺾어야하고,
// 다음 점에 도착하면 위쪽을 본 상태로 출발한다는 의미

// 구현은 현재 방향일때, 몇사분면인지 파악하고 값 더하고, 방향 갱신

class Solution {

	// TC 내부 변수
	public static int N, M, now, dir, answer;
	public static int[] jewel;
	
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
			
			// 보석 위치(jewel), 개수(M) 저장
			M = 0;
			jewel = new int[11];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					int x = Integer.parseInt(st.nextToken());
					if (x != 0) {
						jewel[x] = 10*i+j;
						M = Math.max(M, x);
					}
				}
			}
			
			// 정답 초기화
			answer = 0;
			dir = 1;
			
			// 다음 점에 따라 로봇 회전 횟수 및 출발 방향 바로 갱신
			for (int i=1; i<=M; i++) {
				int nx = jewel[i-1]/10, ny = jewel[i-1]%10;
				int jx = jewel[i]/10,   jy = jewel[i]%10;
				int quad = findQuad(nx, ny, jx, jy);
				
				if (dir == 1) {
					if (quad == 1) { answer += 3; dir = 4; continue; }
					if (quad == 2) { answer += 1; dir = 2; continue; }
					if (quad == 3) { answer += 2; dir = 3; continue; }
					if (quad == 4) { answer += 3; dir = 4; continue; }
				} else if (dir == 2) {
					if (quad == 1) { answer += 3; dir = 1; continue; }
					if (quad == 2) { answer += 3; dir = 1; continue; }
					if (quad == 3) { answer += 1; dir = 3; continue; }
					if (quad == 4) { answer += 2; dir = 4; continue; }
				} else if (dir == 3) {
					if (quad == 1) { answer += 2; dir = 1; continue; }
					if (quad == 2) { answer += 3; dir = 2; continue; }
					if (quad == 3) { answer += 3; dir = 2; continue; }
					if (quad == 4) { answer += 1; dir = 4; continue; }
				} else if (dir == 4) {
					if (quad == 1) { answer += 1; dir = 1; continue; }
					if (quad == 2) { answer += 2; dir = 2; continue; }
					if (quad == 3) { answer += 3; dir = 3; continue; }
					if (quad == 4) { answer += 3; dir = 3; continue; }
				}
			}
			
			// Add Output
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
	
	// 사분면(Quadrant) 판단
	public static int findQuad(int nx, int ny, int jx, int jy) {
		if (nx > jx && ny < jy) return 1;
		if (nx < jx && ny < jy) return 2;
		if (nx < jx && ny > jy) return 3;
		if (nx > jx && ny > jy) return 4;
		return 0;
	}
}