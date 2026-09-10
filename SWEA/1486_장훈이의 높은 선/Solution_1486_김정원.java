package ssafy.swea.kjw;

import java.io.*;
import java.util.*;

public class Solution_1486_김정원 {
	static boolean[] selected;
	static int[] H;
	static int N, B, target, min;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			min = 10000 * 20 + 1;
			st = new StringTokenizer(br.readLine());
			H = new int[N]; 
			for (int i = 0; i < N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}
			for (int p = 1; p <= N; p++) {
				// 점원을 1명이서 N명까지 최소를 만족하는 경우를 찾는다
				target = p;
				dfs(0, 0, new int[target]);
			}
			System.out.println(String.format("#%d %d", test_case, min));
		}
	}
	
	static void dfs(int count, int start, int[] clerks) {
		// 점원 선택이 끝나면 
		if (count == target) {
			// 선택된 점원들의 키를 모두 더해 
			// 조건 1. B 이상
			// 조건 2. 최솟값을 찾으므로 최솟값보다 작다면 업데이트
			int sum = 0;
			for (int h : clerks) {
				sum += h;
			}
			if (sum >= B && sum - B < min) min = sum - B;
			return;
		}
		// 몇명의 점원을 선택할지 결정
		for (int p = start; p < N; p++) {
			clerks[count] = H[p];
			dfs(count+1,p+1, clerks);
		}
	}
}
