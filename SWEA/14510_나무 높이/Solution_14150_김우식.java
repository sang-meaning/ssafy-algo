import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] trees = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int max = 0;
			
			for(int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken()); 
				max = Math.max(trees[i], max);
			}
			
			int one = 0;
			int two = 0;
			
			for(int i = 0; i < N; i++) {
				int diff = max - trees[i];
				
				two += diff/2;
				one += diff%2;
			}
			// +2가 너무 많으면
			// +2 하나를 +1 두 번으로 바꿔서 날짜 균형을 맞춤
			while (two > one + 1) {
			    two--;
			    one += 2;
			}
			int ans = 0;

			// +1 작업이 더 많으면 홀수날에 끝남
			if (one > two) {
			    ans = one * 2 - 1;
			}
			// +2 작업이 더 많거나 같으면 짝수날에 끝남
			else {
			    ans = two * 2;
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
		
	}

}
