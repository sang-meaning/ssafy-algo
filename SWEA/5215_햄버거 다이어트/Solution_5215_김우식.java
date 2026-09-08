package org.example;

import java.util.*;
import java.io.*;

public class swea5215 {

    static int N;
    static int L;
    static int[] score;
    static int[] cal;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T =  Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L =  Integer.parseInt(st.nextToken());

            score = new int[N];
            cal = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                score[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
            }

            max = 0;

            dfs(0,0,0);

            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int idx, int totalScore, int totalCal){

        if(totalCal > L){
            return;
        }

        if(idx == N){
            max = Math.max(max,totalScore);
            return;
        }

        dfs(idx+1,totalScore+score[idx],totalCal+cal[idx]);

        dfs(idx+1,totalScore,totalCal);
    }
}
