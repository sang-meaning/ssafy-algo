package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 특이한자석 {

    static List<Deque<Integer>> list;
    static int k;
    static int[][] rotate_info;

    static int[][] point; //왼쪽, 오른쪽 인덱스 극 저장
    static boolean[] visited;

    public static void main(String[] args) throws IOException{

        list = new ArrayList<>();



        for(int i=0; i<4; i++){
            list.add(new ArrayDeque<>());
        }


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 회전 횟수
        k = Integer.parseInt(st.nextToken());

        //회전 정보
        rotate_info = new int[k][2];

        //자석 정보 입력
        for(int i=0; i<4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<8; j++){
                list.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        //회전 정보 입력
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int way = Integer.parseInt(st.nextToken());
            rotate_info[i][0] = number;
            rotate_info[i][1] = way;
        }


        //회전
        for(int i=0; i<k; i++){
            int number = rotate_info[i][0];
            int way = rotate_info[i][1];

            //회전시키기 전에 4개자석 양쪽 인덱스 극 저장
            point = new int[5][2]; //1, 2, 3, 4 자석
            visited = new boolean[5]; //1, 2, 3, 4 자석 방문
            for(int j=0; j<4; j++){

                List<Integer> queue_list = new ArrayList<>(list.get(j));
                point[j+1][0] = queue_list.get(6);//왼쪽
                point[j+1][1] = queue_list.get(2);//오른쪽
            }

            rotate(number, way);



        }


        int sum = 0;
        int num = 1;
        //계산 n=0 s=1
        for(int i=0; i<4; i++){
            List<Integer> queue_list = new ArrayList<>(list.get(i));

            if(queue_list.getFirst() == 1){
                sum += num;
            }

            num*=2;
        }

        System.out.println(sum);






    }

    public static void rotate(int number, int way){


        if(visited[number]) return;

        visited[number] = true;
        //반시계방향 회전
        if(way == -1){
            int num = list.get(number-1).poll();

            list.get(number-1).add(num);
        }else{//시계방향 회전
            int num = list.get(number-1).pollLast();

            list.get(number-1).addFirst(num);
        }

        //다음자석은 현재자석 way 반대방향으로 회전
        int w = 0;
        if(way == 1) w = -1;
        else w = 1;


        //재귀
        if(number==1){
            if(point[number][1] != point[number+1][0] && !visited[number+1]){

                rotate(number+1, w);

            }
        }else if(number==4){
            if(point[number][0] != point[number-1][1] && !visited[number-1]){
                rotate(number-1, w);
            }

        }else{
            if(point[number][1] != point[number+1][0] && !visited[number+1]){
                rotate(number+1, w);
            }
            if(point[number][0] != point[number-1][1] && !visited[number-1]){
                rotate(number-1, w);
            }


        }

    }

}

/*
4개의 자석 (각 자석에 8개의 날) 날 = N or S 극

4개의 자석 위엔 빨간화살표
무작위로 자석을 돌려 모든 회전이 끝났을때, 빨간화살표에 있는 극이
1번자석 N=0, S=1
2번자석 N=0, S=2
3번자석 N=0, S=4
4번자석 N=0, S=8
만큼 점수획득

입력정보
1. k (k번 회전)
2. 4줄의 각 자석 정보 (0 = n극,  1 = s극)
3. k줄만큼의 회전 정보 number (자석번호), 회전방향 (1=시계방향, -1=반시계방향)
 */