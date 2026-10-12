package _submission;

import java.util.*;
import java.io.*;

public class Solution {
	
	// 재귀와 가지치기 방법 사용.

	static final int N = 9;
	static final int TOTAL_SCORE = (2 * N * (2 * N + 1) / 2);
	static final int WIN_SCORE = TOTAL_SCORE / 2 + TOTAL_SCORE % 2;
	static int[] factorial = new int[N + 1];

	public static void main(String[] args) throws NumberFormatException, IOException {

		factorial[0] = 1;
		factorial[1] = 1;
		for (int i = 2; i <= N; i++) {
			factorial[i] = factorial[i - 1] * i;
		} // factorial 초기화.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int[][] cards = new int[2][N]; // 0 : 규영, 1 : 인영. // 자료구조 이상하긴 하다. 규영은 고정이고 인영은 선택이라서..
			boolean[] drawByG = new boolean[N * 2 + 1];

			for (int i = 0; i < N; i++) {
				cards[0][i] = Integer.parseInt(st.nextToken());
				drawByG[cards[0][i]] = true;
			} // 규영이의 카드.
			int cnt = 0;
			for (int i = 1; i <= 2 * N; i++) {
				if (!drawByG[i]) {
					cards[1][cnt++] = i;
				}
			} // 인영이의 카드 풀도 확인.
			winCount = 0;
			
			playGame(cards, 0, 0, 0, 0);
			
			int loseCount = factorial[N] - winCount;

			System.out.println("#" + test_case_num + " " + winCount + " " + loseCount);
		}
	}

	static int winCount = 0;

	static void playGame(int[][] cards, int cnt, int flag, int scoreG, int scoreI) {
		// 게임 승리가 결정되는 순간. 누군가의 점수가 절반 이상이면, 나머지 경우는 전부 이기게 된다.
		if (scoreG >= WIN_SCORE) {
			winCount += factorial[N - cnt];
			return;
		} else if (scoreI >= WIN_SCORE) {
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) { // 이미 고른 인덱스면,
				continue;
			}
			// 아래 둘 중 하나만 실행.
			int plueScore = cards[0][cnt] + cards[1][i];
			
			if (cards[0][cnt] > cards[1][i]) { // 규영이 큼.
				playGame(cards, cnt + 1, flag | 1 << i, scoreG + plueScore, scoreI);
			} else {
				playGame(cards, cnt + 1, flag | 1 << i, scoreG, scoreI + plueScore);
			}
		}
	}

}
