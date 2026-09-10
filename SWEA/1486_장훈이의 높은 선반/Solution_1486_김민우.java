import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static int N, B;
    static int[] H;
    static int min_diff = Integer.MAX_VALUE;
    
    /*dfs 풀이
    public static void sum_of_emp(int idx, int cur_sum, int total_remain){
        if (cur_sum >= B){
        	int cur_diff = cur_sum - B;
            if(cur_diff < min_diff)
                min_diff = cur_diff;
            return;
        }
 
        if ((cur_sum+total_remain) < B)
            return;
        else{
        	sum_of_emp(idx+1, cur_sum+H[idx+1], total_remain - H[idx+1]);
            sum_of_emp(idx+1, cur_sum, total_remain - H[idx+1]);
        }
    }
    */
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt(); B = sc.nextInt();
            H = new int[N];
            int sum = 0;
            
            for (int i =0; i <N; i++){
                H[i] = sc.nextInt();
            }
            
            //비트마스킹
            for(int i =0; i < (1 << H.length); i++){
            	for(int j = 0; j < H.length; j++){
                	if((i & (1 << j)) > 0)
                        sum += H[j];
                }
                if(sum >= B) {
                  int cur_diff = sum-B;
                  min_diff = (cur_diff < min_diff)?cur_diff:min_diff;
                }
                sum = 0;
            }
            
            System.out.printf("#%d %d\n", test_case, min_diff);
            min_diff = Integer.MAX_VALUE;
		}
	}
}