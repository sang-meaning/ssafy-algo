import java.util.Scanner;

public class Solution_5215_한석호 {
    static int N, L;
    static int[][] ingri;
    static int results;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            L = sc.nextInt();

            ingri = new int[N][2];
            for (int i = 0; i < N; i++) {
                ingri[i][0] = sc.nextInt(); // 점수
                ingri[i][1] = sc.nextInt(); // 칼로리
            }

            results = 0;
            dfs(0, 0, 0);

            System.out.println("#" + test_case + " " + results);
        }
        sc.close();
    }

    // index: 현재 버거 재료 index
    // compare: 정답이랑 비교할 점수
    // current_cal: 현재 칼로리
    static void dfs(int index, int compare, int current_cal) {
        // 백트래킹: 제한 칼로리를 초과하면 탐색 종료
        if (current_cal > L) {
            return;
        }

        // 모든 재료를 다 확인했을 때 최대 점수 갱신
        if (index == N) {
            results = Math.max(results, compare);
            return;
        }

        // 1. 현재 재료를 선택하는 경우
        dfs(index + 1, compare + ingri[index][0], current_cal + ingri[index][1]);

        // 2. 현재 재료를 선택하지 않는 경우
        dfs(index + 1, compare, current_cal);
    }
}