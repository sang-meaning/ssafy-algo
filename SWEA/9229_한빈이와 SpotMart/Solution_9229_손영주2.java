package submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	// 정렬 후 투포인터

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] snacks = new int[N];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}

			int answer = -1;
			
			Arrays.sort(snacks);
			int a = 0;
			int b = N-1;
			while(a<b) {
				int sum = snacks[a] + snacks[b];
				if(sum == M) {
					answer = M;
					break;
				}else if(sum > M) {
					b--;
				}else { // sum < M
					answer = Math.max(answer, sum);
					a++;
				}
			}

			System.out.println("#" + test_case_num + " " + answer);
		}
	}
}
