package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution_1494_이윤찬 {
    static int N;
    static int[][] worm;
    static boolean[] selected;
    static long answer;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine().trim());
            worm= new int[N][2];
            selected = new boolean[N];
            answer = Long.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                worm[i][0] = Integer.parseInt(st.nextToken());
                worm[i][1] = Integer.parseInt(st.nextToken());
            }
            combination(0, 0);
            sb.append("#").append(tc).append(" ")
                    .append(answer).append("\n");
        }
        System.out.print(sb);
    }
    // N마리 중 도착점이 될 N/2마리 선택
    static void combination(int start, int count) {
        if (count == N / 2) {
            calculate();
            return;
        }
        for (int i = start; i < N; i++) {
            selected[i] = true;
            combination(i + 1, count + 1);
            selected[i] = false;
        }
    }
    // 선택한 그룹과 선택하지 않은 그룹의 벡터 합 계산
    static void calculate() {
        long sumX = 0;
        long sumY = 0;

        for (int i = 0; i < N; i++) {
            if (selected[i]) {
                // 도착점 좌표는 더하기
                sumX += worm[i][0];
                sumY += worm[i][1];
            } else {
                // 출발점 좌표는 빼기
                sumX -= worm[i][0];
                sumY -= worm[i][1];
            }
        }
        long result = sumX * sumX + sumY * sumY;
        answer = Math.min(answer, result);
    }
}
