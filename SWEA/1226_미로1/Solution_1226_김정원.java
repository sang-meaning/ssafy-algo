package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1226_김정원 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 미로 16x16
		int[][] miro = new int[16][16];
		Deque<int[]> queue = new ArrayDeque<>();
		int[] start = null;
		int[] destination = null;
		int[] dx = {0,1,0,-1};
		int[] dy = {-1,0,1,0};
		int nx = 0;
		int ny = 0;

		
		for (int i = 0; i < 10; i++) {
			int T = Integer.parseInt(br.readLine());
			int answer = 0;
			for (int j = 0; j < 16; j++) {
				String[] row = br.readLine().split("");
				for (int k = 0; k < 16; k++) {
					// 미로 그리기
					miro[j][k] = Integer.parseInt(row[k]);
					if (miro[j][k] == 2) {
						// 시작 지점 큐에 넣기
						start = new int[] {k, j};
						queue.add(start);
					} else if (miro[j][k] == 3) {
						// 도착지 저장
						destination = new int[] {k, j};
					}
				}
			}
			// 출발지에서 출발하여
			while (!queue.isEmpty()) {
				// 이동할 다음 큐에서 꺼내기x
				int[] cur = queue.pollFirst();
				// 방문 처리
				miro[cur[1]][cur[0]] = 4;
				// 현재 도착지인지 확인
				if (cur[0] == destination[0] && cur[1] == destination[1]) {
					answer = 1;
					break;
				}
				// 네 방향으로 이동 가능한지 확인
				// 조건 1. 벽이 아니여야함
				// 조건 2. 방문한적이 없어야함
				for (int d = 0; d < dx.length; d++) {
					nx = cur[0] + dx[d];
					ny = cur[1] + dy[d];
					// 이동 가능시
					if (miro[ny][nx] == 0 ||miro[ny][nx] == 3) {
						// 이동할 대기 큐에 넣기
						queue.add(new int[] {nx,ny});
					}
				}
				//System.out.println(Arrays.toString(queue.getFirst()));
			}
			System.out.println(String.format("#%d %d", T, answer));
		}
	}
}
