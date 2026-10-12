package ssafy.swea.kjw;

import java.io.*;
import java.util.*;

public class Solution_7465_김정원 {
	static int N, M, answer;
	static int[] r;
	static Map<Integer, List<Integer>> groups;
	static int[][] task_queue;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
	
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			answer = 0;
			groups = new HashMap<>();
			task_queue = new int[M][];
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int people_a = Integer.parseInt(st.nextToken());
				int people_b = Integer.parseInt(st.nextToken());
				task_queue[m] = new int[] {people_a, people_b};
			}
			excute();
			System.out.println(String.format("#%d %d", test_case, answer));
		}
	}
	
	static void init() {
		r = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			r[i] = i;
			groups.put(i, new ArrayList<>(Arrays.asList(i)));
		}
	}
	
	static void excute() {
		init();
		for (int[] task : task_queue) {
			int a = task[0];
			int b = task[1];
			connect(a, b);
		}
		countGroup();
	}
	
	static void connect(int a, int b) {
		int a_parent = r[a];
		int b_parent = r[b];
		if (a==b || a_parent == b_parent) return;
		for (int child : groups.get(b_parent)) {
			r[child] = a_parent;
		}
		
		groups.get(a_parent).addAll(groups.get(b_parent));
		groups.put(b_parent, new ArrayList<>());
		System.out.print(String.format("%d %d ::", a, b));
		System.out.println(Arrays.toString(r));
		System.out.println(groups);
	}
	
	static void countGroup() {
		System.out.println(groups);
		for (int people = 1; people <= N; people++) {
			if (groups.get(people).size() == 0) continue;
			answer++;
		}
	}
}

