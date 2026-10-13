package _submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	// 운영비용: K * K + (K - 1) * (K - 1)

	// 보안회사에서는 손해를 보지 않는 한 최대한 많은 집에 홈방범 서비스를 제공
	// 하나의 집이 지불할 수 있는 비용 M

	// 출력: 서비스를 제공 받는 집들의 수

	// 도시의 크기 N은 5 이상 20 이하의 정수이다. (5 ≤ N ≤ 20)

	// 홈방범 서비스의 운영 비용은 서비스 영역의 면적과 동일

	// 방법 2개
	// 집을 좌표로 저장하고 순회하여 좌표마다 집들과의 멘헤튼거리를 계산 D[거리] = 집개수 로 만들어 계산
	// 집이 적을 경우 이 방법이 빠름

	// 각 좌표에서 범위를 1씩 늘리며 증분만 재계산,손해를 보지 않는 최대 경우를 기록해 갱신
	// 슬라이드윈도우처럼 이미계산한 이전집개수이용할 수도 있으나 구현이 복잡

	static int N, M;
	static int[][] map;
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// input

			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 이 좌표에서 1씩 늘리며 ..
					// 누적 집 수
					int count = map[i][j] == 1 ? 1 : 0;
					// 최적 집 수
					int service = count; // 1칸일때는 항상 서비스하는게이득
					int cost = 1;
					for (int k = 1; k < 2 * N; k++) {
						// 해당하는 좌표 테두리에 집 몇 개?
						count += cntHouse(i, j, k); // 새로 추가되는 집 수
						cost += 4 * k;
						if (count * M >= cost) {
							service = count;
						}
					}
					if (service > max) {
						max = service;
					}
				}
			}
			System.out.println("#" + test_case + " " + max);
		}
	}

	static int cntHouse(int cenR, int cenC, int range) {
		int count = 0;
		for (int dr = -range; dr <= range; dr++) {
			int r = cenR + dr;
			// |dr| + |dc| = range
			int dc = range - Math.abs(dr);
			int c = cenC + dc;

			if ((r >= 0 && r < N && c >= 0 && c < N) && map[r][c] == 1) {
				count++;
			}

			if (dc == 0)
				continue;

			dc = Math.abs(dr) - range;
			c = cenC + dc;

			if ((r >= 0 && r < N && c >= 0 && c < N) && map[r][c] == 1) {
				count++;
			}
		}
		return count;
	}
}
