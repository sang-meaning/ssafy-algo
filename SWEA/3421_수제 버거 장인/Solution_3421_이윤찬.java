import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static long answer;

    static boolean[][] incompatible;
    static boolean[] selected;

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            incompatible = new boolean[N][N];
            selected = new boolean[N];

            for (int i = 0; i < M; i++) {

                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                incompatible[a][b] = true;
                incompatible[b][a] = true;
            }

            answer = 0;

            dfs(0);

            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int index) {

        // 모든 재료의 선택 여부를 결정한 경우
        if (index == N) {
            answer++;
            return;
        }

        // 1. 현재 재료를 선택하지 않는 경우
        dfs(index + 1);

        // 2. 현재 재료를 선택할 수 있는지 확인
        if (canSelect(index)) {

            selected[index] = true;

            dfs(index + 1);

            // 백트래킹
            selected[index] = false;
        }
    }

    static boolean canSelect(int current) {

        // 앞에서 이미 선택한 재료들과 궁합 확인
        for (int i = 0; i < current; i++) {

            if (selected[i] && incompatible[current][i]) {
                return false;
            }
        }

        return true;
    }
}