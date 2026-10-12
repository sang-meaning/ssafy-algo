import java.util.Scanner;

public class Solution {
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[] weight = new int[N];
			int Max = -1;
			
			for(int i = 0; i < N; i++) {
				weight[i] = sc.nextInt();
			}
			
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N; j++) {
			        int sum = weight[i] + weight[j];

			        if (sum <= M) {
			            Max = Math.max(Max, sum);
			        }
				}
			}
			System.out.println("#"+test_case+" "+Max);
		}
	}
}
