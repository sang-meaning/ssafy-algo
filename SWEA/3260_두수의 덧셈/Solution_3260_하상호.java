package al_prac;

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution_3260_하상호 {
	public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int T;
        T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            BigInteger A = sc.nextBigInteger();
            BigInteger B = sc.nextBigInteger();

            BigInteger result = A.add(B);

            System.out.println("#" + test_case + " " + result);
        }
    }
}
