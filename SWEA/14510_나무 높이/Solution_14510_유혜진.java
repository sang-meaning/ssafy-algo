import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_14510_유혜진 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[] trees = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int maxHeight = 0;
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, trees[i]);
            }

            int oddCount = 0; // 1씩 더해야 하는 횟수
            int evenCount = 0; // 2씩 더해야 하는 횟수

            for (int i = 0; i < N; i++) {
                int diff = maxHeight - trees[i];
                evenCount += diff / 2;
                oddCount += diff % 2;
            }

            // 짝수 날(2)에 줄 수 있는 양을 홀수 날(1) 두 번으로 쪼갤 수 있음을 활용
            while (evenCount > oddCount + 1) {
                evenCount--;
                oddCount += 2;
            }

            int ans = 0;
            if (evenCount > oddCount) {
                ans = evenCount * 2;
            } else if (oddCount > evenCount) {
                ans = oddCount * 2 - 1;
            } else {
                ans = evenCount + oddCount;
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}