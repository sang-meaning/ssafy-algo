import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int n;
	static int m;
	static int[] parent;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			init(n);

			System.out.print("#" + test_case + " ");
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());

				int state = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (state == 0) {
					union(a, b);
				} else if (state == 1) {
					a = find(a);
					b = find(b);
					
					if(a == b) {
						System.out.print("1");
					}else {
						System.out.print("0");
					}
				}
			}
			System.out.println("");
		}
	}

	static void init(int n) {
		parent = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return false;
		}
		parent[b] = a;
		return true;
	}
}