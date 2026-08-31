import java.util.Scanner;
import java.io.FileInputStream;
import java.math.BigInteger;


class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			BigInteger A = sc.nextBigInteger();
            BigInteger B = sc.nextBigInteger();
           

            BigInteger sum = A.add(B);

            System.out.println("#"+ test_case +" "+ sum);
		}
	}
}