package coding_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5644_이승규 {

	static int[][][] map;
	static int a;
	static int resultA, resultB;

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
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {

			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);

			int m = Integer.parseInt(st.nextToken()); // step m
			a = Integer.parseInt(st.nextToken()); // 충전기 수 a
			map = new int[11][11][a]; // 발전기 지도 a개

			int[] userARoute = new int[m];
			int[] userBRoute = new int[m]; // a,b 경로
			int[] posA = { 1, 1 };
			int[] posB = { 10, 10 };

			s = bf.readLine();
			st = new StringTokenizer(s);

			for (int i = 0; i < m; i++) {
				userARoute[i] = Integer.parseInt(st.nextToken());
			}

			s = bf.readLine();
			st = new StringTokenizer(s);

			for (int i = 0; i < m; i++) {
				userBRoute[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < a; i++) {
				s = bf.readLine();
				st = new StringTokenizer(s);
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int range = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());

				// map[x][y][i]를 중심으로 정보를 넣기 + 파워값으로 저장하면 괜찮을지도
				makeMap(x, y, i, range, power);
			}

			resultA = 0;
			resultB = 0;

			for (int i = 0; i <= m; i++) { // step 수만큼 반복할것
				int[] BCListA = chargerList(posA);
				int[] BCListB = chargerList(posB);

				if (!overlapped(BCListA, BCListB)) { // 안겹치면
					// 최댓값으로 charge하고 가자.
					Arrays.sort(BCListA);
					Arrays.sort(BCListB);

					resultA += BCListA[a - 1];
					resultB += BCListB[a - 1];
				} else {
					// 겹치면
					// A+B가 최대가 되는 값을 완탐으로 구하도록 하자
					int[] maxResult = findMaxResult(BCListA, BCListB);
					resultA += maxResult[0];
					resultB += maxResult[1];
				}

				if (i == m) // 끝났으면 나가
					break;

				// 앞으로 한칸씩 이동하자
				posA = move(userARoute[i], posA);
				posB = move(userBRoute[i], posB);
			}
			
			int answer = resultA + resultB;
			System.out.println("#" + test_case + " " + answer);
		}
	}

	static void makeMap(int x, int y, int apNum, int range, int power) { // 지도를 만들어주는 함수
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {

				// BC와 현재 칸 사이의 맨해튼 거리
				int distance = Math.abs(x - i) + Math.abs(y - j);

				// 충전 범위 안이면 해당 BC의 power 저장
				if (distance <= range) {
					map[i][j][apNum] = power;
				}
			}
		}
	}

	static int[] chargerList(int[] position) { // 현 위치의 charger의 power값을 배열로 가져옴. 없다면 0.
		int[] result = new int[a];

		for (int i = 0; i < a; i++) {
			if (map[position[0]][position[1]][i] != 0) {
				result[i] = map[position[0]][position[1]][i];
			}
		}

		return result;
	}

	static boolean overlapped(int[] BCListA, int[] BCListB) { // 겹치는게 있으면 true, 없으면 false
		for (int i = 0; i < a; i++) {
			if (BCListA[i] > 0 && BCListB[i] > 0) {
				return true;
			}
		}

		return false;
	}

	static int[] findMaxResult(int[] BCListA, int[] BCListB) { // {최대일때 늘어나는 A의 값, 최대일때 늘어나는 B의 값}을 return
		int maxResult = Integer.MIN_VALUE;
		int result = 0;
		int[] maxResultReturn = new int[2];

		for (int i = 0; i < a; i++) {
			for (int j = 0; j < a; j++) {
				int chargeA;
				int chargeB;
				
				if (i == j && BCListA[i] > 0 && BCListB[j] > 0) { // 공유할때의 값
					chargeA = BCListA[i] / 2;
					chargeB = BCListB[j] / 2;
				} else {
					chargeA = BCListA[i];
					chargeB = BCListB[j];
				}
				
				result = chargeA + chargeB;
				
				if (maxResult < result) {
					maxResult = result;
					maxResultReturn[0] = chargeA;
					maxResultReturn[1] = chargeB;
				}
			}
		}
		return maxResultReturn;
	}

	static int[] move(int instruction, int[] curPos) {
		int[] result = new int[2];
		switch (instruction) {
		case 1:
			result[0] = curPos[0];
			result[1] = curPos[1] - 1;
			break;
		case 2:
			result[0] = curPos[0] + 1;
			result[1] = curPos[1];
			break;
		case 3:
			result[0] = curPos[0];
			result[1] = curPos[1] + 1;
			break;
		case 4:
			result[0] = curPos[0] - 1;
			result[1] = curPos[1];
			break;
		default:
			result[0] = curPos[0];
			result[1] = curPos[1];
			break;
		}

		return result;
	}
}
