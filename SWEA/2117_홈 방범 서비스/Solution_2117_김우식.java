import java.io.*;
import java.util.*;

public class SWEA2117 {
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans = 0;
			
			for(int k = 1; k <= N + 1; k++) {
				int cost = k * k + (k - 1) * (k - 1);
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < N; c++) {
						int cnt = 0;
						for(int i = 0; i < N; i++) {
							for(int j = 0; j < N; j++) {
								
								int d = Math.abs(r - i) + Math.abs(c - j);
								
								if(d < k && map[i][j] == 1) cnt++;
							}
						}
						
						if(cnt * M >= cost) {
							ans = Math.max(ans, cnt);
						}
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
