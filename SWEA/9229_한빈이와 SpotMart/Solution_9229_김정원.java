package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.event.ListSelectionEvent;

public class Solution_9229_김정원 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력 받기
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int test_case = 0; test_case < t; test_case++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int index = 0;
			int max = -1;
			int[] weights = new int[n];
			int sum = 0;
			// 입력을 받아볼까요?
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				weights[index++] = Integer.parseInt(st.nextToken());
			}
			for (int l = 0; l < n; l++) {
				if (weights[l] > m) continue;
				for (int r = l + 1; r < n; r++) {
					// 조건 1. 최대 허용 중량인 m 을 초과하면 패스
					sum = weights[l] + weights[r];
					if (sum > m) continue;
					max = max < sum ? sum : max;
				}
			}
			System.out.println(String.format("#%d %d", test_case + 1, max));
		}
	}
	
}
