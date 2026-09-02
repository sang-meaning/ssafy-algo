import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {
	private static boolean isNumber(char ch) {
	        return ch >= '0' && ch <= '9';
	    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            String line = br.readLine();

            int n = Integer.parseInt(line.trim());
            char[] tree = new char[n + 1];

            for (int i = 1; i <= n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int nodeIdx = Integer.parseInt(st.nextToken());
                tree[nodeIdx] = st.nextToken().charAt(0);
            }

            int isValid = 1;
            int mid = n / 2;


            int left = 1;
            int right = n; 

            while (left <= mid || right > mid) {
                if (left <= mid) {
                    if (isNumber(tree[left])) {
                        isValid = 0;
                        break;
                    }
                    left++;
                }

                if (right > mid) {
                    if (!isNumber(tree[right])) {
                        isValid = 0;
                        break;
                    }
                    right--;
                }
            }

            sb.append("#").append(tc).append(" ").append(isValid).append("\n");
        }

        System.out.print(sb);
    }
}