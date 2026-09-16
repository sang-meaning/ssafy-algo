import java.util.*;
import java.io.*;
class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++) {
			/**
            	100x100의 행렬
                시작점에서 도착점으로 갈 수 있는가?
                테스트 케이스에서 1은 벽을 나타내며 0은 길, 2는 출발점, 3은 도착점을 나타낸다.
            */
            int testNum = Integer.parseInt(br.readLine());
            int[][] matrix = new int[100][100];
            boolean[][] visited = new boolean[100][100];
            int[] start = new int[2];
            int[] end = new int[2];
            for(int i=0; i<100; i++){
                String[] splits = br.readLine().split("");
                for(int j=0; j<100; j++){
                    matrix[i][j] = Integer.parseInt(splits[j]);
                    if(matrix[i][j] == 2) start = new int[]{i, j};
                    else if(matrix[i][j] == 3) end = new int[]{i, j};
                }
            }
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(start);
            int result = 0;
            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};
            while(!queue.isEmpty() && result !=1){
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                visited[x][y] = true;
                for(int i=0; i<4; i++){
                    int nx = x+dx[i];
                    int ny = y+dy[i];
                    if(nx < 0 || ny < 0 || nx >= 100 || ny >= 100) continue;
                    if(matrix[nx][ny] == 1) continue;
                    if(visited[nx][ny]) continue;
                    if(nx == end[0] && ny == end[1]) {
                        result = 1;
                        break;
                    }
                    queue.add(new int[]{nx, ny});
                }
            }
            System.out.println("#"+testNum+" "+result);
		}
	}
}