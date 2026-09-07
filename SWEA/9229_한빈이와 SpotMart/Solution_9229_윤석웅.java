import java.io.*;
import java.util.*;
 
class Solution {
    static int N, M;
    static int[] snacks;
    static int answer;
    static void dfs(int start, int count, int sum) {
        if (count == 2) { // 깊이 2까지만
            if (sum <= M) { // 합이 무게제한까지면
                answer = Math.max(answer, sum);
            }
            return;
        }
 
        for (int i = start; i < N; i++) {
            if (sum + snacks[i] > M) { // 무게제한을 넘어가면 빠져나오기
                continue;
            }
            dfs(i + 1, count + 1, sum + snacks[i]);
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
             
            snacks = new int[N];
            st = new StringTokenizer(br.readLine());
             
            for (int i = 0; i < N; i++) {
                snacks[i] = Integer.parseInt(st.nextToken());
            }
             
            answer = -1;
            dfs(0, 0, 0); // dfs initialize
            sb.append("#").append(tc).append(" ").append(answer).append('\n');
        }
         
        System.out.print(sb);
    }
}