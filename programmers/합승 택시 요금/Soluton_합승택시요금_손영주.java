package _submission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Soluton {

	// 다익스트라!
	// 합승 해제 지점 정하고
	// 최단으로 이동 후 최단으로 집 가기

	// 지점갯수 n은 3 이상 200 이하인 자연수
	// 다익스트라 소요시간 O((V+E)logV)

	// fares 배열의 각 행은 [c, d, f] 형태
	// c지점과 d지점 사이의 예상 택시요금이 f원

	static ArrayList<int[]>[] graph;
	static int N;

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		N = n;
		graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}

		// fares[i][0]
		for (int[] fare : fares) {
			graph[fare[0] - 1].add(new int[] { fare[1] - 1, fare[2] });
			graph[fare[1] - 1].add(new int[] { fare[0] - 1, fare[2] });
		}
		// 인접리스트 형식

		// 합승할 때 드는 돈
		int[] together = new int[n];
		dijkstra(s - 1, together);

		// 혼자 갈 때 드는 돈
		// i(합승해제지점) to a == a to i
		int[] aloneA = new int[n];
		dijkstra(a - 1, aloneA);

		int[] aloneB = new int[n];
		dijkstra(b - 1, aloneB);

		int minFare = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			if (together[i] == -1) {
				continue; // 도달 불가 지점
			}
			int fare = together[i] + aloneA[i] + aloneB[i];
			minFare = Math.min(minFare, fare);
		}
		return minFare;
	}

	public static void dijkstra(int start, int[] d) {
		// 시작점부터 끝점까지의 최단거리 구하기.
		// start end와 시작점과 다른 점들과의 거리를 기록할 배열

		// 2. 초기화
		Arrays.fill(d, -1);
		d[start] = 0;
		// 3. 시작점과의 거리는 0 , 인접한 정점 거리 기록하되,
		// 값이 작아지는 경우만 기록

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.add(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int distance = cur[1];

			// pq에서 꺼낸 값이 이미 최단 거리 결정된 값이면 버리기
			if (d[node] < distance) {
				continue;
			}

			for (int[] adjNode : graph[node]) {
				int adj = adjNode[0];
				int weight = adjNode[1];

				if (d[adj] == -1 || d[adj] > d[node] + weight) {
					d[adj] = d[node] + weight;
					pq.add(new int[] { adj, d[node] + weight });
				}
			}
		}
		// 다익스트라 완료
	}
}
