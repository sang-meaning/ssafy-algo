import java.util.*;
import java.io.*;

public class Solution_3260_임성진 {
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(next());

		for (int tc = 1; tc <= T; tc++) {
			String a = next();
			String b = next();
			sb.append('#').append(tc).append(' ').append(add(a, b)).append('\n');
		}
		System.out.print(sb);
	}

	static String add(String a, String b) {
		StringBuilder sb = new StringBuilder();

		int i = a.length() - 1;
		int j = b.length() - 1;
		int carry = 0;

		while (i >= 0 || j >= 0 || carry > 0) {
			int sum = carry;
			if (i >= 0) sum += a.charAt(i--) - '0';
			if (j >= 0) sum += b.charAt(j--) - '0';

			sb.append(sum % 10);
			carry = sum / 10;
		}
		return sb.reverse().toString();
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine().trim());
		}
		return st.nextToken();
	}
}