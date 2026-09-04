import java.util.Arrays;
import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int[] weights = new int[N];
			for(int i = 0; i < N; i++) {
				weights[i] = sc.nextInt();
			}
			
			Arrays.sort(weights);
			
			int maxWeight = -1;
			int left = 0;
			int right = N - 1;

			while(left < right) {
				int sum = weights[left] + weights[right];
				
				if(sum <= M) {
					maxWeight = Math.max(maxWeight, sum);
					left++;
				} else {
					right--;
				}
			}
			
			System.out.println("#" + test_case + " " + maxWeight);
		}
	}
}