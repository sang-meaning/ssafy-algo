package _submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			// 물건의 개수 N 과 가방의 부피 K

			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			int[] value = new int[N + 1];
			int[] weight = new int[N + 1];

			// 물건의 정보를 나타내는 부피 Vi 와 가치 Ci
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				weight[i] = Integer.parseInt(st.nextToken());
				value[i] = Integer.parseInt(st.nextToken());
			}

			int[][] K = new int[N + 1][W + 1];

			for (int i = 1; i <= N; i++) {
				for (int w = 1; w <= W; w++) {
					if (weight[i] > w) {
						K[i][w] = K[i - 1][w];
					} else if (weight[i] <= w) {
						// 아래 value[i] + K[i][w - weight[i]]; 로 썼다가
						// 물건 중복으로 넣어서 틀렸었음
						int put = value[i] + K[i - 1][w - weight[i]];
						int notPut = K[i - 1][w];
						K[i][w] = Math.max(put, notPut);
					}
				}
			}
			System.out.println("#" + test_case + " " + K[N][W]);
		}
	}
}
