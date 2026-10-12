import java.util.*;
import java.io.*;
class Solution {
    static int N, max, many;
    static int[][] arr;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            /**
                한 변의 길이 N 정사각형 치즈
                맛있는 정도 1 ~ 100
                100일동안 치즈 먹는데 X 번째 날에는 맛있는 정도가 X인 칸을 먹는다.
                치즈 덩어리 상하좌우 묶음
            */
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            max = 0;
            many = 1;
            for(int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j] > max) max = arr[i][j];
                }
            }
            cheese(1);
            System.out.println("#"+test_case+" "+many);
        }
    }
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void cheese(int time){
        if(time == max) return;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] == time) arr[i][j] = 0;
            }
        }
        eatCheese();
        cheese(time+1);
    }
    public static void eatCheese(){
        int cnt = 0;
        boolean[][] visited = new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] == 0 || visited[i][j]) continue;
                cnt++;
                visited[i][j] = true;
                dfs(i, j, visited);
            }
        }
        if(many < cnt ) many = cnt;
    }
    public static void dfs(int x, int y, boolean[][] visited){
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(visited[nx][ny] || arr[nx][ny] == 0) continue;
            visited[nx][ny] = true;
            dfs(nx, ny, visited);
        }
    }
}