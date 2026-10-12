import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			long N = Long.parseLong(br.readLine());
			
			if (N == 2) {
				sb.append('#').append(test_case).append(' ').append(0).append('\n');
				continue;
			}
			
			ArrayDeque<long[]> q = new ArrayDeque<>();
			HashSet<Long> visited = new HashSet<>();
			
			q.add(new long[] {N, 0L});
			visited.add(N);

			long answer = Long.MAX_VALUE;
			while (!q.isEmpty()) {
				long[] f = q.poll();
				
				long x = f[0];
				long cnt = f[1];

				long sqrt = (long) Math.sqrt(x);
				
				// 제곱수이면, sqrt로 이동
				if (sqrt * sqrt == x) {
					// 더 작은값으로만 갱신
					if (sqrt == 2) {
						answer = cnt+1;
					}
					
					if (!visited.contains(sqrt) && cnt+1 < answer) {
						q.add(new long[] {sqrt, cnt+1});
						visited.add(sqrt);
					}
				}
				
				// 제곱수이든 아니든, 다음 제곱수로 이동 가능
				sqrt++;
				long next = sqrt * sqrt;
				if (!visited.contains(next) && cnt+(next-x) < answer) { // 최적화
					q.add(new long[] {next, cnt+(next-x)});
					visited.add(next);
				}
			}
				
			sb.append('#').append(test_case).append(' ');
			sb.append(answer).append('\n');
		}
		
		System.out.println(sb);
	}
}