import java.io.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            long n = Long.parseLong(br.readLine().trim());
            long count = 0;

            while (n > 2) {
                long root = (long) Math.sqrt(n);

                while (root * root > n) root--;
                while ((root + 1) * (root + 1) <= n) root++;

                if (root * root == n) {
                    n = root;
                    count++;
                } else {
                    long next = root + 1;
                    count += next * next - n + 1; // 증가 횟수 + 제곱근 연산
                    n = next;
                }
            }

            answer.append('#').append(tc).append(' ').append(count).append('\n');
        }

        System.out.print(answer);
    }
}