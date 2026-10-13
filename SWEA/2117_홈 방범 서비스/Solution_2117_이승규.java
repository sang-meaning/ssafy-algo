package algo_prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_2117_이승규 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringTokenizer st;
		StringBuilder sb;

		for (int test_case = 1; test_case <= T; test_case++) {
			sb = new StringBuilder();
			st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			ArrayList<int[]> house = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < n; j++) {
					if (Integer.parseInt(st.nextToken()) == 1) { // 1일때
						int[] housePos = new int[2];
						housePos[0] = i;
						housePos[1] = j;
						house.add(housePos);
					}
				}
			}

			int maxHouse = 0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int range = 0; range < 2 * n; range++) {
						int houseCnt = 0;
						int cost = (range + 1) * (range + 1) + (range * range);
						
						for (int houseNum = 0; houseNum < house.size(); houseNum++) {
							int[] housePos = house.get(houseNum);
							int dist = Math.abs(housePos[0] - i) + Math.abs(housePos[1] - j);

							if (dist > range)
								continue;

							houseCnt++;
						}
						if ((houseCnt * m) - cost >= 0) {
							if (maxHouse < houseCnt) {
								maxHouse = houseCnt;
							}
						}
					}
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(maxHouse);
			System.out.println(sb);
		}
	}
}
