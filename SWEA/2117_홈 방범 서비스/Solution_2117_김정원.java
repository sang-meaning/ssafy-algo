package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2117_김정원 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] city = new int[N][N];
 			for (int y = 0; y < N; y++) {
 				st = new StringTokenizer(br.readLine());
 				for (int x = 0; x < N; x++) {
 					city[y][x] = Integer.parseInt(st.nextToken());
 				}
			}
 			int answer = -1;
 			for (int k = N + 2; k >= 1; k--) {
 				int cost = k * k + (k - 1) * (k - 1);
 				for (int y = 0; y < N; y++) {
 					for (int x = 0; x < N; x++) {
 						int count = getHomes(x, y, k, N, city);
 						int profit = count * M;
 						if (profit < cost) continue;
 						// System.out.println(String.format("profit:%d cost:%d N:%d", profit, cost, N));
 						// System.out.println(String.format("x:%d y:%d count:%d", x, y, count));
 						answer = answer < count ? count : answer;
 					}
 				} 
 			}
 			System.out.println(String.format("#%d %d", t, answer));
		}
	}
	
	static int getHomes(int tx, int ty, int k, int N, int[][] city) {
	    int count = 0;

	    for (int y = ty - k; y < ty + k; y++) {
	        for (int x = tx - k; x < tx + k; x++) {
	            if (x < 0 || x >= N || y < 0 || y >= N) continue;
	            if (Math.abs(tx - x) + Math.abs(ty - y) >= k) continue;

	            if (city[y][x] == 1) count++;
	        }
	    }

	    return count;
	}
}
