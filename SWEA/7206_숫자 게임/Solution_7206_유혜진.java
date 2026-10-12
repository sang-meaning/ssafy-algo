import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_7206_유혜진 {
    static int[] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        
        memo = new int[100000];
        Arrays.fill(memo, -1);

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine().trim());
            sb.append("#").append(t).append(" ").append(getMaxTurn(n)).append("\n");
        }
        System.out.print(sb);
    }

    static int getMaxTurn(int n) {
        if (n < 10) return 0;
        if (memo[n] != -1) return memo[n];

        int max = 0;
        String s = String.valueOf(n);
        int len = s.length();

        // 비트마스크를 이용해 자를 위치 결정 (예: len=3이면 2개의 자리 사이에 2가지 선택권)
        int totalComb = 1 << (len - 1);
        for (int i = 1; i < totalComb; i++) {
            int product = 1;
            int prevIdx = 0;
            
            for (int j = 0; j < len - 1; j++) {
                if ((i & (1 << j)) != 0) {
                    product *= Integer.parseInt(s.substring(prevIdx, j + 1));
                    prevIdx = j + 1;
                }
            }
            product *= Integer.parseInt(s.substring(prevIdx));

            max = Math.max(max, 1 + getMaxTurn(product));
        }

        return memo[n] = max;
    }
}