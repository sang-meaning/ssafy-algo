package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1952_김정원 {
	static int total = 0;
	static int[] month, price;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			price = new int[4];
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			month = new int[13];
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m <= 12; m++) {
				month[m] = Integer.parseInt(st.nextToken());
			}
			total = price[3];
			dfs(0,0);
			
			System.out.println(String.format("#%d %d", t, total));
		}
	}
	
	static void dfs(int count, int cost) {
		if (count >= 13) {
			total = Math.min(total, cost);
			return;
		}
		
		if (month[count] == 0) {
			dfs(count+1, cost);
			return;
		}
		
		dfs(count+1, cost + price[0] * month[count]);
		dfs(count+1, cost + price[1]);
		dfs(count+3, cost + price[2]);

	}
}
