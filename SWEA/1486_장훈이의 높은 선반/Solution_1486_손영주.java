package _submission;

import java.util.*;
import java.io.*;

public class Solution{

	static int N, B, S;
	static int min;

	static int[] members;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			members = new int[N];

			S = 0;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				members[i] = Integer.parseInt(st.nextToken());
				S += members[i];
			} // input

			min = Integer.MAX_VALUE;

			selectMem(0, 0, S);

			System.out.println("#" + test_case_num + " " + min);
		}
	}

	static void selectMem(int index, int sum, int remain) {
		if (min == 0) { // 이미 최적을 구해서 끝남
			return;
		}
		
		if (sum + remain < B) { // 가망이 없어서 끝남
			return;
		}
		
		if (sum >= B) { // B가 넘어서 끝남
			min = Math.min(sum - B, min);
			return;
		}

		if (index == N) { // 직원을 다 골라서 끝남.
			return;
		}

		selectMem(index + 1, sum + members[index], remain - members[index]);
		selectMem(index + 1, sum, remain - members[index]);
	}
}