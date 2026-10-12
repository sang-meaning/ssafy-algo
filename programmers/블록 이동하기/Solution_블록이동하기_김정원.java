package ssafy.pro.kjw;

import java.io.*;
import java.util.*;

public class Solution_블록이동하기_김정원 {

	static int[][] map;
	static int N;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	// 로봇의 두 좌표와 현재까지 이동 횟수
	static class Robot {
		int x1, y1;
		int x2, y2;
		int cnt;

		public Robot(int x1, int y1, int x2, int y2, int cnt) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(solution(map));
	}

	public static int solution(int[][] board) {
		map = board;
		N = board.length;
		return bfs();
	}

	static int bfs() {
		Queue<Robot> queue = new ArrayDeque<>();
		// visited[y][x][방향]
		// 방향 0 : 가로
		// 방향 1 : 세로
		boolean[][][] visited = new boolean[N][N][2];
		// 처음에는 (0,0), (1,0) 에 가로로 놓여있음
		queue.offer(new Robot(0, 0, 1, 0, 0));
		visited[0][0][0] = true;
		while (!queue.isEmpty()) {
			Robot robot = queue.poll();
			// 두칸 중 하나라도 도착지점에 있으면 끝
			if (isArrived(robot)) {
				return robot.cnt;
			}
			// 네 방향으로 이동
			move(robot, queue, visited);
			// 가능한 방향으로 회전
			rotate(robot, queue, visited);
		}

		return -1;
	}

	// 네 방향에 대해서 이동 가능한 부분 큐에 넣기
	static void move(Robot robot, Queue<Robot> queue, boolean[][][] visited) {
		for (int d = 0; d < 4; d++) {
			int nx1 = robot.x1 + dx[d];
			int ny1 = robot.y1 + dy[d];
			int nx2 = robot.x2 + dx[d];
			int ny2 = robot.y2 + dy[d];
			// 두 좌표 모두 이동 가능해야함
			if (!isMove(nx1, ny1) || !isMove(nx2, ny2))
				continue;
			addQueue(
					nx1, ny1,
					nx2, ny2,
					robot.cnt + 1,
					queue, visited);
		}
	}

	// 회전 가능한 방향으로 가능한 부분 큐에 넣기
	static void rotate(Robot robot, Queue<Robot> queue, boolean[][][] visited) {
		// 가로로 놓여있는 경우
		if (robot.y1 == robot.y2) {
			// 위, 아래 확인
			for (int d : new int[] { -1, 1 }) {
				int ny = robot.y1 + d;
				// 회전하려면 위 또는 아래 두칸이 모두 비어있어야함
				if (!isMove(robot.x1, ny) || !isMove(robot.x2, ny))
					continue;
				// 첫번째 칸을 축으로 회전
				addQueue(
						robot.x1, robot.y1,
						robot.x1, ny,
						robot.cnt + 1,
						queue, visited);

				// 두번째 칸을 축으로 회전
				addQueue(
						robot.x2, robot.y2,
						robot.x2, ny,
						robot.cnt + 1,
						queue, visited);
			}
		}

		// 세로로 놓여있는 경우
		else {
			// 왼쪽, 오른쪽 확인
			for (int d : new int[] { -1, 1 }) {
				int nx = robot.x1 + d;
				// 회전하려면 왼쪽 또는 오른쪽 두칸이 모두 비어있어야함
				if (!isMove(nx, robot.y1) || !isMove(nx, robot.y2))
					continue;
				// 첫번째 칸을 축으로 회전
				addQueue(
						robot.x1, robot.y1,
						nx, robot.y1,
						robot.cnt + 1,
						queue, visited);

				// 두번째 칸을 축으로 회전
				addQueue(
						robot.x2, robot.y2,
						nx, robot.y2,
						robot.cnt + 1,
						queue, visited);
			}
		}
	}

	static void addQueue(
			int x1, int y1,
			int x2, int y2,
			int cnt,
			Queue<Robot> queue,
			boolean[][][] visited) {
		// visited를 편하게 관리하기 위해서
		// 항상 위쪽 또는 왼쪽 좌표를 첫번째 좌표로 만든다
		if (y1 > y2 || (y1 == y2 && x1 > x2)) {

			int temp = x1;
			x1 = x2;
			x2 = temp;

			temp = y1;
			y1 = y2;
			y2 = temp;
		}

		// 가로면 0 세로면 1
		int direction = (y1 == y2) ? 0 : 1;

		// 이미 똑같은 상태로 방문했으면 안넣음
		if (visited[y1][x1][direction])
			return;
		visited[y1][x1][direction] = true;
		queue.offer(new Robot(
				x1, y1,
				x2, y2,
				cnt));
	}

	static boolean isMove(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		if (map[y][x] == 1)
			return false;
		return true;
	}

	static boolean isArrived(Robot robot) {
		return (robot.x1 == N - 1 && robot.y1 == N - 1)
				|| (robot.x2 == N - 1 && robot.y2 == N - 1);
	}
}