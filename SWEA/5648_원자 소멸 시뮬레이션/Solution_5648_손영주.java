package _submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.transform.Templates;

class Atom {
	int x;
	int y;
	int dir;
	int energy;

	public Atom(int x, int y, int dir, int energy) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.energy = energy;
	}
}

class Pair {
	int i;
	int j;
	int time;

	public Pair(int i, int j, int time) {
		super();
		this.i = i;
		this.j = j;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Pair [i=" + i + ", j=" + j + ", time=" + time + "]";
	}

}

public class Solution {

	static Atom[] atoms;
	static int[][] map;
	static ArrayList<Pair> collision;
	static int N;

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우, 수학적 좌표계

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			N = Integer.parseInt(br.readLine());

			atoms = new Atom[N];
			collision = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				int energy = Integer.parseInt(st.nextToken());
				atoms[i] = new Atom(x, y, dir, energy);
			}

			// 충돌 찾기
			// 조합으로 가능한 충돌을 찾아 리스트로 만들기.
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					Atom a = atoms[i];
					Atom b = atoms[j];

					int vecX = b.x - a.x;
					int vecY = b.y - a.y;

					int diffx = dx[a.dir] - dx[b.dir];
					int diffy = dy[a.dir] - dy[b.dir];

					int xt = 0, yt = 0;
					if (diffx == 0 && diffy == 0) {
						continue;
					} else if (diffx != 0 && diffy == 0) { // x축
						xt = vecX * 2 / diffx;
						if (xt > 0 && a.y == b.y) {
							collision.add(new Pair(i, j, xt));
						}
					} else if (diffx == 0 && diffy != 0) { // y축
						yt = vecY * 2 / diffy;
						if (yt > 0 && a.x == b.x) {
							collision.add(new Pair(i, j, yt));
						}
					} else if (diffx != 0 && diffy != 0) { // 대각
						xt = vecX / diffx;
						yt = vecY / diffy;
						if (xt > 0 && yt > 0 && xt == yt) {
							collision.add(new Pair(i, j, xt * 2));
						}
					}
				}
			}

			// sort
			collision.sort((a, b) -> Integer.compare(a.time, b.time));

			// time이 같은 동안은 터진 원자여도 더하기.
			// 이미 터진 원자가 아니면
			// 에너지 더하기
			int totalE = 0;
			boolean[] isBoomed = new boolean[N];
			Set<Integer> tempAtoms = new HashSet<>();
			int preTime = 0;

			// 첫 충돌
			if (!collision.isEmpty()) {
				Pair first = collision.get(0);
				tempAtoms.add(first.i);
				tempAtoms.add(first.j);
				preTime = first.time;
			}

			for (int i = 1; i < collision.size(); i++) {

				Pair cur = collision.get(i);

				// 다른 시간이면 이전 턴의 원자들 다 펑펑 터트리기
				if (preTime != cur.time) {
					for (Integer atomIdx : tempAtoms) {
						if (isBoomed[atomIdx]) continue;
						isBoomed[atomIdx] = true;
						totalE += atoms[atomIdx].energy;
					}
					tempAtoms.clear();
				}
				// 이전 턴 다 처리했으면 지금 거 하기
				if (isBoomed[cur.i] || isBoomed[cur.j]) continue; // 근데 지금 거 죽었으면 넘기기

				tempAtoms.add(cur.i);
				tempAtoms.add(cur.j);

				preTime = cur.time;
			}

			for (Integer atomIdx : tempAtoms) {
				if (isBoomed[atomIdx]) continue;
				isBoomed[atomIdx] = true;
				totalE += atoms[atomIdx].energy;
			} // 마지막에 남은거 하기

			System.out.println("#" + test_case_num + " " + totalE);
		}
	}
}