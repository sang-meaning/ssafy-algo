import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	
	public static int N, sx, sy, ex, ey, mini;
	public static int[] customer, visited;
	
	public static void main(String args[]) throws Exception {
		// Fast Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// Fast Output
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			// 회사 좌표, 집 좌표 세팅
			st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			
			// 전역 좌표 배열, 방문 배열 세팅
			customer = new int[N];
			visited = new int[N];
			
			// 좌표 저장
			for (int i=0; i<N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				customer[i] = x*1000 + y;
			}
			
			// 최솟값 설정 & 길 탐색
			mini = 20000;
			search(0, -1, 0);
			
			sb.append('#').append(test_case).append(' ').append(mini).append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static void search(int size, int last, int len) {
    // 최소보다 더 길면 탐색할 필요 없음 (이거 킥이네요)
    if (len >= mini) return;
		
    // 전부 돌았으면 집으로 돌아가기
		if (size == N) {
			int lx = customer[last] / 1000;
			int ly = customer[last] % 1000;
			
			// 집가는 길 & 최솟값 갱신
			len += Math.abs(lx-ex) + Math.abs(ly-ey);
			mini = Math.min(mini, len);
			return ;
		}
		
		// 집 탐색
		for (int i=0; i<N; i++) {
			// 방문한 집이면 패스
			if (visited[i] == 1) continue;
			
			// 방문안한 집이면
			int nx = customer[i] / 1000;
			int ny = customer[i] % 1000;
			int bx = last == -1 ? sx : customer[last] / 1000;
			int by = last == -1 ? sy : customer[last] % 1000;

			// 방문 마킹 및 거리 계산해서 다른 곳 탐색
			visited[i] = 1;
			search(size+1, i, len + Math.abs(nx-bx) + Math.abs(ny-by));
			visited[i] = 0;
		}
	}
}