package com.ssafy.swea;

import java.io.*;
import java.util.*;

public class Solution_2112_김정원 {
    static int[][] film;
    static int d, w, k;
    static int answer;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            // 보호 필름 입력 받기
            film = new int[d][w];
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 연속된 k개의 막에 같은 약품을 투입하면 성능검사가 통과됨
            answer = k;

            // 약품을 투입하지 않은 상태에서 성능검사가 가능한지 확인
            if (check()) answer = 0;
            else dfs(0, 0);

            System.out.println("#" + test_case + " " + answer);
        }
    }

    static void dfs(int idx, int cnt) {
        // 현재 약품 투입 횟수가 구한 최소 횟수 이상이라면 리턴하여 종료
        if (cnt >= answer) return;

        // 모든 막의 약품 투입 여부를 정했다면 성능검사를 진행한다
        if (idx == d) {
            if (check()) answer = cnt;
            return;
        }

        // 약품 투입에서 나올 수 있는 모든 경우의수를 확인한다
        // 먼저 현재 막에 약품을 투입하지 않는 경우
        dfs(idx + 1, cnt);

        // 배열을 하나 만들어서 약품을 투입하기 전 상태를 저장한다
        int[] temp = new int[w];
        for (int i = 0; i < w; i++) {
            temp[i] = film[idx][i];
        }

        // 현재 막에 약품 A를 투입하여 모든 셀의 상태를 변경한다
        for (int i = 0; i < w; i++) film[idx][i] = 0;
        dfs(idx + 1, cnt + 1);

        // 현재 막에 약품 B를 투입하여 모든 셀의 상태를 변경한다
        for (int i = 0; i < w; i++) film[idx][i] = 1;
        dfs(idx + 1, cnt + 1);

        // 다른 경우의수를 확인하기 위해서 약품 투입 전 상태로 변경한다
        for (int i = 0; i < w; i++) {
            film[idx][i] = temp[i];
        }
    }

    static boolean check() {
        // 합격기준이 1이라면 모든 세로방향이 조건을 충족하므로 리턴
        if (k == 1) return true;

        for (int i = 0; i < w; i++) {
            // 같은 특성이 연속된 갯수
            int count = 1;
            boolean flag = false;

            // 위에서 부터 셀의 특성을 확인하면서 연속된 갯수를 구한다
            for (int j = 1; j < d; j++) {
                if (film[j][i] == film[j - 1][i]) count++;
                else count = 1;

                // 같은 특성이 k개 이상 연속된다면 현재 열의 검사 종료
                if (count >= k) {
                    flag = true;
                    break;
                }
            }

            // 현재 열이 합격기준을 충족하지 못했다면 리턴하여 종료
            if (!flag) return false;
        }

        // 모든 세로방향이 성능검사를 통과했다면 리턴
        return true;
    }
}