package ssafy.swea.kjw;

import java.io.*;
import java.util.*;

public class Solution_3289_김정원 {
	static int N, M;
	static int[] parent;
	static int[][] task_queue;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
	
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			task_queue = new int[M][];
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int group_a = Integer.parseInt(st.nextToken());
				int group_b = Integer.parseInt(st.nextToken());
				task_queue[m] = new int[] {cmd, group_a, group_b};
			}
			System.out.print(String.format("#%d ", test_case));
			excute();
			System.out.println();
		}
	}
	
	static void init() {
		parent = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	static void excute() {
		init();
		for (int[] task : task_queue) {
			int cmd = task[0];
			int a = parent[task[1]];
			int b = parent[task[2]];
			switch (cmd) {
			case 0:
				union(a,b);
				break;
			case 1:
				hasCommon(a,b);
				break;
			default:
				break;
			}
		}
	}
	
	static int find(int x) {
		if (parent[x] == x) return x;

		return parent[x] = find(parent[x]);
	}
	
	static void union(int p, int c) {
		int rootA = find(p);
		int rootB = find(c);

		if (rootA == rootB) return;

		parent[rootB] = rootA;
	}
	
	static void hasCommon(int group_a, int group_b) {
		System.out.print(find(group_a) == find(group_b) ? 1 : 0);
	}
}

