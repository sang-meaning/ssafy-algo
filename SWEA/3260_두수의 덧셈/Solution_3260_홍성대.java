import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; ++tc) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            StringBuilder result = new StringBuilder();
            int i = a.length() - 1;
            int j = b.length() - 1;
            int carry = 0;

            while (i >= 0 || j >= 0 || carry > 0) {
                int sum = carry;
                if (i >= 0) sum += (a.charAt(i--) - '0');
                if (j >= 0) sum += (b.charAt(j--) - '0');

                carry = sum / 10;
                result.append((char) ((sum % 10) + '0'));
            }

            result.reverse();

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.print(sb);
    }
}