package ysson.SWEA.모의sw역량테스트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class S4012 {
    static int N;
    static int[][] s;
    static int[] isA;
    static int minDiff;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int testcase = 1; testcase <= T; testcase++){
            N = Integer.parseInt(br.readLine())+1;
            s = new int[N][N];
            isA = new int[N];
            minDiff = Integer.MAX_VALUE;
            
            for(int i = 1; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 1; j <N; j++){
                    s[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            isA[1] = 1;
            minDiff = dfs(1, 1);

            System.err.println("#" + testcase + " " + minDiff);
        }
    }
    // A 조합 만드는 재귀
    public static int dfs(int n, int dept) {
        if(dept == (N-1)/2){
            return Math.min(diff(isA), minDiff);
        }
        // 다음거
        for(int i = n+1; i < N; i++){
            if(i < N && isA[i] == 0){
                isA[i] = 1;
                minDiff = dfs(i, dept + 1);
                isA[i]=0;
            }
            
        }
        return minDiff;
    }
    // diff 계산
    public static int diff(int[] isA){
        int s_A = 0;
        int s_B = 0;

        for(int i = 1; i<N; i++){
            for(int j = 1; j<N; j++){
                if (isA[i] == 1 && isA[j] == 1){
                    s_A += s[i][j];
                }
                else if(isA[i] == 0 && isA[j] == 0){
                    s_B += s[i][j];
                }
            }
        }
        return Math.abs(s_A - s_B);
    }
}

