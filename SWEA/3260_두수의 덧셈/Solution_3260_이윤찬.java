package swea;
import java.math.BigInteger;
import java.util.*;
import java.io.*;
public class Solution_3260_이윤찬 {
    static BigInteger A ,B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t= 1; t<=T; t++){
            st = new StringTokenizer(br.readLine());


            A = new BigInteger(st.nextToken());
            B= new BigInteger(st.nextToken());

            BigInteger result=  A.add(B);


            System.out.println("#"+t+" "+result);
        }
    }

}
