
import java.util.*;

public class Solution {

    static int N;
    static int[] tall;
    static List<Integer> peaks;
    static long answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();

            tall = new int[N];
            peaks = new ArrayList<>();
            answer = 0;

            // 높이 입력
            for (int i = 0; i < N; i++) {
                tall[i] = sc.nextInt();
            }

            // 봉우리의 인덱스 찾기
            for (int i = 1; i < N - 1; i++) {
                if (tall[i] > tall[i - 1]
                        && tall[i] > tall[i + 1]) {
                    peaks.add(i);
                }
            }

            function();

            sb.append("#")
                    .append(t)
                    .append(" ")
                    .append(answer)
                    .append("\n");
        }

        System.out.print(sb);
        sc.close();
    }

    public static void function() {
        for (int point : peaks) {
            long left = 1;
            long right = 1;

            // 봉우리 왼쪽의 오르막 구간 확인
            for (int i = point - 2; i >= 0; i--) {
                if (tall[i] < tall[i + 1]) {
                    left++;
                } else {
                    break;
                }
            }

            // 봉우리 오른쪽의 내리막 구간 확인
            for (int i = point + 2; i < N; i++) {
                if (tall[i] < tall[i - 1]) {
                    right++;
                } else {
                    break;
                }
            }

            answer += left * right;
        }
    }
}