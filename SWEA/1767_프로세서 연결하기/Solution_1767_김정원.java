package ssafy.swea.kjw;

import java.io.*;
import java.util.*;

public class Solution_1767_김정원 {
	static int N;
	static int[][] map;
	static List<int[]> coreList;
	static int maxCore;
	static int minLine;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			coreList = new ArrayList<>();
			maxCore = 0;
			minLine = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					// 가장자리에 있는 코어는 이미 연결되어 있으므로 따로 확인하지 않음
					if (map[i][j] == 1 
							&& i != 0 && i != N - 1
							&& j != 0 && j != N - 1) {
						coreList.add(new int[] {i, j});
					}
				}
			}
			
			// 코어를 하나씩 확인하면서 전선을 연결해보기
			connectCore(0, 0, 0);
			
			System.out.println("#" + test_case + " " + minLine);
		}
	}
	
	static void connectCore(int idx, int coreCnt, int lineCnt) {
		// 남은 코어를 모두 연결해도 현재 최대 갯수보다 작은 경우
		if (coreCnt + coreList.size() - idx < maxCore) {
			return;
		}
		
		if (idx == coreList.size()) {
			// 더 많은 코어를 연결한 경우
			if (coreCnt > maxCore) {
				maxCore = coreCnt;
				minLine = lineCnt;
			}
			// 연결한 코어의 갯수가 같으면 전선 길이가 짧은 것으로 저장
			else if (coreCnt == maxCore) {
				minLine = Math.min(minLine, lineCnt);
			}
			
			return;
		}
		
		int[] core = coreList.get(idx);
		int y = core[0];
		int x = core[1];
		
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			int length = 0;
			boolean isConnect = true;
			
			// 현재 방향으로 가장자리까지 연결할 수 있는지 확인
			while (isMapIn(ny, nx)) {
				if (map[ny][nx] != 0) {
					isConnect = false;
					break;
				}
				
				ny += dy[d];
				nx += dx[d];
			}
			
			if (!isConnect) {
				continue;
			}
			
			ny = y + dy[d];
			nx = x + dx[d];
			
			// 전선을 연결한 위치를 2로 표시
			while (isMapIn(ny, nx)) {
				map[ny][nx] = 2;
				length++;
				
				ny += dy[d];
				nx += dx[d];
			}
			
			connectCore(idx + 1, coreCnt + 1, lineCnt + length);
			
			ny = y + dy[d];
			nx = x + dx[d];
			
			// 다음 경우도 확인해야하므로 연결했던 전선을 다시 제거
			while (isMapIn(ny, nx)) {
				map[ny][nx] = 0;
				
				ny += dy[d];
				nx += dx[d];
			}
		}
		
		// 현재 코어를 연결하지 않는 경우도 확인
		connectCore(idx + 1, coreCnt, lineCnt);
	}
	
	static boolean isMapIn(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
}