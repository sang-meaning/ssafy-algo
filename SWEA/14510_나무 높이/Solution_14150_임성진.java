import java.io.*;
import java.util.*;

public class Solution_14150_임성진 {
	static int[] h;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine().trim());
			h = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int max = 0;

			for (int i = 0; i < n; i++) {
				h[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, h[i]);
			}

			int ans = Integer.MAX_VALUE;

			for (int H = max; H <= max + 2; H++)
				ans = Math.min(ans, days(H));

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}

	static int days(int H) {
		int S = 0, odd = 0;
		// S = 모든 나무가 받아야 할 물의 총량
		// odd = 부족한 양이 홀수인 나무 수
		for (int x : h) {
			int d = H - x;
			// d = 이 나무의 부족한 양
			S += d;
			if (d % 2 == 1)
				odd++;
			// odd 에 홀수양 더하기
		}
		if (S == 0)
			return 0;
		
		int best = Integer.MAX_VALUE;
		
		for (int a = odd; a <= S; a += 2) {
			int b = (S - a) / 2;
			best = Math.min(best, Math.max(2*a - 1, 2*b));
			if(2*a - 1 >= 2*b) break;
		}
		return best;
	}
}