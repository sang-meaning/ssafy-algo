package submission;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		// 각 층별 노랑의 개수와 파랑의 경우의 개수를 점화식으로 나타내기
		// 노랑i = 이전 경우의 수
		// 파랑i = 노랑_(i-1)
		// 경우의 수 i = 경우의 수_(i-1) + 경우의 수_(i-2)

		// 뭐야 왜 피보나치지

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int size = Math.max(3, N + 1);
		int[] cases = new int[size];
		cases[1] = 2;
		cases[2] = 3;

		for (int i = 3; i <= N; i++) {
			cases[i] = cases[i - 1] + cases[i - 2];
		}
		System.out.println(cases[N]);
	}
}
