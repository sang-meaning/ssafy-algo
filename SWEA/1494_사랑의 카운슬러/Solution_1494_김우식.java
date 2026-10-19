import java.io.*;
import java.util.*;

public class Solution {
	
	static int T, N;
	static int[] xs, ys;
	static long totalX, totalY, ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			xs = new int[N];
			ys = new int[N];
			totalX = 0;
			totalY = 0;
			
			for (int i = 0; i < N; i++) {
			    StringTokenizer st = new StringTokenizer(br.readLine());
			    xs[i] = Integer.parseInt(st.nextToken());
			    ys[i] = Integer.parseInt(st.nextToken());
			    totalX += xs[i];
			    totalY += ys[i];
			}
			
			ans = Long.MAX_VALUE;
			
			comb(1, 1, xs[0], ys[0]);
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void comb(int idx, int cnt, long sx, long sy) {
		if(cnt == N/2) {
			long ax = totalX - 2 * sx;
			long ay = totalY - 2 * sy;
			
			ans = Math.min(ans, ax * ax + ay * ay);
			return;
		}
		
		if(N -idx < N / 2 - cnt) return;
		
		comb(idx + 1, cnt + 1, sx + xs[idx], sy + ys[idx]);
		
		comb(idx + 1, cnt , sx, sy);
	}

}
