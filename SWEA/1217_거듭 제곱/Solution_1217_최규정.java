import java.util.Scanner;


class Solution
{

	static int T;
	static int base;
	static int power;
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		

		for(int test_case = 0; test_case < 10; test_case++)
		{
			
			T=sc.nextInt();
			
			base=sc.nextInt();
			
			power=sc.nextInt();

			int answer = square(base,power);

			System.out.println("#"+T+" "+answer);
		}
	}

	static int square(int sum, int count){
				
		if(count == 1){
			return sum;
		}

		else{
			sum = sum * base;
			return square(sum, count-1);
		}

	}
}