import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();


		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N;
			N=sc.nextInt();
			int[] arr = new int[N];
			
			
			int max = 0;
			for(int i=0;i<N;i++) {
				arr[i] = sc.nextInt();
				max = Math.max(max, arr[i]);
			}
			
			int need_two = 0;
			int need_one = 0;
			
			for(int i=0;i<N;i++) {
				int diff = max - arr[i];
				need_two += diff / 2;
				need_one += diff % 2;
			}

			while(need_two - need_one > 1) {
				need_one += 2;
				need_two -= 1;
			}
			
			int answer = 0;
			
			if(need_one > need_two) {
				answer = need_one*2-1;
			}else {
				answer = need_two*2;
			}

			System.out.printf("#%d %d\n",test_case,answer);
			
		}
	}
}