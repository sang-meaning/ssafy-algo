
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static char[][] map;
	// 기차 위치와 방향
	static int[] pos = new int[2];
	static int dir;
	static char shape;
	static int H, W;
	static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			// map을 배열로 
			map = new char[H][W];
			for(int i = 0; i< H; i++) {
				String s = br.readLine();
				for(int j = 0; j<W; j++) {
					char c = s.charAt(j);
					map[i][j] = c;
					if(c == '>' || c == '<' || c == '^' || c== 'v'){
						matchDir(c);
						pos  = new int[]{i, j};
					}
				}
			}
			// 명령어 
			int N = Integer.parseInt(br.readLine());
			String s = br.readLine();
			for(int i = 0; i < N; i++) {
				char a = s.charAt(i);
				
				if(a != 'S') {
					matchDir(a);
				}
				
				action(a);
			}
			
			System.out.print("#" + testcase + " ");
			for(int i = 0; i< H; i++) {
				for(int j =0 ; j<W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println("");
			}
		}
	}
	
	// 방향 바꾸기
	public static void matchDir(char c){
		switch(c) {
		case '^': 
		case 'U':
			dir = 0; shape = '^'; return;
		case 'v': 
		case 'D':
			dir = 1; shape = 'v'; return;
		case '<': 
		case 'L':
			dir = 2; shape = '<'; return;
		case '>': 
		case 'R':
			dir = 3; shape = '>'; return;
		}
		return;
	}
	
	// action
	public static void action(char a) {
		int nr = pos[0] + dr[dir];
		int nc = pos[1] + dc[dir];
		map[pos[0]][pos[1]] = shape;
		
		// go
		if(a != 'S' && nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == '.') {
			map[pos[0]][pos[1]] = '.';
			pos = new int[]{nr, nc};
			map[nr][nc] = shape;
			return;
		}
		
		// shooting
		while(a == 'S' && nr >= 0 && nr < H && nc >= 0 && nc < W) {			
			if(map[nr][nc] == '#') {
				return;
			}
			
			if(map[nr][nc] == '*') {
				map[nr][nc] = '.';
				return;
			}
			
			nr += dr[dir];
			nc += dc[dir];
		}
		return;
	}
	

}
