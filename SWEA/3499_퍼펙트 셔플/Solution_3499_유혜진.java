import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] cards = new String[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cards[i] = st.nextToken();
            }

            // 앞쪽 덱의 크기 계산 (N이 홀수면 앞쪽이 1장 더 많음)
            int mid = (N + 1) / 2;

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ");

            // 교대로 하나씩 출력에 추가
            for (int i = 0; i < mid; i++) {
                // 앞쪽 덱의 카드
                sb.append(cards[i]).append(" ");

                // 뒤쪽 덱의 카드 (N이 홀수여서 마지막에 뒤쪽 덱 범위를 벗어나는 것 방지)
                if (i + mid < N) {
                    sb.append(cards[i + mid]).append(" ");
                }
            }

            System.out.println(sb.toString().trim());
        }
    }
}