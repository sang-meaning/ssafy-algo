import java.io.*;
import java.util.*;


public class Solution_7733_김민우 {
	static int[][] cheese;
	static boolean[][] check;
	static int T, N;
	static int max;
	static int delta[][] = new int[][] {{0,-1},{-1,0},{0,1},{1,0}};
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			cheese = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = 1;
			for(int i = 1; i <= 100; i++) {
				check = new boolean[N][N];
				int cur = checkCheese(i);
				if(cur == 0)
					break;
				max = (cur>max)?cur:max;
			}
			
			sb.append("#"+test_case+" "+max+"\n");
			
		}
		System.out.print(sb);
	}
	
	public static int checkCheese(int day) {
		int size = 0;
		
		Queue<int[]> que = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(cheese[i][j] <= day || check[i][j])
					continue;
				
				que.offer(new int[] {i, j});
				check[i][j] = true;
				
				while(!que.isEmpty()) {
					int[] idx = que.poll();
					for(int[] dir : delta) {
						int ni = idx[0] + dir[0];
						int nj = idx[1] + dir[1];
						if(!isIn(ni, nj) || check[ni][nj] || cheese[ni][nj] <= day)
							continue;
						que.offer(new int[] {ni, nj});
						check[ni][nj] = true;
					}
				}
				size++;
			}
		}
		
		return size;
	}
	
	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

}
