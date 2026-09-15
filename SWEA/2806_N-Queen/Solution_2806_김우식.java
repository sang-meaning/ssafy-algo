package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA2806 {
	static int T, N, ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			ans = 0;
			
			dfs(0, 0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
		
	}
	static void dfs(int r, int c, int ld, int rd) {
		
		if(r == N) {
			ans++;
			return;
		}
		for(int i = 0; i < N; i++) {
			int bit = 1 << i;
			
			if((c & bit) != 0) continue;
			
			if((ld & bit) != 0) continue;
			
			if((rd & bit) != 0) continue;
			
			dfs(r + 1, (c|bit), (ld|bit)<< 1, (rd|bit)>>1);
		}
	}
}
