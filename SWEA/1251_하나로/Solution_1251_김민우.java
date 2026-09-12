import java.io.*;
import java.util.*;

public class Solution_1251_김민우 {
	static int T, N;
	static double E;
	static double totalCost;
	static double dist;
	
	static int[][] islands;
	static boolean[] selected;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			islands = new int[N][2];
			selected = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				islands[i][0] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				islands[i][1] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			E = Double.parseDouble(st.nextToken());
			
			dist = 0;
			totalCost = 0;
			
			if(N == 2) {
				dist = calDist(0, 1);
				totalCost = Math.pow(dist, 2) * E;
				sb.append("#"+test_case+" "+Math.round(totalCost)+"\n");
				continue;
			}
			
			selected[0] = true;
			mst(1);//mst의 껍데기를 쓴 완탐?~~
			
			sb.append("#"+test_case+" "+Math.round(totalCost)+"\n");
		}//test_case 끝
		System.out.println(sb);
	}//main 끝
	
	public static void mst(int cnt) {
		if(cnt == N) {
			return;
		}
		
		double min = Double.MAX_VALUE;
		int targetidx = 0;
		
		for(int i = 0; i < N; i++) {
			if(!selected[i])
				continue;
			for(int j = 0; j < N; j++) {
				if(i == j || selected[j])
					continue;
				double tmp = calDist(i, j);
				if(tmp < min) {
					targetidx = j;
					min = tmp;
				}
			}
		}
		
		double curCost = E * Math.pow(min,2);
		totalCost += curCost;
		selected[targetidx] = true;
		mst(cnt+1);
	}//애매한 mst??? 끝
	
	public static double calDist(int idx1, int idx2) {
		return Math.sqrt(Math.pow(islands[idx1][0]-islands[idx2][0],2)+Math.pow(islands[idx1][1]-islands[idx2][1],2));
	}
}//solution 끝
