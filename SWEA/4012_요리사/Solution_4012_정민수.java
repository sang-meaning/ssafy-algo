import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int[][] S = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					S[i][j] = sc.nextInt();

			int half = N / 2;
			int full = (1 << N) - 1;
			int minDiff = Integer.MAX_VALUE;

			for (int mask = 0; mask <= full; mask++)
			{
				if ((mask & 1) == 0) continue;
				if (Integer.bitCount(mask) != half) continue;

				int complement = full ^ mask;

				int tasteA = 0;
				int tasteB = 0;

				for (int i = 0; i < N; i++)
				{
					if (((mask >> i) & 1) == 1)
					{
						for (int j = 0; j < N; j++)
						{
							if (i == j) continue;
							if (((mask >> j) & 1) == 1)
							{
								tasteA += S[i][j];
							}
						}
					}
					else
					{
						for (int j = 0; j < N; j++)
						{
							if (i == j) continue;
							if (((complement >> j) & 1) == 1)
							{
								tasteB += S[i][j];
							}
						}
					}
				}

				int diff = Math.abs(tasteA - tasteB);
				if (diff < minDiff) minDiff = diff;
			}

			System.out.println("#" + test_case + " " + minDiff);
		}
	}
}