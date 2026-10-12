import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution
{
	static int[] memo = new int[100000];
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Arrays.fill(memo, -1);
		
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int N = Integer.parseInt(br.readLine());
			
			int answer = dfs(N);
			
			sb.append("#").append(test_case).append(" ")
			  .append(answer).append("\n");

		}
		
		System.out.print(sb);
	}
	
	static int dfs(int num) {
		if (num < 10) return 0;
		
		if (memo[num] != -1) return memo[num];
		
		String s = String.valueOf(num);
		int len = s.length();
		
		int max = 0;
		
		// 자를 수 있는 위치는 len - 1개
        // mask = 0은 한 번도 자르지 않는 경우라 제외
		for (int mask = 1; mask < (1 << (len - 1)); mask++) {
			
			int product = 1;
			int current = 0;
			
			for (int i = 0; i < len; i++) {
				// 현재 숫자 만들기
				current = current*10 + (s.charAt(i) - '0');
				
				// 마지막 자리이거나
                // i번째 위치를 자르는 경우
				if (i == len - 1 || (mask & (1 << i)) != 0) {
					product *= current;
					current = 0;
				}
			}
			
			max = Math.max(max, 1 + dfs(product));
		}
		
		memo[num] = max;
		
		return max;
	}
}