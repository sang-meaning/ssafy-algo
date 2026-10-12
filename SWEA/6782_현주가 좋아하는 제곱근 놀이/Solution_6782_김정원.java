package ssafy.swea.kjw;

import java.io.*;

public class Solution_6782_김정원 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			long N = Long.parseLong(br.readLine());
			long answer = 0;

			while (N != 2) {

				long sqrt = (long) Math.sqrt(N);

				// 제곱수이면 제곱근으로 변경
				if (sqrt * sqrt == N) {
					N = sqrt;
					answer++;
				}

				// 제곱수가 아니면 다음 제곱수까지 이동
				else {
					long next = sqrt + 1;
					long nextSquare = next * next;

					// 하나씩 더하지 않고 한번에 계산
					answer += nextSquare - N;
					N = nextSquare;
				}
			}

			System.out.println("#" + test_case + " " + answer);
		}
	}
}