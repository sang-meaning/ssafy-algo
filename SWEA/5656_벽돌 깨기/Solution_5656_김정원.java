package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_김정원 {
	static int N,W,H,answer;
	static int[][] map;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	
	static class Bomb {
	    int x, y, range;
	    
	    Bomb(int range) {
			this.range = range;
		}

		Bomb(int x, int y, int range) {
	        this.x = x;
	        this.y = y;
	        this.range = range;
	    }

		@Override
		public String toString() {
			return "Bomb [y=" + y + ", x=" + x + ", range=" + range + "]";
		}
	    
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			answer = H * W;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {					
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0);
			System.out.println(String.format("#%d %d", t, answer));
		}
	}
	
	static void dfs(int count) {
		if (count == N) {
			int bombCount = getBombCount();
			answer = answer > bombCount ? bombCount : answer;  
			return;
		}
		
		for (int x = 0; x < W; x++) {
			int y = getStartY(x);
			if (y == -1) {
				dfs(count+1);
				continue;			
			}
			
			Queue<Bomb> bombs = new ArrayDeque<>();
			int[][] backup = deepCopy(map);
			int range = map[y][x];
	        bombs.offer(new Bomb(x,y,range));
	        
			explodeBomb(bombs);
			applyGravity();
			dfs(count+1);
			
			map = backup;
		}		
	}
	
	static int getBombCount() {
		int count = H * W;
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				if (map[y][x] == 0) count--; 
			}
		}
		return count;
	}
	
	static int getStartY(int x) {
		int y = -1;
        for (int i = 0; i < H; i++) {
            if (map[i][x] != 0) {
                y = i;
                break;
            }
        }
        return y;
	}
    
    static int[][] deepCopy(int[][] ori) {
		int[][] copy = new int[H][W];
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				copy[y][x] = ori[y][x];
			}
		}
		return copy;
	}
	
	static void explodeBomb(Queue<Bomb> bombs) {
		int x,y,nx,ny,range;
		while(bombs.isEmpty() == false) {
			Bomb bomb = bombs.poll();
			x = bomb.x;
			y = bomb.y;
			range = bomb.range;
			map[y][x] = 0;
			for (int r = 1; r < range; r++) {
				for (int d = 0; d < 4; d++) {
					nx = x + dx[d] * r;
					ny = y + dy[d] * r;
					if (isMapIn(nx, ny) == false) continue;
					if (map[ny][nx] != 0) bombs.add(new Bomb(nx,ny,map[ny][nx]));
					map[ny][nx] = 0;
				}
			}
		}
	}
	
	static void applyGravity() {
	    int[][] nextMap = new int[H][W];
	    for (int col = 0; col < W; col++) {
	        int writeRow = H - 1;
	        for (int row = H - 1; row >= 0; row--) {
	            if (map[row][col] == 0) continue;

	            nextMap[writeRow][col] = map[row][col];
	            writeRow--;
	        }
	    }
	    map = nextMap;
	}
	
	static boolean isMapIn(int x, int y) {
		return x >= 0 && x < W && y >= 0 && y < H;
	}
}
