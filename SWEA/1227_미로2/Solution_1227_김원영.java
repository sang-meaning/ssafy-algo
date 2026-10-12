import java.util.Scanner;

public class Solution {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N = 100;
	static int[][] map = new int[N][N];
	static int[][] visited = new int[N][N];
	static int[] start;
	static int[] finish;

	static void dfs(int x, int y) {
		visited[x][y] =1;
		
		for(int i=0; i<4; i++) {
			int next_r = x + dr[i];
			int next_c = y + dc[i];
			if(visited[next_r][next_c] ==0){
				dfs(next_r,next_c);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = 10;

		for (int tc = 1; tc <= T; tc++) {
			int testnum = sc.nextInt();

			for (int i = 0; i < 100; i++) {
				String line = sc.next();
				for (int j = 0; j < 100; j++) {
					map[i][j] = line.charAt(j);
					visited[i][j] = 0;
					if (map[i][j] == '1') {
						visited[i][j] = 1;
					} else if (map[i][j] == '2') {
						start = new int[] { i, j };
					} else if (map[i][j] == '3') {
						finish = new int[] { i, j };
					}
				}
			}
			int answer;
			dfs(start[0], start[1]);
			if (visited[finish[0]][finish[1]] == 1) {
				answer = 1;
			} else {
				answer = 0;
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
}
