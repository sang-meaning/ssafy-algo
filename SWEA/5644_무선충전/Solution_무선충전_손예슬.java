package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 무선충전 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			StringTokenizer a_move = new StringTokenizer(br.readLine());
			StringTokenizer b_move = new StringTokenizer(br.readLine());
			
			int[][] aps = new int[a][4];
			for(int i = 0; i<a; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<4; j++) {
					aps[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] a_map = {1, 1};
			int[] b_map = {10, 10};
			int[] dr = {0, -1, 0, 1, 0};
			int[] dc = {0, 0, 1, 0, -1};
			int total = 0;
			for(int i = -1; i < m; i++) {
				if(i>=0) {
					// 이동 후 좌표
					int ad = Integer.parseInt(a_move.nextToken());
					a_map[0] += dr[ad];
					a_map[1] += dc[ad];
					int bd = Integer.parseInt(b_move.nextToken());
					b_map[0] += dr[bd];
					b_map[1] += dc[bd];
				}
				
				// 속하는 ap 있는지
				int[] a_ap_list = new int[a]; 
				int[] b_ap_list = new int[a]; 
				for(int j = 0; j<a; j++) {
					if(isInAp(aps[j], a_map)) a_ap_list[j]++;
					if(isInAp(aps[j], b_map)) b_ap_list[j]++;
				}
				
				// 가능한 경우 조합
				int max = 0;
				
				for(int j = 0; j < a; j++) { // J, R =  BC번호
					for(int r = 0; r< a; r++) { 
						int sum = 0;
						int ap1 = aps[j][3] * a_ap_list[j]; // 성능, 없으면 0
						int ap2 = aps[r][3] * b_ap_list[r]; 
						
						if (j == r && a_ap_list[j] == 1 && b_ap_list[r] == 1) {
							sum = ap1;
						}
						
						else {
							sum = ap1 + ap2;
						}
						
						max = Math.max(sum, max);
					}
				}
				
				total += max;
				System.out.println(total + " " + max);
			}	
			System.out.println("#"+testcase + " " + total);
		}
	}
	
	// 해당 ap의 범주안에 있는지
	public static boolean isInAp(int[] ap, int[] target) {
		int ap_r = ap[1];
		int ap_c = ap[0];
		int ok_dist = ap[2];
		int t_r = target[0];
		int t_c = target[1];
		
		return (Math.abs(ap_r - t_r) + Math.abs(ap_c - t_c) <= ok_dist);
	}
	
	
}