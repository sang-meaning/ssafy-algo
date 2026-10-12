package ssafy.swea.kjw;

import java.io.*;
import java.util.*;

class Player {
	int x;
	int y;
	int sumCharge;

	public Player(int x, int y, int sumCharge) {
		this.x = x;
		this.y = y;
		this.sumCharge = sumCharge;
	}
}

class BC {
	int index;
	int x;
	int y;
	int C;
	int P;

	public BC(int x, int y, int c, int p) {
		this.x = x;
		this.y = y;
		C = c;
		P = p;
	}
}

public class Solution_5644_김정원 {
	static int M;
	static Player A;
	static Player B;
	static int[] pathA, pathB;
	static BC[] ap;
	static BC[][][] map;

	static int[] DX;
	static int[] DY;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			/* 입력 받기 시작 */

			StringTokenizer st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			int apCount = Integer.parseInt(st.nextToken());

			A = new Player(1, 1, 0);
			B = new Player(10, 10, 0);

			pathA = new int[M];
			pathB = new int[M];
			ap = new BC[apCount];
			map = new BC[11][11][8];

			// 0 : 이동 없음
			// 1 : 상, 2 : 우, 3 : 하, 4 : 좌
			DX = new int[] { 0, 0, 1, 0, -1 };
			DY = new int[] { 0, -1, 0, 1, 0 };

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < M; i++) {
				pathA[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < M; i++) {
				pathB[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < apCount; i++) {
				st = new StringTokenizer(br.readLine());

				ap[i] = new BC(
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())
				);

				ap[i].index = i + 1;
			}

			/* 입력 받기 종료 */

			play();

			// 두 플레이어가 충전한 양의 합을 출력
			System.out.println(
					String.format(
							"#%d %d",
							test_case,
							A.sumCharge + B.sumCharge
					)
			);
		}
	}

	// 맵에 충전소의 범위를 표시
	static void init() {
		for (BC bc : ap) {

			// 충전 범위는 맨해튼 거리 형태로 지정
			for (int c = -bc.C; c <= bc.C; c++) {
				int y = bc.y + c;
				for (int x = (bc.x - bc.C) + Math.abs(c);
						x <= (bc.x + bc.C) - Math.abs(c);
						x++) {

					if (isMapIn(x, y)) {
						map[y][x][getSize(x, y)] = bc;
					}
				}
			}
		}
	}

	// 처음 위치에서 충전하고 이동할 때마다 다시 충전
	static void play() {
		init();

		// 이동 전인 0초에도 충전 가능
		charge();

		for (int t = 0; t < M; t++) {
			move(A, pathA[t]);
			move(B, pathB[t]);

			charge();
		}
	}

	// 플레이어를 입력받은 방향으로 이동
	static void move(Player player, int direction) {
		int nx = player.x + DX[direction];
		int ny = player.y + DY[direction];

		if (!isMapIn(nx, ny)) {
			return;
		}

		player.x = nx;
		player.y = ny;
	}

	// 두 플레이어가 선택할 수 있는 모든 충전소 조합 확인
	static void charge() {
		BC[] aBCList = map[A.y][A.x];
		BC[] bBCList = map[B.y][B.x];

		int maxCharge = 0;
		int selectedACharge = 0;
		int selectedBCharge = 0;

		for (BC selectA : aBCList) {
			for (BC selectB : bBCList) {
				int currentA = 0;
				int currentB = 0;

				if (selectA != null) {
					currentA = selectA.P;
				}

				if (selectB != null) {
					currentB = selectB.P;
				}

				// 두 플레이어가 같은 충전소를 선택했다면
				if (selectA != null && selectA == selectB) {
					// 두명이 충전소의 성능을 나눠서 가져감
					currentA = selectA.P / 2;
					currentB = selectA.P - currentA;
				}

				// 두명의 충전량 합이 가장 큰 경우를 저장
				if (maxCharge < currentA + currentB) {
					maxCharge = currentA + currentB;
					selectedACharge = currentA;
					selectedBCharge = currentB;
				}
			}
		}

		A.sumCharge += selectedACharge;
		B.sumCharge += selectedBCharge;
	}

	static boolean isMapIn(int x, int y) {
		return 0 < x && x <= 10 && 0 < y && y <= 10;
	}

	// 해당 위치에 저장된 충전소의 개수 반환
	static int getSize(int x, int y) {
		int count = 0;

		for (int i = 0; i < map[y][x].length; i++) {
			if (map[y][x][i] == null) {
				break;
			}

			count++;
		}

		return count;
	}
}