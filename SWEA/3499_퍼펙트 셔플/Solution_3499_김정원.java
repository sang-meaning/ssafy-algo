package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3499_김정원 {
	static String tempAddress = null;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		// 카드 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		// 입력 받은 카드에서 홀수인지 짝수인지 분기 굳이?
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			// 카드 배열을 쫙 넣어놓기
			String[] cards = br.readLine().split(" ");
			// n == 1 이라면 예외처리
			if (N == 1) {
				System.out.println("#" + test_case + " " + cards[0]);
				continue;
			}
			int mid = (N + 1) / 2;
			String[] temp = Arrays.copyOf(cards, N);
			int index = 0;
			// 홀수번째 인덱스 순회 0부터 round(n/2) 까지
			// 2k-1 마다 배열에다가 주소를 넣어주기?
			index = 0;
			for (int k = 0; k < mid; k++) {
				temp[(index++) * 2] = cards[k];
			}
			index = 0;
			// 짝수번째 인덱스 순회 0부터 round(n/2)
			// 2k 마다 배열에다가 절반 잘라서 주소를 넣어주기
			for (int k = mid; k < N; k++) {
				temp[(index++) * 2 + 1] = cards[k];
			}				
			// 카드 섞기가 완료되었으므로 임시 카드들을
			// cards 랑 스왑해준다
			System.out.println("#" + test_case + " " + Arrays.toString(temp).replace("[", "").replace("]", "").replace(",", ""));
			
			// 배열 출력
		}
	}
}
