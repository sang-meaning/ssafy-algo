package _submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	// 프림
	// 인접행렬 사용.

	// 환경 부담 세율(E)과 각 해저터널 길이(L)의 제곱의 곱(E * L^2)만큼 지불
	// 1≤N≤1,000
	// 각 섬들의 정수인 X좌표, 세 번째 줄에는 각 섬들의 정수인 Y좌표가 주어진다 (0≤X≤1,000,000, 0≤Y≤1,000,000).
	// 환경 부담 세율 실수 E가 주어진다 (0≤E≤1).

	// 희소 그래프: E가 작으므로 간선을 정렬하는 크루스칼이 효율적. 인접리스트 + PQ 프림도 효율적이다. O(E log E)
    // 밀집 그래프: E ≈ V²라 크루스칼의 간선 정렬 비용이 크다. 인접행렬 프림아 O(V^2)로 유리해진다.
	// 밀집 그래프에서 pq 쓰면  O(V² log V)

	static int N;
	static double E;
	static int[] islandsX, islandsY;

	static boolean[] visited; // 비트리인지 아닌지
	static double[] minEdge; // 각각의 정점에서 그룹까지의 가장 가까운 간선

	static double[][] graph; // 인접 그래프

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			N = Integer.parseInt(br.readLine()); // 1번째 줄

			islandsX = new int[N];
			islandsY = new int[N];
			graph = new double[N][N];

			StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 2번째 줄 - x
			for (int i = 0; i < N; i++) {
				islandsX[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " "); // 3번째 줄 - y
			for (int i = 0; i < N; i++) {
				islandsY[i] = Integer.parseInt(st.nextToken());
			}

			E = Double.parseDouble(br.readLine()); // 4번째 줄

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					graph[i][j] = calTex(i, j);
				}
			} // 인접 행렬에 가중치 넣기..

			visited = new boolean[N];
			minEdge = new double[N];

			Arrays.fill(minEdge, Double.MAX_VALUE);
			minEdge[0] = 0; // 처음
			double total = 0.0;

			for (int c = 0; c < N; c++) {
				// step 1 비트리 정점중 최소 간선 비용의 정점 찾기
				double min = Double.MAX_VALUE;
				int minVertex = -1;
				for (int i = 0; i < N; i++) {
					if (!visited[i] && min > minEdge[i]) {
						min = minEdge[i];
						minVertex = i;
					}
				}
				if (minVertex == -1) break;
				visited[minVertex] = true;
				total += min;
				// step 2 선택된 정점의 인접정점들과의 간선비용 비교해서 minEdge 갱신 작업
				for (int i = 0; i < N; i++) {
					if (graph[minVertex][i] < minEdge[i]) {
						minEdge[i] = graph[minVertex][i];
					}
				}
			}

			total = Math.round(total);
			System.out.println("#" + test_case_num + " " + (long) total);
		}
	}

	public static double calTex(int from, int to) {
		long distance = (long) (Math.pow(islandsX[from] - islandsX[to], 2)
				+ Math.pow(islandsY[from] - islandsY[to], 2));
		return distance * E;
	}
}
