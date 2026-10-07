import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;

        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; ++tc) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long count = 0;

            while (n != 2) {
                long a = (long) Math.sqrt(n);
                if (a * a == n) {
                    n = a;
                    count++;
                } else {
                    long jump = (a + 1) * (a + 1);
                    count += (jump - n);
                    n = jump;
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }

        System.out.print(sb);
    }
}