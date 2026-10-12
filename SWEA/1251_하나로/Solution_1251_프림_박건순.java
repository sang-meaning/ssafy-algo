import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {

	static int N;
	static double E;
	static Node[] adjList;
	static boolean[] visited;
	static int[] X;
	static int[] Y;
	static long[] minEdge;

	static class Node {
		int to;
		long weight;
		Node next;

		public Node(int to, long weight, Node next) {
			super();
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());

			adjList = new Node[N];
			visited = new boolean[N];
			minEdge = new long[N];

			String[] posX = br.readLine().trim().split("\\s+");
			String[] posY = br.readLine().trim().split("\\s+");

			X = Arrays.stream(posX).mapToInt(Integer::parseInt).toArray();
			Y = Arrays.stream(posY).mapToInt(Integer::parseInt).toArray();
			E = Double.parseDouble(br.readLine());
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					long dx = (long) X[i] - X[j];
					long dy = (long) Y[i] - Y[j];

					long weight = dx * dx + dy * dy;
					adjList[i] = new Node(j, weight, adjList[i]);
					adjList[j] = new Node(i, weight, adjList[j]);
				}
			}

			Arrays.fill(minEdge, Long.MAX_VALUE);
			long result = 0;
			minEdge[0] = 0;

			int c;
			for (c = 0; c < N; c++) {
				long min = Long.MAX_VALUE;
				int minVertex = -1;

				for (int i = 0; i < N; i++) {
					if (!visited[i] && minEdge[i] < min) {
						min = minEdge[i];
						minVertex = i;
					}
				}

				if (minVertex == -1) {
					break;
				}

				result += min;
				visited[minVertex] = true;

				for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
					if (!visited[temp.to] && minEdge[temp.to] > temp.weight) {
						minEdge[temp.to] = temp.weight;
					}
				}
			}

			long answer = Math.round(result * E);

			System.out.println("#" + test_case + " " + answer);
		}
	}
}