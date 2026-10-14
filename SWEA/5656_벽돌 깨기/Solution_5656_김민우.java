import java.util.*;
import java.io.*;

public class Solution_5656_김민우 {
	static List<int[]>[] Brick;
	static int[][] map;
	static int T, N, H, W, answer;
	static int total;
	static Queue<int[]> que;
	
	static int[][] delta = new int[][] {{0,-1},{-1,0},{0,1},{1,0}};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			total = 0;
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] != 0) total++;
				}
			}
			
			Brick = new ArrayList[N+1];
			for(int i  = 1; i <= N; i++) {
				Brick[i] = new ArrayList<>();
			}
			
			answer = total;
			que = new LinkedList<>();
			breakBrick(0, total);
			
			System.out.printf("#%d %d\n", test_case, answer);
		}//test_case
		
	}//main
	
	public static void breakBrick(int cnt, int remain) {
		if (answer == 0) {
            return;
        }

        if (remain == 0) {
            answer = 0;
            return;
        }

        if (cnt == N) {
            answer = Math.min(answer, remain);
            return;
        }
		
		for(int i = 0; i < W; i++) {
			int idx = check(i);
			if(idx == -1)
				continue;
			
			int[][] backup = copyMap(map);
			
			que.offer(new int[] {idx, i, map[idx][i]});
	        map[idx][i] = 0;

	        int broken = 1;

	        while (!que.isEmpty()) {
	            int[] current = que.poll();

	            int r = current[0];
	            int c = current[1];
	            int power = current[2];

	            for (int[] dir : delta) {
	                for (int distance = 1; distance < power; distance++) {
	                    int nr = r + dir[0] * distance;
	                    int nc = c + dir[1] * distance;

	                    if (!isIn(nr, nc)) {
	                        break;
	                    }

	                    // 빈칸이어도 폭발은 계속 진행
	                    if (map[nr][nc] == 0) {
	                        continue;
	                    }

	                    int nextPower = map[nr][nc];

	                    // 발견 즉시 제거하여 중복 등록 방지
	                    map[nr][nc] = 0;
	                    broken++;

	                    que.offer(new int[] {nr, nc, nextPower});
	                }
	            }
	        }
	        
			gravity(map);
			
			breakBrick(cnt+1, remain - broken);
			
			for (int row = 0; row < map.length; row++) {
			      System.arraycopy(backup[row], 0, map[row], 0, map[row].length);
			}
		}
		
	}//breakBrick
	
	static void gravity(int[][] map) {
	    int H = map.length;
	    int W = map[0].length;

	    for (int col = 0; col < W; col++) {
	        int bottom = H - 1; // 벽돌을 놓을 위치

	        for (int row = H - 1; row >= 0; row--) {
	            if (map[row][col] != 0) {
	                int brick = map[row][col];

	                map[row][col] = 0;      // 원래 위치 비우기
	                map[bottom][col] = brick;
	                bottom--;
	            }
	        }
	    }
	}
	
	static int[][] copyMap(int[][] map) {
	    int[][] copy = new int[map.length][];

	    for (int row = 0; row < map.length; row++) {
	        copy[row] = map[row].clone();
	    }

	    return copy;
	}
	
	//해당 column의 벽돌들이 모두 깨졌을 경우 -1 반환
	//그외에는 가장 처음 만나는 index 반환
	public static int check(int col) {
		for(int i = 0; i < H; i++) {
			if(map[i][col] != 0)
				return i;
		}
		return -1;
	}
	
	public static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}
