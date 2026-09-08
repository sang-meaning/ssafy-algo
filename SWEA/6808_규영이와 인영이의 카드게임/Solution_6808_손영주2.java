package _submission;

import java.util.*;
import java.io.*;

public class Solution {
	// DP를 활용해보자
	// DP의 아이디어 : 같은 상태일 경우 미래가 결정.
	// 같은 상태에 다시 들어왔을 때, 미래에서 계산할 결과도 같은가

	static final int N = 9;
	static final int TOTAL_SCORE = (2 * N * (2 * N + 1) / 2); // 172
	static final int WIN_SCORE = TOTAL_SCORE / 2 + TOTAL_SCORE % 2; // 86
	static int[] factorial = new int[N + 1];

	static int[][] memo = new int[TOTAL_SCORE + 1][1 << N]; // 점수와 플래그.

	static int[] gyuCards = new int[N];
	static int[] inCards = new int[N];

	public static void main(String[] args) throws NumberFormatException, IOException {

		factorial[0] = 1;
		factorial[1] = 1;
		for (int i = 2; i <= N; i++) {
			factorial[i] = factorial[i - 1] * i;
		} // factorial 초기화.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			for (int[] row : memo) {
				Arrays.fill(row, -1);
			} // 메모 초기화.

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			boolean[] idGyuCard = new boolean[N * 2 + 1];

			for (int i = 0; i < N; i++) {
				gyuCards[i] = Integer.parseInt(st.nextToken());
				idGyuCard[gyuCards[i]] = true;
			} // 규영이의 카드.
			int cnt = 0;
			for (int i = 1; i <= 2 * N; i++) {
				if (!idGyuCard[i]) {
					inCards[cnt++] = i;
				}
			} // 인영이의 카드 풀도 확인.

			int winCount = playGame(0, 0, 0, 0);

			int loseCount = factorial[N] - winCount;

			System.out.println("#" + test_case_num + " " + winCount + " " + loseCount);
		}
	}

	// 각 경우에서 이긴 숫자를 반환.
	static int playGame(int cnt, int flag, int gyuScore, int inScore) {

		if (memo[gyuScore][flag] != -1) {
			return memo[gyuScore][flag]; // 점수와 플래그가 같으면 미래가 같다.
		}

		// 게임 승리가 결정되는 순간. 누군가의 점수가 절반 이상이면, 나머지 경우는 전부 이기게 된다.
		if (gyuScore >= WIN_SCORE) {
			return factorial[N - cnt];
		} else if (inScore >= WIN_SCORE) {
			return 0;
		}

		int winCnt = 0;
		// 각 경우의 이긴 숫자를 다 더해서 리턴하자!
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) { // 이미 고른 인덱스면,
				continue;
			}
			// 아래 둘 중 하나만 실행.
			int score = gyuCards[cnt] + inCards[i];
			int nFlag = flag | 1 << i;

			if (gyuCards[cnt] > inCards[i]) { // 규영이 큼.
				winCnt += playGame(cnt + 1, nFlag, gyuScore + score, inScore);
			} else {
				winCnt += playGame(cnt + 1, nFlag, gyuScore, inScore + score);
			}
		}
		memo[gyuScore][flag] = winCnt;
		return winCnt;
	}
}
