package coding_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution_2477_이승규 {
	static int n;
	static int m;
	static int k;
	static int a;
	static int b;
	static int[] receptionTime;
	static int[] repairTime;
	static Customer[] customerList;

	static class Customer {
		int tk;
		int receptionNum;
		int repairNum;
		boolean isDone;

		Customer(int tk, int receptionNum, int repairNum) {
			this.tk = tk;
			this.receptionNum = receptionNum;
			this.repairNum = repairNum;
			this.isDone = false;
		}
	}

	public static void main(String args[]) throws Exception {
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이 작성한 코드를
		 * 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때
		 * 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		 * 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
//System.setIn(new FileInputStream("res/input.txt"));

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

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			receptionTime = new int[n];
			repairTime = new int[m];

			s = bf.readLine();
			st = new StringTokenizer(s);
			for (int i = 0; i < n; i++) {
				receptionTime[i] = Integer.parseInt(st.nextToken());
			}

			s = bf.readLine();
			st = new StringTokenizer(s);
			for (int i = 0; i < m; i++) {
				repairTime[i] = Integer.parseInt(st.nextToken());
			}

			s = bf.readLine();
			st = new StringTokenizer(s);

			customerList = new Customer[k];

			for (int i = 0; i < k; i++) {
				customerList[i] = new Customer(Integer.parseInt(st.nextToken()), -1, -1);
			}

			int doneCustomer = 0;
			int time = 0;

			Queue<Integer> receptionWaitingQueue = new LinkedList<>();
			Queue<Integer> repairWaitingQueue = new LinkedList<>();

			int[] receptionDesk = new int[n];
			int[] receptionEndTime = new int[n];

			int[] repairDesk = new int[m];
			int[] repairEndTime = new int[m];

			Arrays.fill(receptionDesk, -1);
			Arrays.fill(repairDesk, -1);

			while (doneCustomer < k) {
				// repair 끝난 고객이 있는가? -> 내보내기
				for (int i = 0; i < m; i++) {
					if (repairDesk[i] != -1 && repairEndTime[i] == time) {
						int doneCustomerNum = repairDesk[i];

						customerList[doneCustomerNum].isDone = true;
						customerList[doneCustomerNum].repairNum = i + 1;
						doneCustomer++;
						repairDesk[i] = -1;
					}
				}

				// reception 끝난 고객이 있는가? -> repairWaitingQueue에 추가
				for (int i = 0; i < n; i++) {
					if (receptionDesk[i] != -1 && receptionEndTime[i] == time) {
						int doneCustomerNum = receptionDesk[i];

						repairWaitingQueue.add(doneCustomerNum);
						receptionDesk[i] = -1;
						customerList[doneCustomerNum].receptionNum = i + 1;
					}
				}

				// time에 도착한 고객이 있는가? -> receptionWaitingQueue에 추가
				for (int i = 0; i < k; i++) {
					if (customerList[i].tk == time) {
						receptionWaitingQueue.add(i);
					}
				}

				// reception이 비어있는가? -> poll
				for (int i = 0; i < n; i++) {
					if (receptionDesk[i] == -1) {
						if (!receptionWaitingQueue.isEmpty()) { // 비어있지 않다면
							receptionDesk[i] = receptionWaitingQueue.poll();
							receptionEndTime[i] = time + receptionTime[i];
						} else {
							break;
						}
					}
				}

				// repair가 비어있는가? -> poll
				for (int i = 0; i < m; i++) {
					if (repairDesk[i] == -1) {
						if (!repairWaitingQueue.isEmpty()) { // 비어있지 않다면
							repairDesk[i] = repairWaitingQueue.poll();
							repairEndTime[i] = time + repairTime[i];
						} else {
							break;
						}
					}
				}

				// time++;
				time++;
			}
			
			int answer = 0;
			for(int i = 0; i < k; i++) {
				if(customerList[i].receptionNum == a && customerList[i].repairNum == b) {
					answer += (i+1);
				}
			}
			
			if(answer == 0) {
				answer = -1;
			}
			System.out.println("#" + test_case + " " + answer);
		}
	}
}