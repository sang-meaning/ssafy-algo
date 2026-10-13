import java.util.Scanner;
class point{
	int x;
	int y;
	public point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	static int calc(point location,int k, int map[][]) {
		int count = 0;
		for(int i=0; i< map.length;i++) {
			for(int j=0; j<map.length;j++) {
				if((Math.abs(location.x-i)+ Math.abs(location.y-j))< k && map[i][j] == 1) {
					count++;
				}
			}
		}
		return count;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					point location = new point(i,j);
					
					for(int k=1;k<=2*N-1; k++) {
						int count = calc(location, k, map);
						if(count*M - (k*k + (k-1)*(k-1))>=0) {
							max = Math.max(max, count);
						}
					}
				}
			}
			System.out.println("#"+tc+" "+max);
		}
	}
}
