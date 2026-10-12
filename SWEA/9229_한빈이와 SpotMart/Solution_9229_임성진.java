import java.util.*;
import java.io.*;

public class Solution_9229_임성진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc < T + 1; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] w = new int[N];
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				w[i] = Integer.parseInt(st.nextToken());
			}
			
			int answer = -1;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					int sum = w[i] + w[j];
					if (sum <= M)
						answer = Math.max(answer, sum);
				}
			}
			
			System.out.printf("#%d %d\n", tc, answer);
		}

	}

}
