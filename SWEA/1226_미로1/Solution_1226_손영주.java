package submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		final int T = 10;
		final int M = 16;
		int[][] map = new int[M][M];

		int[] dx = { -1, 0, 1, 0 }; // 상 우 하 좌
		int[] dy = { 0, 1, 0, -1 };

		for (int test_case = 1; test_case <= T; test_case++) {

			int x = 0, y = 0;

			br.readLine();

			for (int i = 0; i < M; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j) - '0';
					if (map[i][j] == 2) {
						x = i;
						y = j;
					}
				}
			}
			Queue<int[]> q = new ArrayDeque<>();
			// 뭘 밀어넣지.
			// 위치, 방향?

			for (int dir = 0; dir < 4; dir++) {
				if (map[x + dx[dir]][y + dy[dir]] != 1) {
					q.offer(new int[] { dir, x, y });
				}
			} // 초기 노드.

			boolean find = false;
			while (!q.isEmpty() && !find) {
				int[] info = q.poll(); // 갈림길좌표와, 어디로 출발할지.
				int dir = info[0];
				int left = (dir + 3) % 4;
				int right = (dir + 1) % 4;
				x = info[1];
				y = info[2];

				boolean stop = false;
				while (!stop && !find) { // 갈림길이거나, 막힘길이거나.
					x += dx[dir];
					y += dy[dir];
					
					if (map[x + dx[left]][y + dy[left]] != 1) { // 왼쪽이 뚫려있다.
						stop = true;
						q.offer(new int[] { left, x, y });
					}
					if (map[x + dx[right]][y + dy[right]] != 1) { // 우측이 뚫려있다.
						stop = true;
						q.offer(new int[] { right, x, y });
					}
					if (map[x + dx[dir]][y + dy[dir]] == 1) { // 앞이 막혀있다.
						if (map[x][y] == 3) { // 막혀서 봤더니 출구다.
							find = true;
							break;
						}
						stop = true;
					}else if (stop) { // 또는 앞이 막혀있지 않지만 옆에가 뚫려있다.
						q.offer(new int[] { dir, x, y });
					}
				}
			}
			int answer = find? 1:0;
			System.out.println("#" + test_case + " " + answer);
		}
	}
}
