
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int[][] map;
    static int col, row;
    static int posX, posY;
    static int time;
    static int hidecount;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static Queue<int[]> pos;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());
            posY = Integer.parseInt(st.nextToken());
            posX = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());
            map = new int[col][row];
            visited = new boolean[col][row];

            for (int N = 0; N < col; N++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < row; m++) {
                    map[N][m] = Integer.parseInt(st.nextToken());
                }
            }
            hidecount = 1;
            pos = new ArrayDeque<>();

            visited[posY][posX] =true;
                            // 위치 x, 위치 y  시간
            pos.offer(new int[] {posY,posX,1});

           
            while(!pos.isEmpty()) {

                int[]  cur= pos.poll();

                int cury =cur[0];
                int curx =cur[1];
                int checkTime = cur[2];

                if(checkTime==time){
                    break;
                }


                int[] direct = decisionDir(map[cury][curx]);

                for(int d = 0; d< direct.length; d++) {

                    int nx = curx + dx[direct[d]];
                    int ny = cury + dy[direct[d]];

                    if (nx < 0 || ny < 0 || ny >= col || nx >= row|| map[ny][nx]==0 || visited[ny][nx]) continue;

                    if (direct[d] == 1) {

                        if (map[ny][nx] == 4 || map[ny][nx] == 5 || map[ny][nx] ==2) continue;
                        pos.offer(new int[]{ny, nx, checkTime+1});
                        visited[ny][nx]= true;
                        hidecount++;

                    } else if (direct[d] == 0) {
                        if (map[ny][nx] == 6 || map[ny][nx] == 7 || map[ny][nx] ==2) continue;
                        pos.offer(new int[]{ny, nx, checkTime+1});
                        visited[ny][nx]= true;
                        hidecount++;

                    } else if (direct[d] == 2) {
                        if (map[ny][nx] == 7 || map[ny][nx] == 4 || map[ny][nx]==3) continue;
                        pos.offer(new int[]{ny, nx, checkTime+1});
                        visited[ny][nx]= true;
                        hidecount++;

                    } else {
                        if (map[ny][nx] == 6 || map[ny][nx] == 5 || map[ny][nx]==3) continue;
                        pos.offer(new int[]{ny, nx,checkTime+1});
                        visited[ny][nx]= true;
                        hidecount++;

                    }
                }

            }

            System.out.println("#"+t+" "+hidecount);
        }

    }
    static int[] decisionDir(int c) {

        switch (c){
            case 1:
                return new int[]{0,1,2,3};
            case 2:
                return new int[] {2,3};
            case 3:
                return new int[]{0, 1};
            case 4:
                return new int[]{2, 1};
            case 5:
                return new int[]{3, 1};
            case 6:
                return new int[]{3, 0};
            case 7:
                return new int[]{2, 0};
            default:
                return new int[] {};
        }
    }
}

