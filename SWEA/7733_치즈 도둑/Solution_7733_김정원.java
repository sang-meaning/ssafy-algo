package ssafy.swea.kjw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_7733_김정원 {
	static int N;
	static int[][] cheese;
	static List<Integer> target_cheese;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
        	N = Integer.parseInt(br.readLine());
        	cheese = new int[N][N];
        	target_cheese = new ArrayList<>();
        	boolean[] selected_cheese = new boolean[101];
        	for (int i = 0; i < N; i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < N; j++) {
        			cheese[i][j] = Integer.parseInt(st.nextToken());
        			selected_cheese[cheese[i][j]] = true;
        		}
        	}
        	// 댓글에 보면 한 덩어리인것도 처리해줘야한다고함
        	if (checkOneChuck()) {
                System.out.println("#" + testCase + " " + 1);
                continue;
            }
        	// 갉아먹을 치즈들을 미리 골라냄
        	for (int n = 1; n <= 100; n++) {
        		if(selected_cheese[n] == false) continue;
        		target_cheese.add(n);
        	}
        	int max = 0;
        	for (int t : target_cheese) {
            	// t 시간이 t 인 치즈는 갉아 먹기
        		// 갉아 먹은 후 남은 치즈의 좌표들을 담아둠
        		Queue<int[]> remainingCheese = eatCheese(cheese, t);
        		// 치즈 덩어리가 몇개인지 세기
        		int count = countCheeseChunk(cheese, remainingCheese);
        		max = max < count ? count : max;
        	}
            System.out.println("#" + testCase + " " + max);
        }
    }
    
    static boolean checkOneChuck() {
    	int target = cheese[0][0];
    	boolean flag = true;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (target != cheese[i][j]) {
					flag = false;
					break;
				}
			}
		}
		return flag;
    }
    
    static void deepArrCopy(int[][] ori, int[][] copy) {
		for(int i = 0; i < ori.length; i++) {
			for(int j = 0; j < ori.length; j++) {
				copy[i][j] = ori[i][j];
			}
		}
    }
    
    static Queue<int[]> eatCheese(int[][] cheese, int target) {
    	Queue<int[]> remainingCheese = new LinkedList<>();
		for(int i = 0; i < cheese.length; i++) {
			for(int j = 0; j < cheese.length; j++) {
				if (cheese[i][j] == target) {
					cheese[i][j] = -1;
				} else if(cheese[i][j] != -1) {
					remainingCheese.add(new int[] {j, i});
				}
			}
		}
		return remainingCheese;
    }
    
    static int countCheeseChunk(int[][] cheese, Queue<int[]> remainingCheese) {
    	boolean[][] visited = new boolean[N][N];
    	int x;
    	int y;
    	int nx;
    	int ny;
    	// 남은 치즈 갯수에서 이미 방문했다면 하나씩 뺄 예정이다
    	// 5x5 치즈 판에서 갉아먹은 치즈가 없다고 가정하면
    	// 정답은 1 덩어리이다
    	// 처음 어디를 시작하든 count 는 25일거고
    	// 아래 큐가 다 돌면 24 번 빼서 1이 나온다
    	int count = 0;
    	for (int[] c : remainingCheese) {
    		if (visited[c[1]][c[0]] == true) continue;
    		Queue<int[]> queue = new LinkedList<>();
    		queue.add(c);
    		visited[c[1]][c[0]] = true;
    		count++;
    		while (!queue.isEmpty()) {
    			int[] chuck = queue.poll();
    			x = chuck[0];
    			y = chuck[1];
    			

    			for (int d = 0; d < 4; d++) {
    				nx = x + dx[d];
    				ny = y + dy[d];
    				// 맵 밖이거나
    				// 방문했거나
    				// 이미 갉아 먹은 치즈라면
    				if (!isMapIn(nx, ny) || visited[ny][nx] || cheese[ny][nx] == -1) continue;
        			// 이미 확인했었던 치즈라면
        			// 전체 치즈 덩어리 갯수에서 하나를 뺀다
    				queue.add(new int[] {nx,ny});
        			// 방문 처리
        			visited[ny][nx] = true;
    			}
    		}
    	}
    	return count;
    }
    
    static boolean isMapIn(int x, int y) {
    	return x >= 0 && x < N && y >= 0 && y < N;
    }
}