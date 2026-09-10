package test;

import java.util.*;
public class 등산로 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[][] arr = new int[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int len = 1; //현재 시작점에서의 길이
				
				int current_x = i;
				int current_y = j;
				while(true) {
					int min = Integer.MAX_VALUE;
					int min_x = 0;
					int min_y = 0;
					boolean bl = false;
					for(int k=0; k<4; k++) {
						int nx = current_x+dx[k];
						int ny = current_y+dy[k];
						
						if(nx>=0 && nx<n && ny>=0 && ny<n) {
							if(arr[current_x][current_y]>arr[nx][ny]) {
								bl = true;
								if(min>arr[nx][ny]) {
									min = arr[nx][ny];
									min_x = nx;
									min_y = ny;
									
								}
							}
						}
					}
					
					if(bl) {
						len++;
						current_x = min_x;
						current_y = min_y;
					}
					else break;
					
				}
				
				if(max<len) max = len;
			}
		}
		
		System.out.println(max);
		
		

	}

}

/*
출발은 어디에서나 가능

상하좌우 중 더 낮은 영역으로만 이동가능 (더 낮은영역이 여러개인경우 제일 최솟값으로)
만약 더 낮은 인접 영역이 없으면 더 이동 불가

*/