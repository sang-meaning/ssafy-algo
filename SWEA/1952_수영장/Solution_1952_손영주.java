package _submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[] price = new int[4];
	static int[] plan = new int[12];

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < price.length; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < plan.length; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			// input

			int[] best = new int[12];
			// 누적 최선 금액.

			for (int i = 0; i < plan.length; i++) {
				if (i >= 1) {
					best[i] = best[i - 1]; // 전달 금액 복사해오기
				}

				int dayPrice = plan[i] * price[0];
				int monthPrice = price[1];
				int threeMonthPrice = price[2];

				best[i] += Math.min(dayPrice, monthPrice); // 최선 비용 더하기
				if (i >= 3) {
					// 3달권 쓰지 않는 최선 비용과..
					// 저저번달에 3달권 썼더라면 비용. 이번달
					best[i] = Math.min(best[i], best[i - 3] + threeMonthPrice);
				} else {
					best[i] = Math.min(best[i], threeMonthPrice);
				}
			}

			int answer = best[11] < price[3] ? best[11] : price[3];

			System.out.println("#" + test_case + " " + answer);
		}
	}
}
