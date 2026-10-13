package com;

import java.io.*;
import java.util.*;

public class SWEA2383 {
	static int[][] map;
	static int ans;
	static ArrayList<Point> people;
	static ArrayList<Point> stairs;
	static int[][] dist;
	static int[] selected;

	static class Point{
		int r, c, len;

		public Point(int r, int c, int len) {
			super();
			this.r = r;
			this.c = c;
			this.len = len;
		}

	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			people = new ArrayList<>();
			stairs = new ArrayList<>();
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if(map[i][j] == 1) {
						people.add(new Point(i, j, 0));
					}else if(map[i][j] > 1) {
						stairs.add(new Point(i, j, map[i][j]));
					}
				}
			}
			dist = new int[people.size()][2];

			for(int i = 0; i < people.size(); i++) {
				Point p = people.get(i);
				for(int j = 0; j < 2; j++) {
					Point s = stairs.get(j);

					dist[i][j] = Math.abs(p.r - s.r) + Math.abs(p.c - s.c);
				}
			}

			ans = Integer.MAX_VALUE;

			selected = new int[people.size()];
			dfs(0);



			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int depth) {
		if(depth == people.size()) {
			simulate();
			return;
		}

		selected[depth] = 0;
		dfs(depth + 1);

		selected[depth] = 1;
		dfs(depth + 1);
	}
	
	static void simulate(){
		int totalTime = 0;

		for(int s = 0; s < 2; s++) {

			ArrayList<Integer> arrival = new ArrayList<>();

			for(int p = 0; p < people.size(); p++) {

				if(selected[p] == s) {
					arrival.add(dist[p][s]);
				}
			}

			Collections.sort(arrival);

			int[] finish = new int[arrival.size()];

			for(int i = 0; i < arrival.size(); i++) {

				int start = arrival.get(i) + 1;

				if(i >= 3) {
					start = Math.max(start,  finish[i - 3]);
				}

				finish[i] = start + stairs.get(s).len;
			}

			if(!arrival.isEmpty()) {
				totalTime = Math.max(totalTime, finish[finish.length - 1]);
			}
		}

		ans = Math.min(totalTime, ans);
	}

}
