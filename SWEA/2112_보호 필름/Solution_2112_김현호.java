
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int[][] map;
    static int[][] copyMap;
    static boolean[] med;
    static int D;
    static int W;
    static int K;
    static boolean flag;

    // 모든 열에 같은 특성이 K개 이상 연속하는지 검사
    public static boolean test() {
        for (int col = 0; col < W; col++) {
            int film = -1;
            int count = 0;
            boolean passed = false;

            for (int row = 0; row < D; row++) {
                if (copyMap[row][col] == film) {
                    count++;
                } else {
                    film = copyMap[row][col];
                    count = 1;
                }

                if (count >= K) {
                    passed = true;
                    break;
                }
            }

            if (!passed) {
                return false;
            }
        }

        return true;
    }

    // 선택한 행마다 A(0), B(1) 약품을 각각 적용
    public static void applyMedicine(int row) {
        if (flag) {
            return;
        }

        if (row == D) {
            if (test()) {
                flag = true;
            }
            return;
        }

        if (!med[row]) {
            applyMedicine(row + 1);
            return;
        }

        Arrays.fill(copyMap[row], 0);
        applyMedicine(row + 1);

        if (flag) {
            return;
        }

        Arrays.fill(copyMap[row], 1);
        applyMedicine(row + 1);
    }

    // 약품을 투입할 행을 limit개 선택
    public static void combination(int depth, int start, int limit) {
        if (flag) {
            return;
        }

        if (depth == limit) {
            copyMap = new int[D][];

            for (int i = 0; i < D; i++) {
                copyMap[i] = map[i].clone();
            }

            applyMedicine(0);
            return;
        }

        // 남은 행으로 필요한 개수를 채울 수 있는 경우만 탐색
        for (int i = start; i <= D - (limit - depth); i++) {
            med[i] = true;
            combination(depth + 1, i + 1, limit);
            med[i] = false;

            if (flag) {
                return;
            }
        }
    }
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
			D = sc.nextInt();
            W = sc.nextInt();
            K = sc.nextInt();

            map = new int[D][W];
            med = new boolean[D];
            flag = false;

            for (int i = 0; i < D; i++) {
                for (int j = 0; j < W; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            // 투입 횟수가 적은 경우부터 검사
            for (int limit = 0; limit <= K; limit++) {
                combination(0, 0, limit);

                if (flag) {
                    System.out.println("#" + test_case + " " + limit);
                    break;
                }
            }
		}
	}
}