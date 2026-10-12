package _submission;

import java.util.Scanner;

public class Solution {
	static int N, cnt;
//	static boolean[] colArr, mainDiagArr, subDiagArr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			N = sc.nextInt();
			cnt = 0;
			setQueenBit(0, 0, 0, 0);
			System.out.println("#" + test_case_num + " " + cnt);
		}
	}

//	static void setQueen(int row) {
//
//		if (row > N) {
//			cnt++;
//			return;
//		}
//
//		for (int c = 1; c <= N; c++) {
//			if (!isAvailable(row, c))
//				continue;
//			colArr[c] = mainDiagArr[(row - c) + N] = subDiagArr[(row + c)] = true;
//			setQueen(row + 1);
//			colArr[c] = mainDiagArr[(row - c) + N] = subDiagArr[(row + c)] = false;
//		}
//	}
//
//	static boolean isAvailable(int r, int c) {
//		return !colArr[c] && !mainDiagArr[r - c + N] && !subDiagArr[r + c];
//	}

	static void setQueenBit(int row, int col, int main, int sub) {

		// 플래그의 의미
		// col : 열로 인해 영향받고 있음
		// main : 주대각선으로 인해 영향받고 있음
		// sub : 부대각선으로 인해 영향받고 있음
		if (row == N) {
			cnt++;
			return;
		}

		// ~영향받아서 못 가는 칸 & 1111(범위 제한 마스크) -> 범위 내의 갈 수 있는 칸
		int flag = ~(col | main | sub) & ((1 << N) - 1);

		// 만약 가능한 자리가 0개가 아니면
		while (flag != 0) {
			// 비트가 1인 lsb만 남김. 2의 보수 이용. 전부 0이면 0
			int bit = flag & -flag;
			flag -= bit;
			// 우측 이동은 로지컬 쉬프트 사용했음
			setQueenBit(row + 1, col | bit, (main | bit) >>> 1, (sub | bit) << 1);
		}
	}
}
