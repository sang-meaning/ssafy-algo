import java.util.*;
import java.io.*;

public class Solution_1226_임성진 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[] start = new int[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc < 11; tc++) {
			br.readLine().trim();
			
			Queue<int[]> q = new ArrayDeque<>();
			
			int[][] map = new int[16][16];

			for (int i = 0; i < 16; i++) {
				String arr = br.readLine().trim();
				for (int j = 0; j < 16; j++) {
					map[i][j] = arr.charAt(j) - '0';
					if (map[i][j] == 2) {
						start[0] = i;
						start[1] = j;
					}
				}
			}
			
			q.add(start);
			
			boolean gg = true;
			int found = 0;
			
			while(gg) {
				int[] cur = q.poll();
				
				gg = true;
				
				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + dr[i];
					int nc = cur[1] + dc[i];
					
					if(nr < 0 || nr >= 16 || nc < 0 || nc >= 16) continue;
					if(map[nr][nc] == 3) {
						gg = false;
						found = 1;
						break; }
					if(map[nr][nc] != 0) continue;
					
					q.add(new int[] {nr, nc});
					map[nr][nc] = -1;
				}
				
				if(q.isEmpty()) gg = false;
			}
			
			System.out.printf("#%d %d\n", tc, found );
		}
		
	}
}