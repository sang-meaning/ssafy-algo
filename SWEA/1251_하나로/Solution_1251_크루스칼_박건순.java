import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static class Edge implements Comparable<Edge> {
		int from, to;
		long weight;

		public Edge(int from, int to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	static int N;
	static double E;
	static Edge[] edgeList;
	static int[] parents;
	static int[] X;
	static int[] Y;

	static void makeSets() {
		for (int i = 0; i < N; i++) {
			parents[i] = -1;
		}
	}

	static int find(int a) {
		if (parents[a] < 0) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) {
			return false;
		}
		if (parents[aRoot] <= parents[bRoot]) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		} else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		}

		return true;
	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());

			parents = new int[N];
			edgeList = new Edge[N * (N - 1) / 2];
			String[] posX = br.readLine().trim().split("\\s+");
			String[] posY = br.readLine().trim().split("\\s+");

			X = Arrays.stream(posX).mapToInt(Integer::parseInt).toArray();
			Y = Arrays.stream(posY).mapToInt(Integer::parseInt).toArray();
			E = Double.parseDouble(br.readLine());
			int idx = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					long dx = (long) X[i] - X[j];
					long dy = (long) Y[i] - Y[j];

					long weight = dx * dx + dy * dy;
					edgeList[idx++] = new Edge(i, j, weight);
				}
			}

			Arrays.sort(edgeList);

			makeSets();

			long result = 0;
			int cnt = 0;

			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					result += edge.weight;

					if (++cnt == N - 1) {
						break;
					}
				}
			}

			long answer = Math.round(result * E);

			System.out.println("#" + test_case + " " + answer);
		}
	}
}