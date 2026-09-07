import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0 , -1, 0};
        int answer = -1;
        int N = maps.length;
        int M = maps[0].length;
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int len = cur[2];
            if(x == N-1 && y == M-1){
                answer = len;
                break;
            }
            for(int d = 0; d < 4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                    continue;
                }
                if(visited[nx][ny] == true){
                    continue;
                }
                if(maps[nx][ny] == 0){
                    continue;
                }
                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny, len + 1});
            }
        }
        return answer;
    }
}