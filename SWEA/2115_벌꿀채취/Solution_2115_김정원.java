package ssafy.swea.kjw;

import java.io.*;
import java.util.*;

public class Solution_2115_김정원 {

	static int[][] honey;
	static int N, M, C;
	static int answer;
	static int maxProfit;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			honey = new int[N][N];
			answer = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			harvestHoney();

			System.out.println("#" + test_case + " " + answer);
		}
	}

	static void harvestHoney() {

		// 일꾼 A가 채밀할 벌통을 선택
		for (int w1Row = 0; w1Row < N; w1Row++) {

			for (int w1Col = 0; w1Col + M <= N; w1Col++) {

				// 현재 선택한 M개의 벌통에서 얻을 수 있는 최대 수익
				int profit1 = getMaxProfit(w1Row, w1Col);

				// 일꾼 B가 채밀할 벌통을 선택
				for (int w2Row = w1Row; w2Row < N; w2Row++) {

					for (int w2Col = 0; w2Col + M <= N; w2Col++) {

						// 같은 행이면 벌통이 겹치는지 확인
						if (w1Row == w2Row) {

							// B의 시작점이 A의 끝보다 앞이면 겹칠 수 있음
							if (w2Col < w1Col + M) {
								continue;
							}
						}

						int profit2 = getMaxProfit(w2Row, w2Col);

						answer = Math.max(answer, profit1 + profit2);
					}
				}
			}
		}
	}

	// 선택한 M개의 벌통에서 C를 넘지 않으면서
	// 얻을 수 있는 최대 수익을 구한다
	static int getMaxProfit(int row, int start) {

		maxProfit = 0;

		selectHoneycomb(row, start, 0, 0, 0);

		return maxProfit;
	}

	// M개의 벌통에서 어떤 꿀을 채밀할지 선택
	// 꿀은 전부 선택할 필요가 없으므로 부분집합으로 확인
	static void selectHoneycomb(int row, int start, int idx, int honeySum, int profit) {

		// C를 넘어가면 더 볼 필요 없음
		if (honeySum > C) {
			return;
		}

		// M개를 전부 확인했으면 최대 수익 비교
		if (idx == M) {
			maxProfit = Math.max(maxProfit, profit);
			return;
		}

		int currentHoney = honey[row][start + idx];

		// 현재 벌통의 꿀을 채밀한다
		selectHoneycomb(
				row,
				start,
				idx + 1,
				honeySum + currentHoney,
				profit + currentHoney * currentHoney
		);

		// 현재 벌통의 꿀을 채밀하지 않는다
		selectHoneycomb(
				row,
				start,
				idx + 1,
				honeySum,
				profit
		);
	}
}