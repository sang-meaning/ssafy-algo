import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static long answer;

    // incompatible[i]의 각 비트는 i번 재료와 함께 사용할 수 없는 재료를 의미
    static int[] incompatible;

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            incompatible = new int[N];

            for (int i = 0; i < M; i++) {

                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                // a와 b는 서로 같이 사용할 수 없음
                incompatible[a] |= (1 << b);
                incompatible[b] |= (1 << a);
            }

            answer = 0;

            dfs(0, 0);

            System.out.println("#" + tc + " " + answer);
        }
    }

    /**
     * index        : 현재 선택 여부를 결정할 재료 번호
     * selectedMask : 지금까지 선택한 재료들
     */
    static void dfs(int index, int selectedMask) {

        // 모든 재료의 선택 여부를 결정한 경우
        if (index == N) {
            answer++;
            return;
        }

        // 1. index번 재료를 선택하지 않는 경우
        dfs(index + 1, selectedMask);

        // 2. index번 재료를 선택하는 경우
        // 현재 선택된 재료와 궁합이 맞지 않는 재료가 없어야 함
        if ((selectedMask & incompatible[index]) == 0) {
            dfs(index + 1, selectedMask | (1 << index));
        }
    }
}