import java.io.*;
import java.util.*;

public class Solution_3282_±è¹Î¿ì {
	static int T, N, K;
	static int[] weight;
	static int[] value;
	static int[][] ks;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			weight = new int[N+1];
			value = new int[N+1];
			ks = new int[N+1][K+1];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				weight[i+1] = Integer.parseInt(st.nextToken());
				value[i+1] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= K; j++) {
					if(weight[i] > j) {
						ks[i][j] = ks[i-1][j];
					}
					else {
						ks[i][j] = Math.max(ks[i-1][j], ks[i-1][j-weight[i]]+value[i]);
					}
				}
			}
			
			System.out.printf("#%d %d\n", test_case, ks[N][K]);
			
		}
		
		
	}

}
