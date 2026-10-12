import java.util.*;
import java.io.*;
import java.math.BigInteger;


public class Solution_3260_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());

            BigInteger a = new BigInteger(st.nextToken());
            BigInteger b = new BigInteger(st.nextToken());

            sb.append("#"+t+" "+ a.add(b));
            sb.append("\n");
        }

        System.out.println(sb);
        

        
    }
    
}
