package algo_prac;

import java.io.*;
import java.util.*;

class Solution {

	static class Person {
		int r, c;

		Person(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Stair {
		int r, c, k;

		Stair(int r, int c, int k) {
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}

	static ArrayList<Person> people;
	static Stair[] stairs;
	static int[] selected;
	static int minTime;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			people = new ArrayList<>();
			stairs = new Stair[2];

			int stairIdx = 0;

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {
					int value = Integer.parseInt(st.nextToken());

					if (value == 1) {
						people.add(new Person(r, c));
					} else if (value >= 2) {
						stairs[stairIdx++] = new Stair(r, c, value);
					}
				}
			}

			selected = new int[people.size()];
			minTime = Integer.MAX_VALUE;

			dfs(0);

			sb.append("#").append(tc).append(" ").append(minTime).append("\n");
		}

		System.out.print(sb);
	}

	// 각 사람이 이용할 계단 선택
	static void dfs(int depth) {

		if (depth == people.size()) {
			simulate();
			return;
		}

		selected[depth] = 0;
		dfs(depth + 1);

		selected[depth] = 1;
		dfs(depth + 1);
	}

	// 모든 사람의 계단 선택이 끝난 후 계산
	static void simulate() {

		ArrayList<Integer> list0 = new ArrayList<>();
		ArrayList<Integer> list1 = new ArrayList<>();

		for (int i = 0; i < people.size(); i++) {
			Person p = people.get(i);
			Stair s = stairs[selected[i]];

			int dist = Math.abs(p.r - s.r) + Math.abs(p.c - s.c);

			if (selected[i] == 0) {
				list0.add(dist);
			} else {
				list1.add(dist);
			}
		}

		int time0 = getTime(list0, stairs[0].k);
		int time1 = getTime(list1, stairs[1].k);

		int totalTime = Math.max(time0, time1);

		minTime = Math.min(minTime, totalTime);
	}

	// 한 계단에 배정된 사람들의 완료 시간
	static int getTime(ArrayList<Integer> list, int k) {

		if (list.isEmpty()) {
			return 0;
		}

		Collections.sort(list);

		int[] finish = new int[list.size()];

		for (int i = 0; i < list.size(); i++) {

			int arrival = list.get(i);

			if (i < 3) {
				finish[i] = arrival + 1 + k;
			} else {
				finish[i] = Math.max(arrival + 1, finish[i - 3]) + k;
			}
		}

		return finish[list.size() - 1];
	}
}
