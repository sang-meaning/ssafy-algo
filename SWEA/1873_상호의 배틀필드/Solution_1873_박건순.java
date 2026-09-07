import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	static char[][] map;
	static int H;
	static int W;
	static int startX;
	static int startY;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int curDir;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			for (int height = 0; height < H; height++) {
				String line = br.readLine();
				for (int width = 0; width < W; width++) {
					map[height][width] = line.charAt(width);

					if (map[height][width] == '<' || map[height][width] == '^' || map[height][width] == '>'
							|| map[height][width] == 'v') {
						startX = height;
						startY = width;
						if (map[height][width] == '^')
							curDir = 0;
						else if (map[height][width] == 'v')
							curDir = 1;
						else if (map[height][width] == '<')
							curDir = 2;
						else if (map[height][width] == '>')
							curDir = 3;
					}
				}
			}

			int count = Integer.parseInt(br.readLine());
			char[] command = br.readLine().toCharArray();

			runGame(command, count);
			
			System.out.print("#" + test_case+ " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}

	static void runGame(char[] command, int count) {
		for (int i = 0; i < count; i++) {
			switch (command[i]) {
			case 'U':
				if (startX - 1 >= 0 && map[startX - 1][startY] == '.') {
					map[startX][startY] = '.';
					map[startX - 1][startY] = '^';
					startX = startX - 1;
				} else {
					map[startX][startY] = '^';
				}
				curDir = 0;
				break;
			case 'D':
				if (startX + 1 < H && map[startX + 1][startY] == '.') {
					map[startX][startY] = '.';
					map[startX + 1][startY] = 'v';
					startX = startX + 1;
				} else {
					map[startX][startY] = 'v';
				}
				curDir = 1;
				break;
			case 'L':
				if (startY - 1 >= 0 && map[startX][startY - 1] == '.') {
					map[startX][startY] = '.';
					map[startX][startY - 1] = '<';
					startY = startY - 1;
				} else {
					map[startX][startY] = '<';
				}
				curDir = 2;
				break;
			case 'R':
				if (startY + 1 < W && map[startX][startY + 1] == '.') {
					map[startX][startY] = '.';
					map[startX][startY + 1] = '>';
					startY = startY + 1;
				} else {
					map[startX][startY] = '>';
				}
				curDir = 3;
				break;
			case 'S':
				int curX = startX;
				int curY = startY;
				while (true) {
					if (curX < 0 || curX >= H || curY < 0 || curY >= W)
						break;
					
					if (map[curX][curY] == '*') {
						map[curX][curY] = '.';
						break;
					}else if(map[curX][curY] == '#') {
						break;
					}
					curX += dx[curDir];
					curY += dy[curDir];
				}
				break;
			}
		}
	}
}