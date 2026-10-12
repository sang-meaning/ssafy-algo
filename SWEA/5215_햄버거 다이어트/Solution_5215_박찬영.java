import java.io.*;
import java.util.*;
 
public class Solution {
    static int n, limitCal;
    static int[] scores;
    static int[] calories;
    static int maxScore;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine().trim());
 
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());        // 재료의 수
            limitCal = Integer.parseInt(st.nextToken()); // 제한 칼로리
 
            scores = new int[n];
            calories = new int[n];
            maxScore = 0;
 
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                scores[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
            }
 
            dfs(0, 0, 0);
 
            sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
        }
 
        System.out.print(sb);
    }
 
    static void dfs(int idx, int currentScore, int currentCal) {
         
        if (currentCal > limitCal) {
            return;
        }
        if (idx == n) {
            maxScore = Math.max(maxScore, currentScore);
            return;
        }
 
        dfs(idx + 1, currentScore + scores[idx], currentCal + calories[idx]);
 
        dfs(idx + 1, currentScore, currentCal);
    }
}