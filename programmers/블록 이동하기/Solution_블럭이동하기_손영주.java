package _submission;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {
	
	// ai assist

	// 상, 하, 좌, 우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public int solution(int[][] board) {
		int N = board.length;

		// {r1, c1, r2, c2, time}
		Queue<int[]> q = new ArrayDeque<>();

		// 두 칸의 위치를 이용해 방문 상태를 저장합니다.
		Set<String> visited = new HashSet<>();

		q.offer(new int[] { 0, 0, 0, 1, 0 });
		visited.add(makeKey(0, 0, 0, 1));

		while (!q.isEmpty()) {
			int[] pos = q.poll();

			int r1 = pos[0];
			int c1 = pos[1];
			int r2 = pos[2];
			int c2 = pos[3];
			int time = pos[4];

			// 로봇의 두 칸 중 하나라도 목적지에 도착하면 종료합니다.
			if ((r1 == N - 1 && c1 == N - 1) || (r2 == N - 1 && c2 == N - 1)) {
				return time;
			}

			// 상하좌우 이동
			for (int dir = 0; dir < 4; dir++) {
				int nr1 = r1 + dr[dir];
				int nc1 = c1 + dc[dir];

				int nr2 = r2 + dr[dir];
				int nc2 = c2 + dc[dir];

				if (canMove(board, nr1, nc1, nr2, nc2)) {

					String key = makeKey(nr1, nc1, nr2, nc2);

					if (!visited.contains(key)) {
						visited.add(key);

						q.offer(new int[] { nr1, nc1, nr2, nc2, time + 1 });
					}
				}
			}

			// 현재 로봇이 가로 방향인 경우
			if (r1 == r2) {

				// 위쪽(-1), 아래쪽(+1)으로 회전할 수 있는지 확인
				for (int d : new int[] { -1, 1 }) {

					int nr1 = r1 + d;
					int nr2 = r2 + d;

					// 회전하려면 위/아래의 두 칸이 모두 비어 있어야
					if (inRange(nr1, c1, N) && inRange(nr2, c2, N) && board[nr1][c1] == 0 && board[nr2][c2] == 0) {

						// r1, c1을 축으로 회전
						addState(q, visited, r1, c1, nr1, c1, time + 1);

						// r2, c2를 축으로 회전
						addState(q, visited, r2, c2, nr2, c2, time + 1);
					}
				}
			}

			// 현재 로봇이 세로 방향인 경우
			else {

				// 왼쪽(-1), 오른쪽(+1)으로 회전할 수 있는지 확인
				for (int d : new int[] { -1, 1 }) {

					int nc1 = c1 + d;
					int nc2 = c2 + d;

					// 회전하려면 왼쪽/오른쪽의 두 칸이 모두 비어 있어야 합니다.
					if (inRange(r1, nc1, N) && inRange(r2, nc2, N) && board[r1][nc1] == 0 && board[r2][nc2] == 0) {

						// r1, c1을 축으로 회전합니다.
						addState(q, visited, r1, c1, r1, nc1, time + 1);

						// r2, c2를 축으로 회전합니다.
						addState(q, visited, r2, c2, r2, nc2, time + 1);
					}
				}
			}
		}

		return -1;
	}

	// 두 위치가 모두 범위 안이고 벽이 아닌지 검사합니다.
	private boolean canMove(int[][] board, int r1, int c1, int r2, int c2) {

		int N = board.length;

		if (!inRange(r1, c1, N) || !inRange(r2, c2, N)) {
			return false;
		}

		return board[r1][c1] == 0 && board[r2][c2] == 0;
	}

	// 한 칸이 보드 내부인지 검사
	private boolean inRange(int r, int c, int N) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

	// 방문하지 않은 상태만 큐에 넣습니다.
	private void addState(Queue<int[]> q, Set<String> visited, int r1, int c1, int r2, int c2, int time) {

		String key = makeKey(r1, c1, r2, c2);

		if (!visited.contains(key)) {
			visited.add(key);

			q.offer(new int[] { r1, c1, r2, c2, time });
		}
	}

	// 두 로봇 칸의 순서가 뒤집혀도 같은 상태가 되도록 정규화합니다.
	private String makeKey(int r1, int c1, int r2, int c2) {

		// 첫 번째 좌표가 사전순으로 뒤에 있다면 두 좌표를 뒤집습니다.
		if (r1 > r2 || (r1 == r2 && c1 > c2)) {
			return r2 + "," + c2 + "," + r1 + "," + c1;
		}

		return r1 + "," + c1 + "," + r2 + "," + c2;
	}
}
