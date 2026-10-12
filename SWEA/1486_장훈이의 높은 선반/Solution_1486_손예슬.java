import java.util.Scanner;

public class Solution1486 {
    static int minSum;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int B = sc.nextInt();
            int[] p = new int[N];
            for (int i = 0; i < N; i++) p[i] = sc.nextInt();

            minSum = Integer.MAX_VALUE;
            go(0, 0, p, N, B);

            sb.append("#").append(tc).append(" ").append(minSum - B).append("\n");
        }
        System.out.print(sb);
    }

    // depth: 몇 번째 사람까지 결정했는지, sum: 지금까지 쌓은 키의 합
    static void go(int depth, int sum, int[] p, int N, int B) {
        // 이미 B를 넘긴 상태라도 더 쌓으면 답이 나빠지기만 하므로 그대로 진행은 하되,
        // 끝에서만 최소값 비교
        if (depth == N) {
            if (sum >= B && sum < minSum) {
                minSum = sum;
            }
            return;
        }
        go(depth + 1, sum + p[depth], p, N, B); // 이 사람을 포함
        go(depth + 1, sum, p, N, B);             // 포함하지 않음
    }
}