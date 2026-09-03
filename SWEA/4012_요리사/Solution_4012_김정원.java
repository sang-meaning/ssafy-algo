package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.event.ListSelectionEvent;

public class Solution_4012_김정원 {
	static boolean[] selected;
	static int[][] S;
	static int min = 999999999;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력 받기
		int t = Integer.parseInt(br.readLine());
		int index = 0;
		StringTokenizer st;
		for (int test_case = 0; test_case < t; test_case++) {
			int n = Integer.parseInt(br.readLine());
			selected = new boolean[n];
			S = new int[n][n];
			for (int i = 0; i < n; i++) {
				index = 0;
				st = new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()) {
					S[i][index++] = Integer.parseInt(st.nextToken());
				}
			}
			// cook(ingredient, ingredients, 1, n);
			cook(0, 0, n);
			System.out.println(String.format("#%d %d", test_case + 1, min));
			min = 999999999;
		}
	}
	
	static void cook(int ing, int count, int n) {
		// 식재료를 모두 골랐다면 반환
		if (count == n / 2) {
			// 두 음식의 맛을 비교하자!
			min = min > calc(n) ? calc(n) : min;
		}
		for (int i = ing; i < n; i++) {
            // i번 재료를 A 음식에 넣기
            selected[i] = true;

            // 다음 재료 선택
            cook(i + 1, count + 1, n);

            // i번 재료 선택 취소
            selected[i] = false;
		}
	}
	
	static int calc(int n) {
		int A = 0;
		int B = 0;

		// 식재료의 맛 표를 떠올려보자
		// 표에서 A 음식의 식재료만 더하고 B도 마찬가지로 더해줘서
		// A 음식 식재료인지 판별하는것은 두 식재료가 A 의 식재료일때만 A에 넣는다
		// B 는 반대로 하면 된다
		// 두 음식의 맛의 차이를 반환한다
		for (int i = 0; i < n ; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) continue;
				// A 음식은 selected 에서 1로 분류 되어있음
				if (selected[i] && selected[i] == selected[j]) {
					A += S[i][j];
				// B 음식은 selected 에서 0로 분류 되어있음
				} else if (!selected[i] && selected[i] == selected[j]) {
					B += S[i][j];
				}
			}
		}
		return Math.abs(A - B);
	}
}
