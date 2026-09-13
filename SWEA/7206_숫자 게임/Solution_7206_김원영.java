import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    static Map<Integer,Integer> memo = new HashMap<>();

    static int dfs(int num) {
        if(num < 10) {
            return 0;
        }

        if(memo.containsKey(num)) {
            return memo.get(num);
        }

        String str = Integer.toString(num);
        int n = str.length();
        int max = 0;

        for(int mask=1; mask<(1<<(n-1)); mask++) {
            int mul = 1;
            int temp = 0;

            for(int i=0; i<n; i++) {
                temp = temp*10 + (str.charAt(i)-'0');

                if(i == n-1 || (mask & (1<<i)) != 0) {
                    mul *= temp;
                    temp = 0;
                }
            }

            max = Math.max(max,dfs(mul)+1);
        }

        memo.put(num,max);

        return max;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {
            int num = sc.nextInt();
            memo.clear();
            int answer = dfs(num);

            System.out.println("#" + tc + " " + answer);
        }
        sc.close();
    }
}