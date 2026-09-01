
import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			String[] card = new String[N];
			String[] ans = new String[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < N; i++) {
				card[i] = st.nextToken();
			}
			
			int first = 0;
			int second = (N + 1) / 2;
			
			for(int i = 0; i < N; i++) {
				if(i%2==0) {
					ans[i] = card[first];
					first++;
				}else {
					ans[i] = card[second];
					second++;
				}
			}
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < N; i++) {
				sb.append(ans[i]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
