import java.util.*;
import java.io.*;


public class Solution_1873_김민우 {

	static char[][] map;
	static int[] init;
	static Map<Character, int[]> delta;
	static int T, H, W, D, r, c;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		delta = new HashMap<>();
		delta.put('<', new int[]{0,-1});
		delta.put('^', new int[]{-1,0});
		delta.put('>', new int[]{0,1});
		delta.put('v', new int[]{1,0});
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			r = 0;
			c = 0;
			init = new int[2];
			
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				String s= st.nextToken();
				for(int j = 0; j < W; j++) {
					map[i][j] = s.charAt(j);
					if(delta.containsKey(s.charAt(j))) {
						r = i;
						c = j;
						init = delta.get(s.charAt(j));
					}
				}
			}//입력 for문 끝
			
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			String cmdLine = st.nextToken();
			
			for(int i = 0; i < D; i++) {
				char cmd = cmdLine.charAt(i);
				//SHOOT의 경우
				switch(cmd) {
					case 'S':
						shoot(init);
						break;
					case 'U':
						changeDir('^');
						break;
					case 'R':
						changeDir('>');
						break;
					case 'D':
						changeDir('v');
						break;
					case 'L':
						changeDir('<');
						break;
				}
			}
			
			System.out.printf("#%d ", test_case);
			for(int i = 0; i < H; i++) {
				for(int j= 0; j < W; j++) {
					System.out.printf("%c", map[i][j]);
				}
				System.out.println();
			}	
		}//test_case 끝
	}//main 끝

	public static void changeDir(char d) {
		int nr = 0;
		int nc = 0;
		init = delta.get(d);
		nr = r + init[0];
		nc = c + init[1];
		
		if(isIn(nr,nc) && map[nr][nc] == '.') {
			map[r][c] = '.';
			r = nr;
			c = nc;	
		}
		map[r][c] = d;
	}//changeDir 끝
	
	public static void shoot(int[] dir) {
		int nr = r + dir[0];
		int nc = c + dir[1];
		
		while(isIn(nr,nc) && map[nr][nc] != '#' && map[nr][nc] != '*') {
			nr += dir[0];
			nc += dir[1];
		}
		if(!isIn(nr, nc) || map[nr][nc] == '#')
			return;
		if(map[nr][nc] == '*') {
			map[nr][nc] = '.';
			return;
		}
	}//shoot 끝
	
	public static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}//isIn 끝
	
}
