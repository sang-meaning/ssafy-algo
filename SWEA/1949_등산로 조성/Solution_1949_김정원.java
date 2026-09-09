package ssafy.swea.kjw;

import java.io.*;
import java.util.*;

public class Solution_1949_김정원 {
	static int N,K,answer;
	static int[][] map;
	static boolean[][] visited;
	static List<int[]> startLoc;
	
	static int[] dx;
	static int[] dy;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		dx = new int[] {0,1,0,-1};
		dy = new int[] {1,0,-1,0};
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int max = -1;
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			startLoc = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = max <= map[i][j] ? map[i][j] : max;
				}
			}
			// 시작할 지점 startLoc 에 저장
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) {
						startLoc.add(new int[] {j,i});
					}
				}
			}
			// 시작지점에서 출발하여 최대로 갈 수 있는 등산로의 길이를 구하기
			for (int i = 0; i < startLoc.size(); i++) {
				//System.out.println(Arrays.toString(startLoc.get(i)));
				// walk 는 탐색한 등산로 길이를 반환하고
				// 이 길이는 시작점에서 K 를 반영한 최대 길이를 반환 해야한다
				int x = startLoc.get(i)[0];
				int y = startLoc.get(i)[1];
				visited[y][x] = true;
				walk(1,x,y,false);
				visited[y][x] = false;
			}
			System.out.println(String.format("#%d %d", test_case, answer));
			answer = 0;
		}
	}
	
	// 깊이 우선 탐색으로 하지만 조건에 맞지 않을 경우 버린다
	static void walk(int distance, int x, int y, boolean isUsed) {
		if (answer < distance) {
			answer = distance;
		}
		int nx;
		int ny;
		// 탐색시 k 를 사용할 수 있는지 아닌지 여부를 확인하기
		int cur = map[y][x];
		int next;
		//System.out.println(String.format("(%d,%d) %d", x,y,distance));
		for (int d = 0; d < 4; d++) {
			nx = x + dx[d];
			ny = y + dy[d]; 
			
			// 이동하려는 위치가 맵 밖이라면
			if (!isMapIn(nx,ny)) continue;
			next = map[ny][nx];
			// 방문했다면
			if (visited[ny][nx]) continue;
			// 다음에 이동하려는 위치가 경사가 더 낮다면
			if (cur > next) {
				visited[ny][nx] = true;
				walk(distance + 1, nx, ny, isUsed);
				visited[ny][nx] = false;
			// 다음에 이동하려는 지형이 더 높다면 
			// K 를 사용했었는지 확인하기 false : 미사용, true : 사용했음
			} else if (!isUsed) {
				// K 를 사용해도 이동하지 못한다면 넘기기
				if (!isKMove(x,y,nx,ny)) continue;
				visited[ny][nx] = true;
				int temp = map[ny][nx];
				map[ny][nx] = map[y][x] - 1;
				walk(distance + 1, nx, ny, true);
				map[ny][nx] = temp;
				visited[ny][nx] = false;				
			}
	
			// printt();
		}
	}
	
	static void printt() {
		for (int yy = 0; yy < N; yy++) {
			for (int xx = 0; xx < N; xx++) {
				System.out.print(visited[yy][xx] ? 'O' : 'X');
			}
			System.out.println();
		}
		System.out.println("-".repeat(N));
	}
	
	static boolean isMapIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	static boolean isKMove(int x, int y, int nx, int ny) {
		for (int k = 1; k <= K; k++) {
			//System.out.println(String.format("(%d,%d) -> (%d,%d)", x,y,nx,ny));
			if (map[ny][nx] - k == map[y][x] - 1) {
				return true;
			}
		}
		return false;
	}
}
