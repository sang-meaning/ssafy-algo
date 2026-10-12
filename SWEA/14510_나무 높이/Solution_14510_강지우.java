import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int N = Integer.parseInt(br.readLine());
			
			int[] height = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int maxHeight = 0;
			
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, height[i]);
			}
			
			int one = 0;  // +1이 필요한 횟수
			int two = 0;  // +2가 필요한 횟수
			
			for (int i=0; i<N; i++) {
				
				int diff = maxHeight - height[i];
				
				two += diff / 2;
				one += diff % 2;
			}
			
			while (two > one + 1) {
				two--;
				one += 2;
			}
			
			// 홀수 날이 더 많이 필요한 경우
			int oddDays = 2*one -1;
			
			// 짝수 날이 더 많이 필요한 경우
			int evenDays = 2*two;
			
			int answer = Math.max(oddDays, evenDays);
			
			if (one == 0 && two == 0) {
				answer = 0;
			}	
				
			sb.append("#")
			.append(test_case)
			.append(" ")
			.append(answer)
			.append("\n");
		}
		
		System.out.println(sb);
	}
}