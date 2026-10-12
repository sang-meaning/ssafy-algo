import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(br.readLine().trim());
            long count = 0;

            while (N > 2) {
                long root = (long) Math.sqrt(N);

                if (root * root == N) {
                    N = root;
                    count++;
                } 
                else {
                    long nextRoot = root + 1;
                    long nextSquare = nextRoot * nextRoot;
                    count += (nextSquare - N) + 1;
                    N = nextRoot;
                }
            }

            System.out.println("#" + tc + " " + count);
        }
    }
}