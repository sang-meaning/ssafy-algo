import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	static int receptionCount, repairCount, customer, A, B;
	static int[] receptionTimeArr;
	static int[] repairTimeCountArr;
	static int[] customerArriveTimeArr;
	static int sumCustomer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			receptionCount = Integer.parseInt(st.nextToken());
			repairCount = Integer.parseInt(st.nextToken());
			customer = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			sumCustomer = 0;

			receptionTimeArr = new int[receptionCount + 1];
			repairTimeCountArr = new int[repairCount + 1];
			customerArriveTimeArr = new int[customer + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= receptionCount; i++) {
				receptionTimeArr[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= repairCount; i++) {
				repairTimeCountArr[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= customer; i++) {
				customerArriveTimeArr[i] = Integer.parseInt(st.nextToken());
			}

			simulate();

			if (sumCustomer == 0) {
				sumCustomer = -1;
			}

			System.out.println("#" + test_case + " " + sumCustomer);
		}
	}

	static void simulate() {
		Queue<Integer> receptionWait = new ArrayDeque<>();
		Queue<Integer> repairWait = new ArrayDeque<>();

		int[] receptionCustomer = new int[receptionCount + 1];
		int[] repairCustomer = new int[repairCount + 1];

		int[] receptionEnd = new int[receptionCount + 1];
		int[] repairEnd = new int[repairCount + 1];

		int[] usedReception = new int[customer + 1];

		int time = 0;
		int nextCustomer = 1;
		int finished = 0;

		while (finished < customer) {
			while (nextCustomer <= customer && customerArriveTimeArr[nextCustomer] <= time) {
				receptionWait.offer(nextCustomer);
				nextCustomer++;
			}

			for (int i = 1; i <= receptionCount; i++) {
				if (receptionCustomer[i] != 0 && receptionEnd[i] == time) {
					repairWait.offer(receptionCustomer[i]);
					receptionCustomer[i] = 0;
				}
			}

			for (int i = 1; i <= repairCount; i++) {
				if (repairCustomer[i] != 0 && repairEnd[i] == time) {
					repairCustomer[i] = 0;
					finished++;
				}
			}

			for (int i = 1; i <= receptionCount; i++) {
				if (receptionCustomer[i] == 0 && !receptionWait.isEmpty()) {
					int customerId = receptionWait.poll();

					receptionCustomer[i] = customerId;
					receptionEnd[i] = time + receptionTimeArr[i];

					usedReception[customerId] = i;
				}
			}

			for (int i = 1; i <= repairCount; i++) {
				if (repairCustomer[i] == 0 && !repairWait.isEmpty()) {
					int customerId = repairWait.poll();

					repairCustomer[i] = customerId;
					repairEnd[i] = time + repairTimeCountArr[i];

					if (usedReception[customerId] == A && i == B) {
						sumCustomer += customerId;
					}
				}
			}

			time++;
		}
	}
}