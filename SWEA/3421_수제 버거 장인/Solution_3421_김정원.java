package com.ssafy.swea;

import java.io.*;
import java.util.*;

public class Solution_3421_김정원 {
    static int n, m;
    static boolean[][] bad;
    static boolean[] selected;
    static int answer;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            bad = new boolean[n + 1][n + 1];
            selected = new boolean[n + 1];
            answer = 0;

            // 같이 들어가면 안되는 재료를 입력 받아서 저장한다
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                bad[a][b] = true;
                bad[b][a] = true;
            }

            dfs(1);

            System.out.println("#" + test_case + " " + answer);
        }
    }

    static void dfs(int idx) {
        // 모든 재료의 선택 여부를 정했다면 버거의 종류를 증가
        // 재료를 하나도 넣지 않은 경우도 포함됨
        if (idx > n) {
            answer++;
            return;
        }

        // 재료 선택에서 나올 수 있는 모든 경우의수를 확인한다
        // 먼저 현재 재료를 넣지 않는 경우
        dfs(idx + 1);

        // 현재 선택한 재료들과 같이 넣을 수 있는지 확인
        if (!check(idx)) return;

        // 현재 재료를 넣는 경우
        selected[idx] = true;
        dfs(idx + 1);

        // 다른 경우의수를 확인하기 위해서 선택을 해제한다
        selected[idx] = false;
    }

    static boolean check(int idx) {
        // 현재 재료 이전까지 선택한 재료들을 확인한다
        for (int i = 1; i < idx; i++) {
            // 선택한 재료가 아니라면 넘어간다
            if (!selected[i]) continue;

            // 같이 들어가면 안되는 재료라면 리턴하여 종료
            if (bad[idx][i]) return false;
        }

        return true;
    }
}