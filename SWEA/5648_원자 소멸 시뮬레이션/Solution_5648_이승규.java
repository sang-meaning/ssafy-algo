package alg_prac;

import java.io.*;
import java.util.*;

class Solution_5648_이승규 {

	static class Atom {
		int x;
		int y;
		int dir;
		int energy;

		Atom(int x, int y, int dir, int energy) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
		}
	}

	// 0: 상, 1: 하, 2: 좌, 3: 우
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static final int MIN = -2000;
	static final int MAX = 2000;
	static final int OFFSET = 2000;
	static final int SIZE = 4001;

	static ArrayList<Atom> atoms;

	// 해당 위치에 존재하는 원자 개수
	static byte[][] map = new byte[SIZE][SIZE];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine());

			atoms = new ArrayList<>();

			for (int i = 0; i < N; i++) {

				st = new StringTokenizer(br.readLine());

				// 0.5 위치에서 충돌하는 경우를 처리하기 위해 2배
				int x = Integer.parseInt(st.nextToken()) * 2;
				int y = Integer.parseInt(st.nextToken()) * 2;
				int dir = Integer.parseInt(st.nextToken());
				int energy = Integer.parseInt(st.nextToken());

				atoms.add(new Atom(x, y, dir, energy));
			}

			int answer = simulation();

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(sb);
	}

	static int simulation() {

		int answer = 0;

		while (atoms.size() >= 2) {

			ArrayList<Atom> movedAtoms = new ArrayList<>(atoms.size());

			// 1. 모든 원자 이동
			for (Atom atom : atoms) {

				atom.x += dx[atom.dir];
				atom.y += dy[atom.dir];

				// 범위를 벗어나면 앞으로 충돌할 일이 없음
				if (atom.x < MIN || atom.x > MAX || atom.y < MIN || atom.y > MAX) {
					continue;
				}

				movedAtoms.add(atom);

				int nx = atom.x + OFFSET;
				int ny = atom.y + OFFSET;

				// 2개 이상인지만 알면 되므로
				if (map[nx][ny] < 2) {
					map[nx][ny]++;
				}
			}

			ArrayList<Atom> nextAtoms = new ArrayList<>(movedAtoms.size());

			// 2. 충돌 확인
			for (Atom atom : movedAtoms) {

				int nx = atom.x + OFFSET;
				int ny = atom.y + OFFSET;

				if (map[nx][ny] >= 2) {
					// 같은 위치에 2개 이상이면 전부 소멸
					answer += atom.energy;
				} else {
					nextAtoms.add(atom);
				}
			}

			// 3. 이번 턴에서 사용했던 map만 초기화
			for (Atom atom : movedAtoms) {

				int nx = atom.x + OFFSET;
				int ny = atom.y + OFFSET;

				map[nx][ny] = 0;
			}

			atoms = nextAtoms;
		}

		return answer;
	}
}