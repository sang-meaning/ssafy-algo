package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5644_이윤찬 {

	// 맵은 하나인데 담아야할 내용은 여러개이니깐 차원을 늘려야함 굳이?
	static final int N = 10; // 맵의 크기는 고정
	// 이동 시간
	static int M;
	// 배터리 개수
	static int A;

	// 배터리 클래스
	static class Battery {
		// 배터리 위치 , 충전 범위 , 성능
		int x, y, c, p;

		public Battery(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}

	// 유저2명 클래스
	static class User {
		int x, y;

		public User(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
// Battery 리스트 생성

	static Battery[] bList;

	// 테스트케이스
	static int T;

	// 맨해튼 거리 공식을 통해서 우리는 배터리 사용가능한 인덱스를 보내줄거임.
	public static List<Integer> isPossible(User man) {
		int userX = man.x;
		int userY = man.y;

		List<Integer> bL = new ArrayList<>();
		int check = 0;
		for (int i = 0; i < bList.length; i++) {

			int constain = bList[i].c;

			check = Math.abs(userX - bList[i].x) + Math.abs(userY - bList[i].y);

			if (check <= constain)
				bL.add(i);
		}

		return bL;

	}

	// 사용자 커맨드 1
	static List<Integer> Cmd1;
	// 사용자 커맨드 2
	static List<Integer> Cmd2;

	// 이동방법 5가지
	static int[] dx = { 0, 0, 1, 0, -1 };
	static int[] dy = { 0, -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 테스트 케이스 입력
		T = Integer.parseInt(br.readLine());

		StringTokenizer st;

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());

			A = Integer.parseInt(st.nextToken());
			// cmd1 초기화
			Cmd1 = new ArrayList<>();
			Cmd2 = new ArrayList<>();
			// 사용자 초기 정보 제공
			User user1 = new User(0, 0);

			User user2 = new User(9, 9);

			// 이동정보 제공
			st = new StringTokenizer(br.readLine());

			for (int mov = 0; mov < M; mov++) {
				Cmd1.add(Integer.parseInt(st.nextToken()));

			}
			// 이동정보 제공
			st = new StringTokenizer(br.readLine());

			for (int mov = 0; mov < M; mov++) {

				Cmd2.add(Integer.parseInt(st.nextToken()));

			}
			// 배터리배열 초기화
			bList = new Battery[A];
			// 배터리 정보 제공
			for (int ba = 0; ba < A; ba++) {

				st = new StringTokenizer(br.readLine());

				Battery b = new Battery(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				// 집어넣기
				bList[ba] = b;
			}

			int Total = 0;

			for (int move = 0; move <= M; move++) {

				// 첫번째 유저의 좌표를 기준으로 유효거리에 있는 배터리가 있는가? -> 배터리 인덱스를 부여 받는다
				List<Integer> user1_BC = isPossible(user1);

				List<Integer> user2_BC = isPossible(user2);

				if (user1_BC.isEmpty()) { // 비어져있을때
					Total += 0;
					if (!user2_BC.isEmpty()) {
						int Max = 0;
						for (int index : user2_BC) {
							Max = Math.max(bList[index].p, Max);
						}
						Total += Max;
					} else {
						Total += 0;
					}
				} else { // 무언가 들어가있을때
					if (user1_BC.size() == 1) { // 1개일때
						int idx1 = user1_BC.get(0);

						if (user2_BC.size() == 1) { // 1개일때
							if (user2_BC.get(0) == idx1) { // 동일할 때
								Total += bList[idx1].p;
							} else {// 동일하지 않음
								Total += bList[idx1].p + bList[user2_BC.get(0)].p;
							}
						} else if (user2_BC.isEmpty()) {// 비엇을때
							Total += bList[idx1].p;
						} else { // 다수일 때
							int max = 0;
							int buffer1 = 0;
							for (int id : user2_BC) {

								int buffer2 = bList[idx1].p;
								if (idx1 == id) {
									buffer1 = bList[id].p;
								} else {
									buffer2 += bList[id].p;
								}
								max = Math.max(max, buffer2);
							}
							max = Math.max(buffer1, max);
							Total += max;
						}
					} else {// use1 도 여러개일때
						int maxA = 0;
						int maxId = -1;
						for (int id : user1_BC) {
							int buffer = bList[id].p;
							if (bList[id].p > maxA) {
								maxA = bList[id].p;
								maxId = id;
							}
						}
						if (user2_BC.isEmpty()) {
							Total += maxA;
						} else {
							int maxB = 0;
							int maxBId = -1;
							for (int id : user2_BC) {
								if (bList[id].p > maxB) {
									maxB = bList[id].p;
									maxBId = id;
								}
							}
							if (maxBId == maxId) {
								int maxA2 = 0;
								for (int id : user1_BC) {
									int buffer = bList[id].p;
									if (buffer != maxA) {
										maxA2 = Math.max(buffer, maxA2);
									}
								}
								int sum1 = maxA + maxA2;
								int maxB2 = 0;
								for (int id : user2_BC) {
									int buffer = bList[id].p;
									if (buffer != maxA) {
										maxB2 = Math.max(buffer, maxB2);
									}
								}
								int sum2 = maxA + maxB2;
								if (maxA < sum1 || maxA < sum2) {
									if (maxA2 >= maxB2) {
										Total += sum1;
									} else {
										Total += sum2;
									}
								} else {
									Total += maxA;
								}
							} else {
								Total = Total + maxB + maxA;
							}
						}
					}
				}
				if (M == move) {
					break;
				}
				int command1_IDX = Cmd1.get(move);
				user1 = new User(user1.x + dx[command1_IDX], user1.y + dy[command1_IDX]);
				int command2_IDX = Cmd2.get(move);
				user2 = new User(user2.x + dx[command2_IDX], user2.y + dy[command2_IDX]);
			}
			System.out.println("#" + t + " " + Total);
		}
	}
}