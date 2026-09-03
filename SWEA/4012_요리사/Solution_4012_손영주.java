package submission;

import java.util.*;
import java.io.*;

public class Solution{

	static final int MAX_SIZE = 16;

	static int[][] table = new int[MAX_SIZE][MAX_SIZE];
	static boolean[] select = new boolean[MAX_SIZE];

	static int minMat = Integer.MAX_VALUE;

	public static void cook(int s, int depth, int N) {
		if (depth == N / 2) { // 뭘 a에 넣을 지 골랐음..
			minMat = Math.min(minMat, calMat(N)); // 갱신.
			return;
		}
		for (int i = s; i < N; i++) { // 0번 재료는 무조건 dishA에 들어감
			select[i] = true;
			cook(i + 1, depth + 1, N);
			select[i] = false;
		}
	}

	public static int calMat(int N) {
		int matA = 0;
		int matB = 0;

		int countA = 0;
		int countB = 0;

		int[] dishA = new int[N / 2]; // 나는 배열이 싫다
		int[] dishB = new int[N / 2]; // select에 따라 dish에 담는다. 예: select 101100, A: 1 3 4, B: 2 5 6
		for (int i = 0; i < N; i++) { // 
			if (select[i]) {
				dishA[countA++] = i;
			} else {
				dishB[countB++] = i;
			}
		}

		for (int i = 0; i < N / 2 - 1; i++) { // 재료 맛 다 더하기.
			for (int j = i + 1; j < N / 2; j++) {
				matA += table[dishA[i]][dishA[j]] + table[dishA[j]][dishA[i]];
				matB += table[dishB[i]][dishB[j]] + table[dishB[j]][dishB[i]];
			}
		}
		return Math.abs(matA - matB);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			int N = Integer.parseInt(br.readLine()); // 식재료 개수.
			Arrays.fill(select, false); // 식재료 선택 초기화.
			minMat = Integer.MAX_VALUE; // 정답 초기화.

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					table[i][j] = Integer.parseInt(st.nextToken()); // table 초기화 따로 안 함.
				}
			}

			select[0] = true;
			// 0번 재료는 무조건 dishA에 들어감
			cook(1, 1, N);

			System.out.println("#" + test_case_num + " " + minMat);
		}
	}
}