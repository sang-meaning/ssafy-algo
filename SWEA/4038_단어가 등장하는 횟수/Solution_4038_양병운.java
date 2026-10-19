import java.io.*;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            String first = br.readLine();
            String second = br.readLine();
            int[] pi = new int[second.length()];
            int idx = 0;
            for (int i = 1; i < second.length(); i++) {
                while (idx > 0 && second.charAt(i) != second.charAt(idx)) {
                    idx = pi[idx - 1];
                }
                if (second.charAt(i) == second.charAt(idx)) idx++;
                pi[i] = idx;
            }
            idx = 0;
            int count = 0;
            for (int i = 0; i < first.length(); i++) {
                while (idx > 0 && first.charAt(i) != second.charAt(idx)) {
                    idx = pi[idx - 1];
                }
                if (first.charAt(i) == second.charAt(idx)) idx++;
                if (idx == second.length()) {
                    count++;
                    idx = pi[idx - 1];
                }
            }
            System.out.println("#" + test_case + " " + count);
        }
    }
}