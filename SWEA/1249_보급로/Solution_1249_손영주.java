package _submission;

import java.util.*;
import java.io.*;

// 다익스트라

// 다익스트라적 사고하기.
// 현 노드에서 다음 노드로 가는 최소 경로 찾기

class Pos implements Comparable<Pos> {
	int r;
	int c;
	int dis;

	public Pos(int r, int c, int dis) {
		this.r = r;
		this.c = c;
		this.dis = dis;
	}

	@Override
	public int compareTo(Pos o) {
		return Integer.compare(this.dis, o.dis);
	}
}

public class Solution {

	static int N;
	static int[][] map;
	static int[][] memo;

	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String args[]) throws Exception {

		// BufferedReader br = new BufferedReader(new InputStreamReader(new
		// FileInputStream("input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}

			// 인풋

			memo = new int[N][N];
			for (int[] row : memo) {
				Arrays.fill(row, -1);
			}

			// 격자 그래프
			// pq?
			int answer = 0;
			PriorityQueue<Pos> pq = new PriorityQueue<>();
			pq.add(new Pos(0, 0, 0));

			while (!pq.isEmpty()) {
				// 최소값의 위치
				Pos cur = pq.poll();

				if (cur.r == N - 1 && cur.c == N - 1) {
					answer = cur.dis;
					break;
				}
				// 그곳에서 갈 수 있는 4방의 연결점
				for (int dir = 0; dir < 4; dir++) {
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];

					if (nr >= N || nr < 0 || nc >= N || nc < 0)
						continue;

					// 방문한 적이 없거나, 새 최소값보다 크면
					if (memo[nr][nc] == -1 || memo[nr][nc] > cur.dis + map[nr][nc]) {
						memo[nr][nc] = cur.dis + map[nr][nc];
						// 갱신되었으므로
						pq.add(new Pos(nr, nc, memo[nr][nc]));
					}
				}
			}

			System.out.println("#" + test_case + " " + answer);
		}
	}
}
