import java.util.*;
import java.io.*;
class Solution {
    static int N, W, H, min;
    static int[][] init;
	public static void main(String args[]) throws Exception	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)	{
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            init = new int[H][W];
            int initCount = 0;
            for(int i=0; i<H; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++) {
                    init[i][j] = Integer.parseInt(st.nextToken());
                    if(init[i][j] > 0) initCount++;
                }
            }
            // N < 4
            min = initCount;
            dfs(0, new ArrayList<>());
            System.out.println("#"+test_case+" "+min);
		}
	}
    
    public static void dfs(int depth, List<Integer> choose){
        if(depth==N) {
            shoot(choose);
            return;
        }
        for(int i=0; i<W; i++) {
            choose.add(i);
        	dfs(depth+1, choose);
        	choose.remove(choose.size()-1);
        }
    }
    public static int[][] copyMatrix(){
        int[][] temp = new int[H][W];
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) temp[i][j] = init[i][j];
        }
        return temp;
    }
    public static void cleaning(boolean[][] visited, int[][] matrix){
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                if(visited[i][j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    public static void compacting(int[][] matrix) {
        for (int w = 0; w < W; w++) {
            int bottom = H - 1;
            for (int h = H - 1; h >= 0; h--) {
                if (matrix[h][w] > 0) {
                    int value = matrix[h][w];
                    matrix[h][w] = 0;
                    matrix[bottom][w] = value;
                    bottom--;
                }
            }
        }
    }
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};
    public static void shoot(List<Integer> choose){
        int[][] temp = copyMatrix();
        for(int width : choose){
            int height = -1;
            for(int h=0; h<H; h++) {
                if(temp[h][width] > 0) {
                    height = h;
                    break;
                }
            }
            if(height == -1) continue; // 한 라인이 0
            if(temp[height][width]==1) {
                temp[height][width] = 0;
                continue;
            }
            boolean[][] visited = new boolean[H][W];
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{height, width});
			visited[height][width] = true;
            
            while(!queue.isEmpty()){
                int[] p = queue.poll();
                int x = p[0];
                int y = p[1];
                int demage = temp[x][y];
                visited[x][y] = true;
                for (int i = 1; i < demage; i++) {
                    for(int j=0; j<4; j++){
                        int nx = x+dx[j]*i;
                        int ny = y+dy[j]*i;
                        if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                        if (temp[nx][ny] > 1 && !visited[nx][ny]) queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            cleaning(visited, temp);
            compacting(temp);
        }
        int count = 0;
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                if(temp[i][j] > 0) count++;
            }
        }
        min = Math.min(min, count);
    }
}