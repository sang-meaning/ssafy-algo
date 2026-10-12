package Solution;

import java.util.*;
import java.math.BigInteger;
import java.io.*;

public class Solution_3260_한석호 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            BigInteger A = new BigInteger(st.nextToken());
            BigInteger B = new BigInteger(st.nextToken());

            BigInteger result = A.add(B);

            System.out.println("#" + tc + " " + result);
        }
    }
}