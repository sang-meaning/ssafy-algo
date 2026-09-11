import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
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
			int M = sc.nextInt();
			int A = sc.nextInt();
			int[] dx = {0, 0, 1, 0, -1};
			int[] dy = {0, -1, 0, 1, 0};
			int[] A_route = new int[M];
			int[] B_route = new int[M];
			int[][] AP_info = new int[A][4];
			int charge = 0;
			for(int i = 0; i < M; i++) {
				A_route[i] = sc.nextInt();
			}
			for(int i = 0; i < M; i++) {
				B_route[i] = sc.nextInt();
			}
			for(int i = 0; i < A; i++) {
				for(int j = 0; j < 4; j++) {
					if(j <= 1) {
						AP_info[i][j] = sc.nextInt() - 1;
					}else {
						AP_info[i][j] = sc.nextInt();
					}
				}
			}
			int A_x = 0;
			int A_y = 0;
			int B_x = 9;
			int B_y = 9;
			for(int i = 0; i <= M; i++) {
				int[] A_incharge = new int[A];
				int[] B_incharge = new int[A];
				int maxCharge = 0;
				int max_A = 0;
				int max_B = 0;
				for(int j = 0; j < A; j++) {
					int A_distance = Math.abs(A_x - AP_info[j][0]) + Math.abs(A_y - AP_info[j][1]);
					int B_distance = Math.abs(B_x - AP_info[j][0]) + Math.abs(B_y - AP_info[j][1]);
					if(A_distance <= AP_info[j][2]) {
						A_incharge[j] = AP_info[j][3];
					}
					if(B_distance <= AP_info[j][2]) {
						B_incharge[j] = AP_info[j][3];
					}
				}
				for(int j = 0; j < A; j++) {
				    for(int k = 0; k < A; k++) {

				        int sum;

				        if(j == k) {
				            // 같은 BC를 둘 다 사용하는 경우
				            sum = Math.max(A_incharge[j], B_incharge[k]);
				        } else {
				            // 서로 다른 BC
				            sum = A_incharge[j] + B_incharge[k];
				        }

				        maxCharge = Math.max(maxCharge, sum);
				    }
				}
				charge += maxCharge;
				if(i == M) {
					break;
				}
				A_x = A_x + dx[A_route[i]];
				A_y = A_y + dy[A_route[i]];
				B_x = B_x + dx[B_route[i]];
				B_y = B_y + dy[B_route[i]];
			}
			System.out.println("#" + test_case + " " + charge);
		}
	}
}