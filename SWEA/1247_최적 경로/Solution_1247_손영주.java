package _submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	// dfs + pruning

	static int N;
	static int[] office;
	static int[] home;
	static int[][] client;

	static int min;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());

			office = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			home = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };

			client = new int[N][2];
			for (int i = 0; i < N; i++) {
				client[i][0] = Integer.parseInt(st.nextToken());
				client[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 일단 인풋 받기

			min = Integer.MAX_VALUE;
			dfs(office, 0, 0);

			System.out.println("#" + test_case + " " + min);
		}
	}

	static int calDistance(int[] curPos, int[] nextPos) {
		return Math.abs(curPos[0] - nextPos[0]) + Math.abs(curPos[1] - nextPos[1]);
	}

	static void dfs(int[] curPos, int flag, int distance) {
		if (distance >= min) {
			return;
		}

		if (flag == (1 << N) - 1) {
			int temp = calDistance(curPos, home) + distance;
			if (temp < min)
				min = temp;
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((flag & (1 << i)) != 0)
				continue;
			dfs(client[i], flag | (1 << i), calDistance(curPos, client[i]) + distance);
		}
	}
}
