/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
package coding_prac;

import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution_5650_이승규 {
	static int n;
	static int[][] map;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int curDir;
	
	static int[][][] wormhole;
	static int[] wormCnt;

	public static void main(String args[]) throws Exception {
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이 작성한 코드를
		 * 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때
		 * 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		 * 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		// System.setIn(new FileInputStream("res/input.txt"));

		/*
		 * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {
			n = sc.nextInt();
			map = new int[n][n];
			wormhole = new int[11][2][2];
			wormCnt = new int[11];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					
					if(map[i][j] >= 6 && map[i][j] <= 10) {

					    int num = map[i][j];
					    int idx = wormCnt[num];

					    wormhole[num][idx][0] = i;
					    wormhole[num][idx][1] = j;

					    wormCnt[num]++;
					}
				}
			}

			int nextX = 0;
			int nextY = 0;
			int maxPoint = Integer.MIN_VALUE;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) { // 모든칸에서 (n^2)
					if (map[i][j] != 0)
						continue;
					int startX = i;
					int startY = j;

					for (int startDir = 0; startDir < 4; startDir++) { // 4번 -> 최대 4만루프
						int nowX = i;
						int nowY = j;

						curDir = startDir;

						boolean isDone = false;
						int point = 0;

						// 무한루프. 탈출조건 만들기
						while (!isDone) {
							nextX = nowX + dir[curDir][0];
							nextY = nowY + dir[curDir][1];
							
							if(nextX == startX && nextY == startY) {
								break;
							}

							// next 좌표가 무엇에 부딛혔는가?
							int nextCondition = isHit(nextX, nextY);

							switch (nextCondition) {
							case 0: { // 그냥 길. 움직이기
								nowX = nextX;
								nowY = nextY;
								break;
							}
							case 1: { // 벽. dir+2. point++. 움직이지 않기
								reverse();
								point++;
								
							    nowX = nextX;
							    nowY = nextY;
							    
								break;
							}
							case 2: { // 블럭. 움직이기, dir 어떻게할지 체크해보기. point++.
								int checkDir = decideDir(nextX, nextY); // 0 -> tr 1 -> tl 2 -> wall
								if (checkDir == 0)
									turnRight();
								else if (checkDir == 1)
									turnLeft();
								else
									reverse();
								point++;

								nowX = nextX;
								nowY = nextY;

								break;
							}
							case 3: { // 텔레포트. 움직이기, dir 유지. nowX nowY 옮기기
								int[] teleLoc = teleport(nextX, nextY);
								nowX = teleLoc[0];
								nowY = teleLoc[1];
								break;
							}
							case 4: { // 끝, 루프종료
								isDone = true;
								break;
							}
							default:
								break;
							}
						}

						// 최대값 check
						maxPoint = Math.max(maxPoint, point);
					}
				}
			}
			System.out.println("#" + test_case + " " + maxPoint);
		}
	}

	// 0인지 아닌지 체크하는 함수.
	static int isHit(int nextX, int nextY) {
		if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) { // 벽일경우 1
			return 1;
		}
		if (map[nextX][nextY] == 0) // 빈칸
			return 0;
		if (map[nextX][nextY] > 0 && map[nextX][nextY] < 6) // 1~5일 경우 2
			return 2;
		else if (map[nextX][nextY] >= 6 && map[nextX][nextY] <= 10) // 6~10 3
			return 3;
		else // -1일경우 4
			return 4;
	}

	// 방향 정하는 함수
	static int decideDir(int nextX, int nextY) { // 0이면 tr 1이면 tl 2면 hitwall

		int block = map[nextX][nextY];

		if (block == 5)
			return 2;

		if (block % 4 == curDir) {
			return 1;
		}

		int rightCheck = (block + 1) % 4;

		if (rightCheck == curDir) {
			return 0;
		}

		return 2;
	}

	// 우로돌아
	static void turnRight() {
		curDir += 1;
		if (curDir == 4)
			curDir = 0;
	}

	// 좌로돌아
	static void turnLeft() {
		curDir -= 1;
		if (curDir == -1)
			curDir = 3;
	}

	// 이동
	static int[] teleport(int nextX, int nextY) {
		int resultX = 0;
		int resultY = 0;
		int target = map[nextX][nextY];
		
		if(wormhole[target][0][0] == nextX && wormhole[target][0][1] == nextY) {
			resultX = wormhole[target][1][0];
			resultY = wormhole[target][1][1];
		} else {
			resultX = wormhole[target][0][0];
			resultY = wormhole[target][0][1];
		}
		
		int[] result = { resultX, resultY };
		return result;

	}

	// 뒤로돌아
	static void reverse() {
		curDir += 2;
		if (curDir >= 4) {
			curDir -= 4;
		}
	}
}