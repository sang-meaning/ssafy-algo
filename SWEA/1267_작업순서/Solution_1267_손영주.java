package _submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;

		// 칸 알고리즘
		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			int[] indegree = new int[V];
			ArrayList<Integer>[] graph = new ArrayList[V];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;

				if (graph[from] == null) {
					graph[from] = new ArrayList<>();
				}
				graph[from].add(to);
				indegree[to]++;
			} // input

			Queue<Integer> q = new ArrayDeque<Integer>();

			for (int i = 0; i < V; i++) {
				if (indegree[i] == 0) {
					q.offer(i);
				}
			}

			sb.append("#").append(test_case_num).append(" ");

			while (!q.isEmpty()) {
				int cur = q.poll();
				sb.append((cur + 1)).append(" ");
				if (graph[cur] == null) continue;
				for (int ver : graph[cur]) {
					indegree[ver]--;
					if (indegree[ver] == 0) {
						q.offer(ver);
					}
				}
			}

			sb.append("\n");
		}
		System.out.println(sb);
	}
}