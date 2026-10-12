import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static int N, pcnt, maxi, answer;
	public static int[] process;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int[][] map;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			// 프로세서의 위치 저장 배열 process
			// 프로세스와 전선을 1로 표시하는 map 세팅
			pcnt = 0;
			process = new int[12];
			map = new int[N][N];
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						process[pcnt++] = i*100+j;
					}
				}
			}

			// '조합'으로 가능한 전선 연결 개수를 찾아보자
			maxi = -1;
			answer = -1;
			comb(0, 0, 0);
			
			sb.append("#").append(test_case).append(' ');
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void comb(int last, int cnt, int len) {
		// 모든 프로세서의 탐색이 끝났을 때,
		if (last == pcnt) {
			// 카운트 개수가 최대 길이 보다 작으면, 무시
			if (maxi > cnt) return;
			
			// 같으면, 최대 길이로 갱신
			if (maxi == cnt) {
				answer = Math.min(answer, len);
				return;
			}
			
			// 크면, 지금까지 구한 값으로 답 갱신
			maxi = cnt;
			answer = len;
			return;
		}
		
		// 다음 인덱스의 프로세서가 바깥쪽에 위치한다면
		int sx = process[last]/100;
		int sy = process[last]%100;
		if (sx == 0 || sx == N-1 || sy == 0 || sy == N-1) {
			// 개수만 새주고, 별다른 행동 없이 통과
			comb(last+1, cnt+1, len);
			return;
		}
		
		// 안쪽에 위치한다면 가능한 전선을 다 뻗어보기
		// 먼저 전선을 깔지 않고 그냥 통과
		comb(last+1, cnt, len);
		
		// 이제는 전선 깔아보기
		for (int d=0; d<4; d++) {
			boolean can = true;
			
			// 먼저 깔 수 있는지 검사
			int tx = sx + dx[d];
			int ty = sy + dy[d];
			while (tx >= 0 && tx < N && ty >= 0 && ty < N) {
				if (map[tx][ty] == 1 || map[tx][ty] == 2) {
					can = false;
					break;
				}
				tx += dx[d];
				ty += dy[d];
			}
			
			// 깔 수 없으면 해당 방향으로 까는건 고려하지 않음
			// 위에서 깔지 않은 것의 재귀를 태웠으니 여기서는 무시
			if (!can) continue;
			
			// 깔 수 있으면 실제로 맵에 전선 깔기
			tx = sx + dx[d];
			ty = sy + dy[d];
			int add = 0;
			while (tx >= 0 && tx < N && ty >= 0 && ty < N) {
				map[tx][ty] = 2;
				add++;
				tx += dx[d];
				ty += dy[d];
			}
			
			comb(last+1, cnt+1, len+add);
			
			// 한번 고려했으니 이젠 전선 지우기
			tx = sx + dx[d];
			ty = sy + dy[d];
			while (tx >= 0 && tx < N && ty >= 0 && ty < N) {
				map[tx][ty] = 0;
				tx += dx[d];
				ty += dy[d];
			}
		}
	}
}