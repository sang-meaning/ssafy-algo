import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); 
            int M = Integer.parseInt(st.nextToken()); 

            int[] weights = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(weights);

            int left = 0;
            int right = N - 1;
            int maxWeight = -1;

            while (left < right) {
                int sum = weights[left] + weights[right];

                if (sum <= M) {
                    maxWeight = Math.max(maxWeight, sum);
                    left++;
                } else {
                    right--;
                }
            }

            sb.append("#").append(tc).append(" ").append(maxWeight).append("\n");
        }

        System.out.print(sb);
    }
}