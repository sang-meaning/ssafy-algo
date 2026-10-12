import java.io.*;
import java.util.*;

public class Solution_3421_정영훈 {

    static int n, answer;
    static boolean[][] conflict;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            conflict = new boolean[n + 1][n + 1];
            selected = new boolean[n + 1];
            answer = 0;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                conflict[a][b] = true;
                conflict[b][a] = true;
            }

            dfs(1);

            sb.append("#").append(test_case).append(" ")
              .append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int index) {
        if (index > n) {
            answer++;
            return;
        }

        // 현재 재료를 선택하지 않는 경우
        dfs(index + 1);

        // 이미 선택한 재료와 함께 사용할 수 있는지 확인
        for (int i = 1; i < index; i++) {
            if (selected[i] && conflict[index][i]) {
                return;
            }
        }

        // 현재 재료를 선택하는 경우
        selected[index] = true;
        dfs(index + 1);
        selected[index] = false;
    }
}