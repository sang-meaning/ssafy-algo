import java.util.*;
import java.io.*;
import java.math.BigInteger;

class Solution_3260_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int A, B;

    public static void main(String args[]) throws IOException {
        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            BigInteger A = new BigInteger(st.nextToken());
            BigInteger B = new BigInteger(st.nextToken());

            StringBuilder sb = new StringBuilder();

            sb.append("#")
                    .append(test_case)
                    .append(" ")
                    .append(A.add(B));
            System.out.println(sb);
        }
    }
}