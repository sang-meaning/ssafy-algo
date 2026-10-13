import java.io.*;
import java.util.*;

public class Solution_막대_이윤찬 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N =Integer.parseInt(br.readLine());
        long[] dp=  new long[N];
        dp[0] = 2;
        dp[1] =5;
        for(int i= 2; i<N; i++){
            dp[i] = 2* dp[i-1] + dp[i-2];
        }
        System.out.println(dp[N-1]);

    }
}
