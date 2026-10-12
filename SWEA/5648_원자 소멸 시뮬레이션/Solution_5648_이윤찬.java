import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution_5648_이윤찬 {
	static class atom {
		int x;
		int y;
		int dir;
		int e;

		atom(int x, int y, int dir, int e) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.e = e;
		}
	}
	static int N = 4001;
	static int[][] map = new int[N][N];
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static ArrayDeque<atom> atomList;

	static int T;
	static int count;
	static int TotalEnergy;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			
			TotalEnergy = 0;
			count = Integer.parseInt(br.readLine());
			atomList = new ArrayDeque<>();
			for (int i = 0; i < count; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken()) + 1000) << 1;
				int y = (Integer.parseInt(st.nextToken()) + 1000) << 1;
				int dir = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());

				map[y][x] = e;
				
				atomList.addLast(new atom(x, y, dir, e));
				
			}
			// 살아있는 원자들을 이동처리

			while (!atomList.isEmpty()) {
				

				atom cur = atomList.pollFirst();
				
				if(map[cur.y][cur.x] != cur.e) {
				
					TotalEnergy += map[cur.y][cur.x];
					
					map[cur.y][cur.x] = 0;
					
					continue;
				}
				
				// 현재의 위치를 0으로 만들고 이동...
				map[cur.y][cur.x] = 0;
				
				int nx = cur.x + dx[cur.dir];
				
				int ny = cur.y + dy[cur.dir];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				cur.x = nx;
				
				cur.y = ny;
				
				map[cur.y][cur.x] += cur.e;

				atomList.addLast(cur);

			}
			
			System.out.println("#"+tc + " " +TotalEnergy);
		}

	}
}
