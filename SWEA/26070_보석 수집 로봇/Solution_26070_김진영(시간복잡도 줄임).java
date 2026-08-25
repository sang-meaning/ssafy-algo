package swea;

import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N,result;
	static int[][] map;
	static int[][] pos;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1;test_case<=T;test_case++) {
			
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			pos = new int[11][2];
			
			for (int[] row : pos) {
				Arrays.fill(row, -1);
			}

			int max_value = 0;
			result = 0;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j] > 0) {
						pos[map[i][j]][0] = i;
						pos[map[i][j]][1] = j;
					}
					max_value = Math.max(max_value, map[i][j]);
				}
			}
			
			int cx = 0;
			int cy = 0;
			int dirct = 0; // 0 오른쪽, 1 아래, 2 왼쪽, 3 위
			
			for(int index=1;index<=max_value;index++) {
				int nx = pos[index][0];
				int ny = pos[index][1];
				
				//계산
				if(dirct == 0) {//오른쪽
					if(cx == nx && cy <= ny) { //같은 라인 오른쪽
						result += 0;
					}else if(cx < nx && cy <= ny) { // 아레인데 오른쪽
						result += 1;
						dirct = 1;
					}else if(cx < nx && cy > ny) { // 아래인데 왼쪽
						result += 2;
						dirct = 2;
					}else {
						result += 3; // 이외 부분
						dirct = 3;
						if(ny == N-1) {
							result += 1; // 벽면이면 +1
							dirct = 0;
						}
					}
				
				}else if(dirct == 1) {//아래
					if(cx <= nx && cy == ny) {
						result += 0;
					}else if(cx <= nx && cy > ny) {
						result += 1;
						dirct = 2;
					}else if(cx > nx && cy > ny) {
						result += 2;
						dirct = 3;
					}else {
						result += 3;
						dirct = 0;
						if(nx == N-1) {
							result += 1;
							dirct = 1;
						}
					}
				}else if(dirct == 2) {//왼쪽
					if(cx == nx && cy >= ny) {
						result += 0;
					}else if(cx > nx && cy >= ny) {
						result += 1;
						dirct = 3;
					}else if(cx > nx && cy < ny){
						result += 2;
						dirct = 0;
					}else {
						result += 3;
						dirct = 1;
						if(ny == 0) {
							result += 1;
							dirct = 2;
						}
					}
				}else if(dirct == 3) {//위
					if(cx >= nx && cy == ny) {
						result += 0;
					}else if(cx >= nx && cy < ny) {
						result += 1;
						dirct = 0;
					}else if(cx < nx && cy < ny) {
						result += 2;
						dirct = 1;
					}else {
						result += 3;
						dirct = 2;
						if(nx == 0) {
							result += 1;
							dirct = 3;
						}
					}
				}
				
				cx = nx;
				cy = ny;
			}
			
			sb.append("#")
			.append(test_case)
			.append(" ")
			.append(result)
			.append("\n");
		}
		System.out.print(sb);
	}
}
