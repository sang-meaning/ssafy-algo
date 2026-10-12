package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 최적경로 {
    static int n;
    static int[] company, home;
    static int[][] customer;

    static boolean[] visited;
    static int[] seq;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        //고객 수 입력
        n = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());

        company = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        home = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        customer = new int[n][2];

        for(int i=0; i<n; i++){
            for(int j=0; j<2; j++){
                customer[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        visited = new boolean[n];
        seq = new int[n];

        dfs(0);

        System.out.println(min);


    }

    public static void dfs(int count){
        if(count==n){
            int distance = cal_distance();
            min = Math.min(min, distance);
            return;
        }

        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                seq[count] = i;
                dfs(count+1);
                visited[i] = false;
            }
        }

    }




    public static int cal_distance(){
        int current_x = company[0];
        int current_y = company[1];

        int sum = 0;
        for(int i=0; i<n; i++){
            int num = seq[i];

            sum += Math.abs(current_x-customer[num][0]) + Math.abs(current_y-customer[num][1]);
            current_x = customer[num][0];
            current_y = customer[num][1];

        }
        sum += Math.abs(current_x - home[0])+Math.abs(current_y - home[1]);

        return sum;
    }
}

/*
N명의 고객을 방문하고 돌아가려함 (회사에서 출발 -> N명의 고객 방문 -> 집 도착)

회사, 집, 고객 위치 = x,y

두 위치 (x1, y1)와 (x2, y2) 사이의 거리는 |x1-x2| + |y1-y2|으로 계산된다.
여기서 |x|는 x의 절대값을 의미하며 |3| = |-3| = 3이다. 회사의 좌표, 집의 좌표, 고객들의 좌표는 모두 다르다

(회사에서 출발 -> N명의 고객 방문 -> 집 도착)에서 가장 잛은 경로를 찾으려함


입력
1. N (고객의 수)
2. 회사좌표(x, y) 집 좌표(x,y) 고객수만큼 좌표 (x, y)

 */