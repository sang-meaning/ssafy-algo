import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;		
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			int nx = 0, ny = 0, dir = 0;
			int[][] arr = new int[H][W];
			for (int i=0; i<H; i++) {
				String s = br.readLine();
				for (int j=0; j<W; j++) {
					char c = s.charAt(j);
					if (c == '.') arr[i][j] = 1;
					if (c == '*') arr[i][j] = 2;
					if (c == '#') arr[i][j] = 3;
					if (c == '-') arr[i][j] = 0;
					
					if (c == '^') { arr[i][j] = 1; nx = i; ny = j; dir = 0; }
					if (c == 'v') { arr[i][j] = 1; nx = i; ny = j; dir = 1; }
					if (c == '<') { arr[i][j] = 1; nx = i; ny = j; dir = 2; }
					if (c == '>') { arr[i][j] = 1; nx = i; ny = j; dir = 3; }
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			String s = br.readLine();
			for (int i=0; i<N; i++) {
				char c = s.charAt(i);
				if (c == 'S') {
					int tx = nx;
					int ty = ny;
					
					while (true) {
						tx += dx[dir];
						ty += dy[dir];
						if (tx < 0 || tx >= H || ty < 0 || ty >= W) break;
						if (arr[tx][ty] == 3) break;
						if (arr[tx][ty] == 2) {
							arr[tx][ty] = 1;
							break;
						}
						
					}
					
					continue;
				}
				
				if (c == 'U') dir = 0;
				if (c == 'D') dir = 1;
				if (c == 'L') dir = 2;
				if (c == 'R') dir = 3;
				
				int tx = nx + dx[dir];
				int ty = ny + dy[dir];
				
				if (tx < 0 || tx >= H || ty < 0 || ty >= W) continue;
				if (arr[tx][ty] != 1) continue;
				
				nx = tx;
				ny = ty;
			}
			
			
			sb.append('#').append(test_case).append(' ');
			for (int i=0; i<H; i++) {
				for (int j=0; j<W; j++) {
					if (arr[i][j] == 2) sb.append('*');
					else if (arr[i][j] == 3) sb.append('#');
					else if (arr[i][j] == 0) sb.append('-');
					else {
						if (nx == i && ny == j) {
							if (dir == 0) sb.append('^');
							if (dir == 1) sb.append('v');
							if (dir == 2) sb.append('<');
							if (dir == 3) sb.append('>');
						} else sb.append('.');
					}
				}
				sb.append('\n');
			}
		}
		
		System.out.println(sb);
	}
}

