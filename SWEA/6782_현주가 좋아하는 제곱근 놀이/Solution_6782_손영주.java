package _submission;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	// ai assist

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			long N = Long.parseLong(br.readLine());
			long count = 0;

			while (N != 2) {
				long sqrt = (long) Math.sqrt(N);

				if (sqrt * sqrt == N) {
					N = sqrt;
					count++;
				} else {
					long next = sqrt + 1;
					long nextSquare = next * next;

					count += nextSquare - N + 1;
					N = next;
				}
			}

			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}

		System.out.print(sb);
	}
}
