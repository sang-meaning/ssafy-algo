package coding_prac;

/////////////////////////////////////////////////////////////////////////////////////////////
//기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
//아래 표준 입출력 예제 필요시 참고하세요.
//표준 입력 예제
//int a;
//double b;
//char g;
//String var;
//long AB;
//a = sc.nextInt();                           // int 변수 1개 입력받는 예제
//b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
//g = sc.nextByte();                          // char 변수 1개 입력받는 예제
//var = sc.next();                            // 문자열 1개 입력받는 예제
//AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
//표준 출력 예제
//int a = 0;                            
//double b = 1.0;               
//char g = 'b';
//String var = "ABCDEFG";
//long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution_4130_이승규 {
	static int[][] gear;
	static BufferedReader bf;

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
		bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(ignoreEmptyLine());
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {
			String s = ignoreEmptyLine();
			int k = Integer.parseInt(s);

			gear = new int[4][8];
			for (int i = 0; i < 4; i++) { // 기어는 4개
				s= ignoreEmptyLine();
				StringTokenizer st = new StringTokenizer(s);
				for (int j = 0; j < 8; j++) { // 이빨은 8개
					gear[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < k; i++) {
				s = ignoreEmptyLine();
				StringTokenizer st = new StringTokenizer(s);
				int target = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken()); // 1이면 시계, -1이면 반시계

				int[] turnList = turnList(target, dir); // 돌릴애들 찾기

				for (int j = 0; j < 4; j++) { // 돌리기
					if (turnList[j] == 0)
						continue;
					else if (turnList[j] == 1)
						clockwise(j);
					else
						counterClockwise(j);
				}
			}
			int result = countScore();

			System.out.println("#" + test_case + " " + result);

		}
	}

	static String ignoreEmptyLine() throws IOException {
		String s;
		
		do {
	        s = bf.readLine();
	    } while (s != null && s.trim().isEmpty());

	    return s;
	}
	
	static int[] turnList(int target, int dir) { // 이번에 돌릴 애들과 방향. 1이면 시계, -1이면 반시계, 0이면 안돌려요
		int[] result = new int[4];
		int originalTarget = target;
		int originalDir = dir;

		result[target] = dir;
		while (target - 1 >= 0) { // 왼쪽
			if (gear[target][6] != gear[target - 1][2]) {
				dir *= -1;
				result[target - 1] = dir;
				target--;
			} else
				break;
		}

		target = originalTarget;
		dir = originalDir;

		while (target + 1 < 4) { // 오른쪽
			if (gear[target][2] != gear[target + 1][6]) {
				dir *= -1;
				result[target + 1] = dir;
				target++;
			} else
				break;
		}
		return result;

	}

	static void clockwise(int gearNum) { // 1일경우, 시계
		int[] temp = new int[8];
		temp[0] = gear[gearNum][7];
		for (int i = 0; i < 7; i++) {
			temp[i + 1] = gear[gearNum][i];
		}
		gear[gearNum] = temp;
	}

	static void counterClockwise(int gearNum) { // -1일경우, 반시계
		int[] temp = new int[8];
		temp[7] = gear[gearNum][0];
		for (int i = 0; i < 7; i++) {
			temp[i] = gear[gearNum][i + 1];
		}
		gear[gearNum] = temp;
	}

	static int countScore() {
		int result = 0;
		for(int i = 0; i < 4; i++) {
			result += gear[i][0] * (1 << i); // 1 2 4 8
		}
		return result;
	}
}