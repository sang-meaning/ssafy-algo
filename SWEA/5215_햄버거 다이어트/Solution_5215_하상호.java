package swea;

import java.io.*;
import java.util.*;

public class Solution_5215_하상호 {
    static int N, L;
    static int[] score;
    static int[] calorie;
    static int maxScore;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            score = new int[N];
            calorie = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                score[i] = Integer.parseInt(st.nextToken());
                calorie[i] = Integer.parseInt(st.nextToken());
            }

            maxScore = 0;

            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + maxScore);
        }
    }

    static void dfs(int idx, int totalScore, int totalCalorie) {

        // 칼로리 제한 초과
        if (totalCalorie > L) {
            return;
        }

        // 모든 재료 확인 완료
        if (idx == N) {
            maxScore = Math.max(maxScore, totalScore);
            return;
        }

        // 현재 재료 선택
        dfs(
            idx + 1,
            totalScore + score[idx],
            totalCalorie + calorie[idx]
        );

        // 현재 재료 선택하지 않음
        dfs(
            idx + 1,
            totalScore,
            totalCalorie
        );
    }
}