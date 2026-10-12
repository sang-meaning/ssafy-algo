package swea;

import java.util.Scanner;

public class Solution_1217_이윤찬 {
	static int N;
	static int M;
	static int T;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		

		for (int t = 1; t <= 10; t++) {
			T = sc.nextInt();
			N = sc.nextInt();
			M = sc.nextInt();
			System.out.println("#"+t+" "+ pow(N,1));

		}
	}

	static int pow(int math ,int count) {

		if (count == M) {
			return math;
		}
		return pow( math*N ,count + 1);
	}

}
