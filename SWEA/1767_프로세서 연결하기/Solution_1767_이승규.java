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

package alg_prac_solved;

import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution_1767_이승규
{
    static int n;
	static int[][] processor;
	static List<int[]> nodeToCheck;
	static int maxCore;
	static int answer;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static boolean connectable;
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
            n = sc.nextInt();
			processor = new int[n][n];
			maxCore = 0;
			answer = 0;

			nodeToCheck = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					processor[i][j] = sc.nextInt();
					if (processor[i][j] != 0) {
						if (i != 0 && j != 0 && i != n - 1 && j != n - 1)
							nodeToCheck.add(new int[] { i, j });
					}
				}
			} // 넣기

//			for(int i = 0; i < nodeToCheck.size(); i++) {
//				System.out.print(nodeToCheck.get(i)[0]);
//				System.out.println(nodeToCheck.get(i)[1]);
//			}

			dfs(0, 0, 0);
			System.out.println("#" + test_case + " " + answer);
		}        
	}
    
 	public static void dfs(int index, int core, int length) {

		if (index == nodeToCheck.size()) { // end dfs
			if (core > maxCore) {
				maxCore = core;
				answer = length;
			} else if (core == maxCore) {
				answer = Math.min(answer, length);
			}
			return;
		}

		// not end dfs

		int nowx = nodeToCheck.get(index)[0];
		int nowy = nodeToCheck.get(index)[1];

		// 현재 코어에서 4방향 check.
		for (int i = 0; i < 4; i++) {
			nowx = nodeToCheck.get(index)[0];
			nowy = nodeToCheck.get(index)[1];
			connectable = true;
			int wireLength = 0;

			// 미리 쭉 보고 막혀있으면 안가고 안막혀있으면 connect해보기
			while (true) {
				int checkx = nowx + dir[i][0];
				int checky = nowy + dir[i][1];
				if (checkx < 0 || checky < 0 || checkx >= n || checky >= n) { // 끝까지 체크했어요
					break;
				}
				if (processor[checkx][checky] == 1 || processor[checkx][checky] == -1) { // 다른코어를만나면
					connectable = false;
					break;
				}
				nowx = checkx;
				nowy = checky;
			}

			// wire 설치, -1로 넣기
			if (connectable) {
				nowx = nodeToCheck.get(index)[0];
				nowy = nodeToCheck.get(index)[1];
				while (true) {
					int checkx = nowx + dir[i][0];
					int checky = nowy + dir[i][1];
					if (checkx < 0 || checky < 0 || checkx >= n || checky >= n) { // 끝까지 체크했어요
						break;
					}
					processor[checkx][checky] = -1;
					nowx = checkx;
					nowy = checky;
					wireLength++;
				}

				// dfs(index+1 core+1 length+설치한length << 이것도 세야한다는 뜻
				dfs(index + 1, core + 1, length + wireLength);

				nowx = nodeToCheck.get(index)[0];
				nowy = nodeToCheck.get(index)[1];
				// 설치한 wire 싹 제거 (다음 dfs루프를 위해)
				while (true) {
					int checkx = nowx + dir[i][0];
					int checky = nowy + dir[i][1];
					if (checkx < 0 || checky < 0 || checkx >= n || checky >= n) { // 끝까지 체크했어요
						break;
					}
					processor[checkx][checky] = 0;
					nowx = checkx;
					nowy = checky;
				}
			}
		}
        dfs(index+1,core,length);
	}
    
}