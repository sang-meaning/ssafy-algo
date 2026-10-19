import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1494_한석호 {
    static int N;
    static long[][] points;
    static boolean[] selected;
    static long minVectorSize;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            points = new long[N][2];
            selected = new boolean[N];
            minVectorSize = Long.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                points[i][0] = Long.parseLong(st.nextToken());
                points[i][1] = Long.parseLong(st.nextToken());
            }

            // 첫 번째 지렁이(0번)는 대칭성 제거를 위해 무조건 '도착점(selected = true)'으로 고정
            selected[0] = true;
            combination(1, 1);

            sb.append("#").append(t).append(" ").append(minVectorSize).append("\n");
        }

        System.out.print(sb);
    }

    /**
     * @param idx   탐색할 지렁이 인덱스
     * @param count 현재까지 선택된 도착점 지렁이 수
     */
    static void combination(int idx, int count) {
        // N / 2 마리를 모두 선택한 경우
        if (count == N / 2) {
            calculate();
            return;
        }

        // 남은 원소를 모두 선택해도 N / 2를 채울 수 없는 경우 가지치기
        if (N - idx < (N / 2) - count) {
            return;
        }

        for (int i = idx; i < N; i++) {
            selected[i] = true;
            combination(i + 1, count + 1);
            selected[i] = false;
        }
    }

    static void calculate() {
        long sumX = 0;
        long sumY = 0;

        for (int i = 0; i < N; i++) {
            if (selected[i]) {
                sumX += points[i][0];
                sumY += points[i][1];
            } else {
                sumX -= points[i][0];
                sumY -= points[i][1];
            }
        }

        long vectorSize = sumX * sumX + sumY * sumY;
        if (vectorSize < minVectorSize) {
            minVectorSize = vectorSize;
        }
    }
}