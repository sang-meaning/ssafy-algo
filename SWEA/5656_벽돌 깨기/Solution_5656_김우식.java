import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static int W;
	static int H;
	static int[][] map;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	static int min = Integer.MAX_VALUE;
	
	public static void dfs(int depth) {

	    if (min == 0) {
	        return;
	    }

	    if (depth == N) {

	        int cnt = 0;

	        for (int r = 0; r < H; r++) {
	            for (int c = 0; c < W; c++) {
	                if (map[r][c] != 0) {
	                    cnt++;
	                }
	            }
	        }

	        min = Math.min(min, cnt);
	        return;
	    }

	    for (int c = 0; c < W; c++) {

	        int[][] copy = new int[H][W];

	        for (int r = 0; r < H; r++) {
	            for (int j = 0; j < W; j++) {
	                copy[r][j] = map[r][j];
	            }
	        }

	        int hitRow = -1;

	        for (int r = 0; r < H; r++) {
	            if (map[r][c] != 0) {
	                hitRow = r;
	                break;
	            }
	        }

	        if (hitRow == -1) {

	            dfs(depth + 1);

	            map = copy;
	            continue;
	        }

	        Queue<int[]> q = new ArrayDeque<>();

	        q.add(new int[] {
	            hitRow,
	            c,
	            map[hitRow][c]
	        });

	        map[hitRow][c] = 0;

	        while (!q.isEmpty()) {

	            int[] cur = q.poll();

	            int row = cur[0];
	            int col = cur[1];
	            int bomb = cur[2];

	            for (int d = 0; d < 4; d++) {

	                for (int k = 1; k < bomb; k++) {

	                    int nr = row + dr[d] * k;
	                    int nc = col + dc[d] * k;

	                    if (nr < 0 || nr >= H ||
	                        nc < 0 || nc >= W) {
	                        break;
	                    }

	                    if (map[nr][nc] == 0) {
	                        continue;
	                    }

	                    if (map[nr][nc] > 1) {
	                        q.add(new int[] {
	                            nr,
	                            nc,
	                            map[nr][nc]
	                        });
	                    }

	                    map[nr][nc] = 0;
	                }
	            }
	        }

	        for (int col = 0; col < W; col++) {

	            int write = H - 1;

	            for (int r = H - 1; r >= 0; r--) {

	                if (map[r][col] != 0) {

	                    int value = map[r][col];

	                    map[r][col] = 0;
	                    map[write][col] = value;

	                    write--;
	                }
	            }
	        }

	        dfs(depth + 1);

	        map = copy;
	    }
	}
	
	public static void main(String[] args) throws Exception {

	    BufferedReader br =
	            new BufferedReader(new InputStreamReader(System.in));

	    StringBuilder sb = new StringBuilder();

	    int T = Integer.parseInt(br.readLine());

	    for(int tc = 1; tc <= T; tc++) {

	        StringTokenizer st =
	                new StringTokenizer(br.readLine());

	        N = Integer.parseInt(st.nextToken());
	        W = Integer.parseInt(st.nextToken());
	        H = Integer.parseInt(st.nextToken());

	        min = Integer.MAX_VALUE;

	        map = new int[H][W];

	        for(int r = 0; r < H; r++) {

	            st = new StringTokenizer(br.readLine());

	            for(int c = 0; c < W; c++) {

	                map[r][c] =
	                        Integer.parseInt(st.nextToken());
	            }
	        }

	        dfs(0);

	        sb.append("#")
	          .append(tc)
	          .append(" ")
	          .append(min)
	          .append("\n");
	    }

	    System.out.print(sb);
	}
}
	