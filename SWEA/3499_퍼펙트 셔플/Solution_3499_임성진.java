import java.util.*;
import java.io.*;

public class Solution_3499_임성진 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());

			String[] cards = new String[N];
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < N; i++) {
				cards[i] = st.nextToken();
			}

			int half = (N + 1) / 2;

			sb.append('#').append(tc);
			for (int i = 0; i < half; i++) {
				sb.append(' ').append(cards[i]);
				if (half + i < N) {
					sb.append(' ').append(cards[half + i]);
				}
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}