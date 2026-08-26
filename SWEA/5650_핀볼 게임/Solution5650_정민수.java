package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 핀볼 {   //상 하 좌 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int n;
    static int[][] arr;
    static int max = Integer.MIN_VALUE;

    static HashMap<Integer, List<Warm>> warm;
    static class Warm{
        int x;
        int y;

        public Warm(int x, int y){
            this.x = x;
            this.y = y;
        }
    }



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        //웜홀위치저장 map
        warm = new HashMap<>();
        for(int i=6; i<=10; i++){
            warm.put(i, new ArrayList<>());
        }

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                //웜홀 위치 넣기
                if(warm.containsKey(arr[i][j])){
                    warm.get(arr[i][j]).add(new Warm(i, j));
                }

            }
        }





        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                if(arr[i][j] != 0) continue;
                dfs(i, j);
            }
        }

        System.out.println(max);




    }


    public static void dfs(int x, int y){

        for(int i=0; i<4; i++){
            int sum = 0;

            //방향 설정
            int way_x = dx[i];
            int way_y = dy[i];

            int current_x = x;
            int current_y = y;

            while(true){

                //방향만큼 한칸이동
                int nx = current_x + way_x;
                int ny = current_y + way_y;

                //시작점으로 도착하면 종료
                if(nx==x && ny==y){
                    break;
                }

                //벽 닿았을때 변경
                if(nx<0 || nx>=n || ny<0 || ny>=n){
                    if(way_x == dx[0] && way_y == dy[0]){//상
                        way_x = dx[1];
                        way_y = dy[1];
                    }else if(way_x == dx[1] && way_y == dy[1]){//하
                        way_x = dx[0];
                        way_y = dy[0];
                    }else if(way_x == dx[2] && way_y == dy[2]){//좌
                        way_x = dx[3];
                        way_y = dy[3];
                    }else{//우
                        way_x = dx[2];
                        way_y = dy[2];
                    }

                    current_x = nx;
                    current_y = ny;

                    sum++;
                    continue;
                }



                if(arr[nx][ny]!=0) {
                    int num = arr[nx][ny];

                    //블랙홀이면 종료
                    if (num == -1) {
                        break;
                    }



                    //블럭이면
                    if(num >= 1 && num <= 5){
                        switch(num){
                            case(1) -> {
                                if(way_x == dx[0] && way_y == dy[0]){//상
                                    way_x = dx[1];
                                    way_y = dy[1];
                                }else if(way_x == dx[1] && way_y == dy[1]){//하
                                    way_x = dx[3];
                                    way_y = dy[3];
 
                                }else if(way_x == dx[2] && way_y == dy[2]){//좌
                                    way_x = dx[0];
                                    way_y = dy[0];
                                }else{//우
                                    way_x = dx[2];
                                    way_y = dy[2];
                                }

                                sum++;
                                current_x = nx;
                                current_y = ny;
                                continue;
                            }case(2) -> {
                                if(way_x == dx[0] && way_y == dy[0]){//상
                                    way_x = dx[3];
                                    way_y = dy[3];
                                }else if(way_x == dx[1] && way_y == dy[1]){//하
                                    way_x = dx[0];
                                    way_y = dy[0];
                                }else if(way_x == dx[2] && way_y == dy[2]){//좌
                                    way_x = dx[1];
                                    way_y = dy[1];
                                }else{//우
                                    way_x = dx[2];
                                    way_y = dy[2];
                                }
                                sum++;
                                current_x = nx;
                                current_y = ny;
                                continue;
                            }case(3) -> {
                                if(way_x == dx[0] && way_y == dy[0]){//상
                                    way_x = dx[2];
                                    way_y = dy[2];
                                }else if(way_x == dx[1] && way_y == dy[1]){//하
                                    way_x = dx[0];
                                    way_y = dy[0];
                                }else if(way_x == dx[2] && way_y == dy[2]){//좌
                                    way_x = dx[3];
                                    way_y = dy[3];
                                }else{//우
                                    way_x = dx[1];
                                    way_y = dy[1];
                                }
                                sum++;
                                current_x = nx;
                                current_y = ny;
                                continue;
                            }case(4) -> {
                                if(way_x == dx[0] && way_y == dy[0]){//상
                                    way_x = dx[1];
                                    way_y = dy[1];
                                }else if(way_x == dx[1] && way_y == dy[1]){//하
                                    way_x = dx[2];
                                    way_y = dy[2];
                                }else if(way_x == dx[2] && way_y == dy[2]){//좌
                                    way_x = dx[3];
                                    way_y = dy[3];
                                }else{//우
                                    way_x = dx[0];
                                    way_y = dy[0];
                                }
                                sum++;
                                current_x = nx;
                                current_y = ny;
                                continue;
                            }case(5) -> {
                                if(way_x == dx[0] && way_y == dy[0]){//상
                                    way_x = dx[1];
                                    way_y = dy[1];
                                }else if(way_x == dx[1] && way_y == dy[1]){//하
                                    way_x = dx[0];
                                    way_y = dy[0];
                                }else if(way_x == dx[2] && way_y == dy[2]){//좌
                                    way_x = dx[3];
                                    way_y = dy[3];
                                }else{//우
                                    way_x = dx[2];
                                    way_y = dy[2];
                                }
                                sum++;
                                current_x = nx;
                                current_y = ny;
                                continue;
                            }
                        }
                    }



                    // 웜홀 이동
                    if(num >= 6){
                        for(Warm w : warm.get(num)){
                            if(w.x != nx || w.y != ny){
                                current_x = w.x;
                                current_y = w.y;
                                break;
                            }
                        }
                        continue;
                    }


                }

                current_x = nx;
                current_y = ny;


            }


            max = Math.max(max, sum);
        }


    }
}

/*
N*N의 게임판

정사각형블록과 4가즤 형태의 삼각형, 웜홀, 블랙홀

핀볼은 상하좌우 중 한 방향으로 움직임
블록이나 웜홀,블랙홀을 만나지 않는 한 방향을 유지하며 직진
블록의 수평면, 수직을 만날경우 반대방향으로, 경사면을 만나면 직각으로 꺾임
끝벽을 만날경우에도 반대방향으로 돌아옴

웜홀에 빠지면 반대편 동일한숫자의 웜홀로 나오고 방향은 유지 / 웜홀 번호 = 6이상 10이하
핀볼이 블랙홀을 만나면 게임 끝

 */



