import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	static int N, min_time, people_count, stair0_length, stair1_length;
	static int[][] stairs;
	static List<int[]> people;
	static int[][] distance;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			stairs = new int[2][2];
			people = new ArrayList<>();
			int stairIndex = 0;
			min_time = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 1) {
						people.add(new int[] { i, j });
					} else if (num > 1) {
						stairs[stairIndex][0] = i;
						stairs[stairIndex][1] = j;
						if (stairIndex == 0) {
							stair0_length = num;
						} else {
							stair1_length = num;
						}
						stairIndex++;
					}
				}
			}
			people_count = people.size();
			distance = new int[people_count][2];
			setDistance();
			dfs(0, new ArrayList<>(), new ArrayList<>());
			System.out.println("#" + test_case + " " + min_time);
		}
	}

	static void setDistance() {
		for (int i = 0; i < distance.length; i++) {
			int[] peopleLocation = people.get(i);
			distance[i][0] = Math.abs(stairs[0][0] - peopleLocation[0]) + Math.abs(stairs[0][1] - peopleLocation[1]);
			distance[i][1] = Math.abs(stairs[1][0] - peopleLocation[0]) + Math.abs(stairs[1][1] - peopleLocation[1]);
		}
	}

	static void dfs(int idx, ArrayList<Integer> stair_0, ArrayList<Integer> stair_1) {

		// 모든 사람의 계단 선택 완료
		if (idx == people_count) {
			// 원본 리스트의 순서를 유지하도록 복사해서 계산
			int time_0 = calculate_time(new ArrayList<>(stair_0), stair0_length);
			int time_1 = calculate_time(new ArrayList<>(stair_1), stair1_length);

			min_time = Math.min(min_time, Math.max(time_0, time_1));
			return;
		}

		// 현재 사람이 0번 계단 선택
		stair_0.add(distance[idx][0]);
		dfs(idx + 1, stair_0, stair_1);
		stair_0.remove(stair_0.size() - 1);

		// 현재 사람이 1번 계단 선택
		stair_1.add(distance[idx][1]);
		dfs(idx + 1, stair_0, stair_1);
		stair_1.remove(stair_1.size() - 1);
	}

	static int calculate_time(ArrayList<Integer> arrival, int length) {
		if (arrival.isEmpty())
			return 0;

		Collections.sort(arrival);

		int[] finish = new int[arrival.size()];

		for (int i = 0; i < arrival.size(); i++) {
			int start = arrival.get(i) + 1;

			if (i >= 3) {
				start = Math.max(start, finish[i - 3]);
			}

			finish[i] = start + length;
		}

		return finish[finish.length - 1];
	}
}