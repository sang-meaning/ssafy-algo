package _submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution{

	public static void main(String[] args) throws NumberFormatException, IOException {
		StreamTokenizer st = new StreamTokenizer(
				new BufferedReader(new InputStreamReader(System.in)));
		// 버퍼드리더 오류로 인한 스트림토크나이저 사용
		
		st.nextToken();
		int T = (int) st.nval;

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			st.nextToken();
			int N = (int) st.nval;

			int[] mountains = new int[N];

			for (int i = 0; i < N; i++) {
				st.nextToken();
				mountains[i] = (int) st.nval;
			}

			// 전략 : 투포인터
			// 증가하지 않을 때까지 p 증가
			// p부터 감소하지 않을 때까지 e 증가

			boolean end = false;

			int cnt = 0;

			int s = 0;
			int e = 0;
			int p = 0;

			while (!end) {
				int i = p;
				while (mountains[i] > mountains[i + 1]) {
					i++;
					if (i + 1 == N) {
						end = true;
						break;
					}
				} // base; // 내리는 중에 끝날 시 카운트
				e = i;
				cnt += (p - s) * (e - p);
				s = i;

				if (end) break; // 끝나있으면 나오기

				while (mountains[i] < mountains[i + 1]) {
					i++;
					if (i + 1 == N) {
						end = true;
						break;
					}
				} // 오르는 중에 끝날 시 카운트 x
				p = i;
			}

			System.out.println("#" + test_case_num + " " + cnt);
		}
	}
}