
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static boolean[] col;      
    static boolean[] v_b1;  // y - x
    static boolean[] v_b2; // y + x
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; testcase++) {
            N = Integer.parseInt(br.readLine());
			col = new boolean[N];
            v_b1 = new boolean[2*N];
            v_b2 = new boolean[2*N];

            int answer = dfs(0);
            System.out.println("#" + testcase + " " + answer);
        }
            
    }

    public static int dfs(int row){
        int cnt = 0;

        if(row == N){
            return 1;
        }

        for(int i = 0; i < N; i++){
            if(col[i]) continue;
            // b1
            int b1 = row - i + N; // 음수 보정
            // b2
            int b2 = row + i;
            if(v_b1[b1]) continue;
            if(v_b2[b2]) continue;

            col[i] = true;
            v_b1[b1] = true;
            v_b2[b2] = true;
            cnt+= dfs(row + 1);
            col[i] = false;
            v_b1[b1] = false;
            v_b2[b2] = false;

        }
        return cnt;
    }
}
