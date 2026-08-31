package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_3260_손영주 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(br.readLine());

		// int 불가..

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line, " ");

			String A = st.nextToken();
			String B = st.nextToken();

			int carry = 0; // 처음 캐리는 0
			String answer = "";

			for (int i = 0; i < 101; i++) {
				int a = 0;
				int b = 0;

				if (i >= A.length() && i >= B.length()) {
					if (carry == 1) {
						answer = "1" + answer;
					}
					break;
				}

				if (i < A.length()) {
					a = A.charAt(A.length() - 1 - i) - '0';
				}
				if (i < B.length()) {
					b = B.charAt(B.length() - 1 - i) - '0';
				}

				int sum = a + b + carry;
				carry = (sum > 9) ? 1 : 0;
				answer = "" + (sum % 10) + answer;
			}

			System.out.println("#" + test_case_num + " " + answer);

		}

	}

}
