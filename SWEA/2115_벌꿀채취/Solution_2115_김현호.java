package test;
import java.util.*;

public class swea_2115 {
	static int N;
	static int M;
	static int C;
	static int[][] honey;
	static int[][] select;
	static int[] hon;
	static int min_max;
	static int max;
	static boolean[] ishon;
	public static void collaboration(int start, int depth) {
		if(depth == 2) {
			int[] hon1 = new int[M];
			int[] hon2 = new int[M];
			int num = 0;
			for(int i = 0; i < M; i++) {
				hon1[i] = honey[select[0][0]][select[0][1] + i];
			}
			hon = hon1;
			ishon = new boolean[M];
			min_max = 0;
			col(0,0);
			num += min_max;
			for(int i = 0; i < M; i++) {
				hon2[i] = honey[select[1][0]][select[1][1] + i];
			}
			hon = hon2;
			ishon = new boolean[M];
			min_max = 0;
			col(0, 0);
			num += min_max;
			max = Math.max(max, num);
			return;
		}
		for(int i = start/N; i < N; i++) {
			for(int j = 0; j <= N - M; j++) {
				int index = i * N + j;
				if(index < start) {
					continue;
				}
				if (depth == 1
	                    && select[0][0] == i
	                    && j < select[0][1] + M) {
	                continue;
	            }
				select[depth][0] = i;
				select[depth][1] = j;
				collaboration(index + 1, depth + 1);
			}
		}
	}
	public static void col(int start, int sum) {
		if(sum <= C) {
			int num_sum = 0;
			for(int i = 0; i < hon.length; i++) {
				if(ishon[i]) {
					num_sum +=  hon[i] * hon[i];
				}
			}
			min_max = Math.max(min_max, num_sum);
		}else {
			return;
		}
		for(int i = start; i < hon.length; i++) {
			ishon[i] = true;
			col(i + 1, sum + hon[i]);
			ishon[i] = false;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			C = sc.nextInt();
			honey = new int[N][N];
			select = new int[2][2];
			max = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					honey[i][j] = sc.nextInt();
				}
			}
			collaboration(0, 0);
			System.out.println("#" + test_case + " " + max);
		}
	}
}
