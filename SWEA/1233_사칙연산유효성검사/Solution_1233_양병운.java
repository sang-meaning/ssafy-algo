import java.util.*;
import java.io.*;

class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        for(int test_case = 1; test_case <= T; test_case++) {
            int result = 1;
            int N = Integer.parseInt(br.readLine());
            boolean[] isNum = new boolean[N + 1];
            boolean[] isOperator = new boolean[N + 1];
            int[][] child = new int[N + 1][2];
            for(int i = 0; i < N; i++) {
                String[] split = br.readLine().split(" ");
                int idx = Integer.parseInt(split[0]);
                if(split.length == 4) {
                    String operator = split[1];
                    if(operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
                        isOperator[idx] = true;
                        child[idx][0] = Integer.parseInt(split[2]);
                        child[idx][1] = Integer.parseInt(split[3]);
                    } else {
                        result = 0;
                    }
                } else if(split.length == 2) {
                    isNum[idx] = true;
                }
            }

            for(int i = 1; i <= N; i++) {
                if(isOperator[i]) {
                    int left = child[i][0];
                    int right = child[i][1];
                    if(left == 0 || right == 0) {
                        result = 0;
                    }
                }
            }
            System.out.println("#" + test_case + " " + result);
        }
    }
}