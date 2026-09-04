import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= TC; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] weights = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            int maxWeight = -1;

            // 2개의 과자를 고르는 모든 조합 확인 (완전탐색)
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    int sum = weights[i] + weights[j];
                    
                    // 무게 합이 M 이하이면서 기존 최대값보다 크면 갱신
                    if (sum <= M) {
                        maxWeight = Math.max(maxWeight, sum);
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(maxWeight).append("\n");
        }

        System.out.print(sb);
    }
}