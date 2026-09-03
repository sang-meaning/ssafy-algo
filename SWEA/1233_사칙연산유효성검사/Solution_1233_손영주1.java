package submission;

import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 10; // 10개

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			int answer = 1; // true 상태.

			int N = Integer.parseInt(br.readLine()); // 노드개수

			for (int i = 0; i < N; i++) {

				StringTokenizer st = new StringTokenizer(br.readLine(), " ");

				String index = st.nextToken(); // 사용하지 않음.
				char node = st.nextToken().charAt(0);
				boolean isOperator = (node == '*' || node == '-' || node == '+' || node == '/');
				if (st.hasMoreTokens()) { // 자식노드가 있다면 사칙연산이어야 하고, 없으면 숫자여야 함.
					if(!isOperator) {
						answer = 0;
					}
				}else {
					if(isOperator) {
						answer = 0;
					}
				}
			}
			System.out.println("#" + test_case_num + " " + answer);
		}
	}
}