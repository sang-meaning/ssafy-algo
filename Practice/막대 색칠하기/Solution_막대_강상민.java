import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_막대_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1000];;
        dp[0] = 1;
        dp[1] = 2;

        for (int i=2; i<1000; i++) {
            dp[i] = dp[i-2] + dp[i-1]*2;
        }

        System.out.println("답은 "+dp[n]+"지롱");
    }
    
}
