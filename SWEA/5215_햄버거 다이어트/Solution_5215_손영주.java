package _submission;

import java.util.*;
import java.io.*;

public class Solution_5215_손영주 {

	static int N;
	static int calLimit;

	static int[] scores;
	static int[] calories;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			calLimit = Integer.parseInt(st.nextToken());
			scores = new int[N];
			calories = new int[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				scores[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			} // input

			int bestScore = combHam(0, 0, 0);

			System.out.println("#" + test_case_num + " " + bestScore);
		}
	}

	static int combHam(int index, int score, int calSum) {
		if(index == N) {
			return score;
		}
		int select = 0;
		if (calSum + calories[index] <= calLimit) { // 제한 이하면
			// 남은 시도와 현재 시도 중 큰 값을 반환
			select = combHam(index + 1, score + scores[index], calSum + calories[index]);
		}
		int noSelect = combHam(index + 1, score, calSum);
		return Math.max(select, noSelect);
	}
}