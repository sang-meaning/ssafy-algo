import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Soultion_7733_이윤찬 {
	static int N;
	static int T;
	static int[][] map;
	static int max;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int count;
	static boolean[][] visited;
	static int sX, sY, answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			max = 0;
			map = new int[N][N];
			answer = 1;
			StringTokenizer st;
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[y][x]);
				}
			}
			for (int i = 1; i <= max; i++) {
				count = 0;
				visited = new boolean[N][N];
				for (int y = 0; y < N; y++) {
					for (int x = 0; x < N; x++) {
						if (map[y][x] > i && !visited[y][x]) {
							sX = x;
							sY = y;
							dfs(sX, sY,i);
							count++;
						}
					}
				}
				answer = Math.max(answer, count);
			}
		System.out.println("#" + tc + " " + answer );
		}
	}

	static void dfs(int startX, int startY , int i ) {

		visited[startY][startX] = true;
		
		for (int d = 0; d < 4; d++) {
			int nX = startX + dx[d];
			int nY = startY + dy[d];
			if (nX < 0 || nY < 0 || nX >= N || nY >= N)
				continue;
			if(visited[nY][nX]) continue;
			
			if(map[nY][nX] <= i) continue;
			
			visited[nY][nX] = true;
			
			
			dfs(nX,nY, i );
		}
	}
}
