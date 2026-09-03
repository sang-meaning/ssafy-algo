import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int answer = 0;

        Queue<int[]> queue = new ArrayDeque<>();

        char[][] maze = new char[maps.length][maps[0].length()];
        boolean[][] visited = new boolean[maps.length][maps[0].length()];

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int lever = 0;


        int leverX = 0;
        int leverY = 0;


        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {

                maze[i][j] = maps[i].charAt(j);

                if (maze[i][j] == 'S') {
                    queue.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }


        while (!queue.isEmpty()) {

            int[] now = queue.poll();

            int x = now[0];
            int y = now[1];
            int count = now[2];

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0|| nx >= maps.length || ny >= maps[0].length() || maze[nx][ny] == 'X' || visited[nx][ny]) {
                    continue;
                }

                if (maze[nx][ny] == 'L') {

                    lever = count + 1;

                    leverX = nx;
                    leverY = ny;

                    break;
                }

                visited[nx][ny] = true;

                queue.offer(new int[]{
                    nx,
                    ny,
                    count + 1
                });
            }


            if (lever != 0) {
                break;
            }
        }



        if (lever == 0) {
            return -1;
        }





        queue.clear();


        visited = new boolean[maps.length][maps[0].length()];


        queue.offer(new int[]{
            leverX,
            leverY,
            0
        });

        visited[leverX][leverY] = true;


        while (!queue.isEmpty()) {

            int[] now = queue.poll();

            int x = now[0];
            int y = now[1];
            int count = now[2];

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length() || maze[nx][ny] == 'X' || visited[nx][ny]) {
                    continue;
                }

                if (maze[nx][ny] == 'E') {

                    answer = lever + count + 1;

                    return answer;
                }

                visited[nx][ny] = true;

                queue.offer(new int[]{
                    nx,
                    ny,
                    count + 1
                });
            }
        }

        return -1;
    }
}