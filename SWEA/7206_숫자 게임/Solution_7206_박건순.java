import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {

    static int max_turn;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            int num = Integer.parseInt(br.readLine());

            max_turn = 0;

            dfs(num, 0);

            System.out.println("#" + test_case + " " + max_turn);
        }
    }

    static void dfs(int num, int turn) {

        String str = String.valueOf(num);
        int len = str.length();

        if (len == 1) {
            max_turn = Math.max(max_turn, turn);
            return;
        }

        int size = 1 << (len - 1);
        for (int mask = 1; mask < size; mask++) {

            int multiply_result = 1;
            int current_num = str.charAt(0) - '0';

            for (int i = 0; i < len - 1; i++) {

                if ((mask & (1 << i)) != 0) {

                    multiply_result *= current_num;

                    current_num = str.charAt(i + 1) - '0';

                } else {

                    current_num
                            = current_num * 10
                            + (str.charAt(i + 1) - '0');
                }
            }

            multiply_result *= current_num;

            dfs(multiply_result, turn + 1);
        }
    }
}