
import java.util.*;
import java.io.*;

public class Solution_아파트_이윤찬 {
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] DP = new int[N];
        DP[0]= 2;

        if(N>=2){
            DP[1]=3;
        }
        for (int i = 2; i < N; i++) {
            DP[i] = DP[i - 1] + DP[i - 2];
        }

        System.out.println(DP[N - 1]);
    }
}
