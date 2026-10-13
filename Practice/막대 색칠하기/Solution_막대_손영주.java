package submission;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		// 3cm 막대를 만드는 방법

		// 2cm 막대를 만드는 방법은 저장되어있음
		// i - 1 막대에 1cm 막대를 더해 만드는 방법 (노랑 파랑 중 선택)
		// i - 2 막대에 2cm 막대를 더해 만드는 방법 (빨강 붙임)

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int size = Math.max(3, N + 1);
		int[] cases = new int[size];

		cases[0] = 1; // 0cm 막대 : 아무 방법이 없다는 방법 1
		cases[1] = 2; // 1cm 막대
		// cases[2] = 5; // 2cm 막대부터 일관적 적용 가능

		for (int i = 2; i <= N; i++) {

			int addOne = cases[i - 1] * cases[1];
			int addTwo = cases[i - 2];
			cases[i] = addOne + addTwo;
		}

		System.out.println(cases[N]);
	}
}
