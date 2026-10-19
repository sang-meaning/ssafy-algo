
import java.io.*;
import java.util.*;

public class Solution_4038_하상호 {

    static int[] getPi(String pattern) {
        int M = pattern.length();
        int[] pi = new int[M];

        int j = 0;

        for (int i = 1; i < M; i++) {

            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    // KMP
    static int kmp(String text, String pattern) {
        int N = text.length();
        int M = pattern.length();

        int[] pi = getPi(pattern);

        int count = 0;
        int j = 0;

        for (int i = 0; i < N; i++) {

            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (text.charAt(i) == pattern.charAt(j)) {

                if (j == M - 1) {
                    count++;

                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            String B = br.readLine();
            String S = br.readLine();

            int answer = kmp(B, S);

            sb.append("#").append(tc).append(" ")
              .append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
