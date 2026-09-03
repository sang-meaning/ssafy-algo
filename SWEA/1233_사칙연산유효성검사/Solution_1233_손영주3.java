package submission;

import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 10; // 10개

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			int N = Integer.parseInt(br.readLine()); // 노드개수

			String[] tree = new String[N + 1]; // 트리.

			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				st.nextToken();
				tree[i] = st.nextToken();
			} // 입력.

			int answer = dfs(1, tree) ? 1 : 0;

			System.out.println("#" + test_case_num + " " + answer);
		}
	}

	public static boolean dfs(int cur, String[] tree) {
		boolean isOperator = (tree[cur].equals("+") || tree[cur].equals("-") || tree[cur].equals("*")
				|| tree[cur].equals("/"));

		if (cur * 2 < tree.length) { // 왼쪽 있다.
			if (!dfs(cur * 2, tree))
				return false;
			if (cur * 2 + 1 < tree.length) { // 우측있다.
				if (!dfs(cur * 2 + 1, tree))
					return false;
			} else
				return false;
			return isOperator;
		}
		return isOperator ? false : true;
	}
}