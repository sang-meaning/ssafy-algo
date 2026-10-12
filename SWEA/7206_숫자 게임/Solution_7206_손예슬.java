import java.util.HashMap;
import java.util.Scanner;

public class Solution7206 {
    static HashMap<Integer, Integer> memo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            memo = new HashMap<>();
            int depth = game(N);
            sb.append("#").append(tc).append(" ").append(depth).append("\n");
        }
        System.out.print(sb);
    }

    static int game(int n) {
        if (n < 10) return 0; // 한 자리 수가 되면 더 못 쪼갬 -> 재귀 종료

        String s = String.valueOf(n);
        int gaps = s.length() - 1; // 자릿수 사이 경계 개수
        int max = 0;

        // 경계 gaps개 각각을 "끊는다/안 끊는다"로 표현한 비트마스크를 전부 시도
        for (int mask = 1, size = 1 << gaps; mask < size; mask++) {
            int num = s.charAt(0) - '0'; // 현재 조각을 만들어가는 숫자
            int mul = 1;                 // 조각들의 곱

            for (int bit = 0; bit < gaps; bit++) {
                if ((mask & (1 << bit)) == 0) {
                    // 이 위치는 안 끊음 -> 다음 숫자를 이어붙임
                    num = num * 10 + (s.charAt(bit + 1) - '0');
                } else {
                    // 이 위치에서 끊음 -> 지금까지 만든 조각을 곱에 반영하고 새로 시작
                    mul *= num;
                    num = s.charAt(bit + 1) - '0';
                }
            }
            mul *= num; // 마지막 조각 반영

            int depth;
            if (memo.containsKey(mul)) {
                depth = memo.get(mul);
            } else {
                depth = game(mul);
                memo.put(mul, depth);
            }
            max = Math.max(max, depth);
        }
        return max + 1; // 이번 쪼갬 한 번 + 그 이후 최선의 깊이
    }
}