package swea;

import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N,result;
	static int[][] map;
	static Deque<int[]> q;
	static Deque<int[]> jewel;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1;test_case<=T;test_case++) {
			
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			q = new ArrayDeque<>();
			jewel = new ArrayDeque<>();
			
			int max_value = 0;
			result = 0;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max_value = Math.max(max_value, map[i][j]);
				}
			}
			
			int index = 1;
			while(index < max_value) {
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(map[i][j] == index) {
							jewel.add(new int[] {i,j});
							index++;
							continue;
						}
					}
				}
			}

			if(index == max_value) {
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(map[i][j] == max_value) {
							jewel.add(new int[] {i,j});
						}
					}
				}
			}
			
			move(0, 0, 0); // 좌표 x,y 방향 1,2,3,4  오른쪽,아래,왼쪽,위

			sb.append("#")
			.append(test_case)
			.append(" ")
			.append(result)
			.append("\n");
		}
		System.out.print(sb);
	}

	private static void move(int cx, int cy, int dirct) {
		if(jewel.isEmpty()) {
			return;
		}
		int temp[] = jewel.poll();

		int nx = temp[0];
		int ny = temp[1];
		
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

		//계산 끝
		move(nx,ny,dirct);
	}
	
}