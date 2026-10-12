package ssafy.swea.kjw;

import java.io.*;
import java.util.*;

public class Solution_5215_김정원 {
	static int[][] ingredients;
	static int[] buger;
	static int N;
	static int L;
	static int taste = 0;
	public static void main(String[] args) throws Exception {
		// 햄버거의 재료가 맛과 칼로리가 주어진다
		// 햄버거를 만들 때 칼로리는 최대치를 초과하면 안된다
		// 맛은 최대한 높이고 갯수의 제한은 없다
		
		// 완전 탐색부터 생각하면
		// 가장 맛이 크려면 당연히 맛이 양수이므로 갯수가 많아야한다!
		// N개 부터 칼로리 조건을 만족하는것을 찾기
		// N 개의 가능한 조합을 모두 -> N - 1 개의 조합 확인,
		// 1 -> N -> N*(N-1) / 2 -> N * (N-1) * (N-2) / 3 * 2 -> ...  
		// 순으로 연산해야하고 최댓값인 20이므로 1 + 20/1 + 20*19/2 + ... + 20!/20! 으로 연산이 이루어져야 한다?
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
	
		for (int test_case = 1; test_case <= T; test_case++) {
			taste = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			ingredients = new int[N][3];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ingredients[i][0] = Integer.parseInt(st.nextToken());
				ingredients[i][1] = Integer.parseInt(st.nextToken());
				ingredients[i][2] = 0; // 재료를 선택했는지 여부를 담음
			}
			// 
			for (int r = N; r > 0; r--) {
				buger = new int[r];
				cook(0, 0, r);
			}
			System.out.println(String.format("#%d %d", test_case, taste));
		}
		
	}
	
	static void cook(int count, int start, int maxCount) {
		// 햄버거의 재료를 다 모이면
		if (count == maxCount) {
			// System.out.println(Arrays.toString(buger));
			int tasteSum = 0;
			int calSum = 0; 
			for (int t : buger) {
				tasteSum += ingredients[t][0];
				calSum += ingredients[t][1];
				if (calSum > L) return;
				taste = taste <= tasteSum ? tasteSum : taste;
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			buger[count] = i;
			cook(count+1,i+1,maxCount);
		}
	}
}
