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

			boolean valid = true;
			for (int i = 1; i <= N; i++) {
				boolean isOperator = tree[i].equals("+") || tree[i].equals("-") || tree[i].equals("*")
						|| tree[i].equals("/");
				if (i * 2 <= N) { // 부모 노드.(왼쪽 자식 있음.)
					if (!isOperator) {
						valid = false;
						break;
					}
					if (i * 2 + 1 > N) { // 우측 노드 없음..
						valid = false;
						break;
					}
				} else {
					if (isOperator) {
						valid = false;
						break;
					}
				}
			}

			int answer = valid ? 1 : 0;

			System.out.println("#" + test_case_num + " " + answer);
		}
	}
}