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
			int n;
			n=sc.nextInt();
			int[] map = new int [n];
            
            int max = 0;
            
            for(int i = 0; i < n; i++){
                map[i] = sc.nextInt();
                if(map[i] >max){
                	max = map[i];
                }
            }
            
            int one_count = 0;
            int two_count = 0;
            
            for(int i = 0; i < n; i++){
            	int require = max - map[i];
                

                	
                    two_count = two_count + require / 2;
                    one_count = one_count + require % 2;
                }
                
                int answer = 0;
                
                if(two_count > one_count){
					int remain = two_count - one_count;
                    int num1 = (remain * 2) / 3;
                    int num2 = (remain * 2) % 3; 
                    answer =(num1 * 2) + num2 + (one_count * 2);  
                }

            	else if(one_count > two_count){
                	answer = (one_count * 2) -1;
                }
                
            	else{
                	answer = one_count *2;
                }
            
            System.out.println("#" + test_case + " " + answer);
            
            }
	}
}