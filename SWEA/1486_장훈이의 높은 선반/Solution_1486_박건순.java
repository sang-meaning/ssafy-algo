import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static int N;
    static int B;
    static int[] heightArr;
    static int minDiff;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            heightArr = new int[N];
            minDiff = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                heightArr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            System.out.println("#" + test_case + " " + minDiff);
        }
    }

    static void dfs(int start, int sum) {

        if (sum >= B) {
            minDiff = Math.min(minDiff, sum - B);
            return;
        }

        for (int i = start; i < N; i++) {
            dfs(i + 1, sum + heightArr[i]);
        }
    }
}