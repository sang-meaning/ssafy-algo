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
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution {
	static int n;
	static int h;
	static int w;
	static int currBlock;
	static int[][] map;
	static int maxBroken;
	static int[][] breakDir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static ArrayList<Integer> blockInfo;
	static Queue<ArrayList<Integer>> blockQueue;

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
			w = sc.nextInt();
			h = sc.nextInt();
			map = new int[h][w];
			currBlock = 0;

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] != 0) {
						currBlock++;
					}
				}
			}

			maxBroken = 0;
			dfs(0, 0);

			System.out.println("#" + test_case + " " + (currBlock - maxBroken));
		}
	}

	static void dfs(int count, int howManyBroken) {
		if (count == n) {
			maxBroken = Math.max(maxBroken, howManyBroken);
			return;
		}

		int broken = 0;

		for (int i = 0; i < w; i++) {
			int[][] tempMap = new int[h][w];

			for (int j = 0; j < h; j++) {
				tempMap[j] = map[j].clone();
			}

			broken = breakBlock(i);

			blockDrop();

			dfs(count + 1, howManyBroken + broken);

			map = tempMap;
		}
	}

	static int breakBlock(int col) {
		blockQueue = new LinkedList<>();
		int result = 0;
		// [0][col]부터 [h-1][col] 까지 보면서 가장 먼저 0이 아닌걸 체크한다. 큐에 [y][x] 좌표와 range값을 넣은
		// ArrayList를 넣는다.
		for (int i = 0; i < h; i++) {
			if (map[i][col] != 0) {
				blockInfo = makeBlockInfo(i, col, map[i][col]);
				blockQueue.add(blockInfo);
				result++;
				map[i][col] = 0;
				break;
			}
		}

		// 큐가 비지 않았다면, poll. [y][x] 좌표를 기준, range범위를 0으로 바꾸고, 만나는 0이 아닌 숫자들과 좌표를 큐에 넣기.
		// 큐가 빌때까지 반복

		while (!blockQueue.isEmpty()) {
			ArrayList<Integer> targetBlock = new ArrayList<>();
			targetBlock = blockQueue.poll();
			int targetY = targetBlock.get(0);
			int targetX = targetBlock.get(1);
			int targetRange = targetBlock.get(2);

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < targetRange; j++) {
					int nextY = targetY + breakDir[i][0] * j;
					int nextX = targetX + breakDir[i][1] * j;

					if (nextY < 0 || nextX < 0 || nextY >= h || nextX >= w) {
						continue;
					}

					if (map[nextY][nextX] != 0) {
						blockInfo = makeBlockInfo(nextY, nextX, map[nextY][nextX]);
						blockQueue.add(blockInfo);
						result++;
						map[nextY][nextX] = 0;
					}

				}
			}
		}

		// 큐가 비었다면 result (부순 블럭 수)를 리턴한다.
		return result;
	}

	static void blockDrop() {
		// map[0][i] 부터 map[h-1][i] 까지 arrayList에 넣는다.
		// arrayList.removeIf(val -> val==0)
		// map[][i]를 0으로 초기화하고, 아래에 arrayList 값을 넣는다?
		for (int i = 0; i < w; i++) {
			ArrayList<Integer> tempCol = new ArrayList<>();
			for (int j = 0; j < h; j++) {
				tempCol.add(map[j][i]);
				map[j][i] = 0;
			}
			tempCol.removeIf(val -> val == 0);

			for (int j = 0; j < tempCol.size(); j++) {
				map[h - tempCol.size() + j][i] = tempCol.get(j);
			}
		}
	}

	static ArrayList<Integer> makeBlockInfo(int y, int x, int range) {
		ArrayList<Integer> result = new ArrayList<>();
		result.add(y);
		result.add(x);
		result.add(range);
		return result;
	}
}