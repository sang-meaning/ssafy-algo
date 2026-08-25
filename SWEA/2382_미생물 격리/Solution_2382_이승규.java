package coding_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution_2382_이승규 {

	static class Germ {
		int germX;
		int germY;
		int germNum;
		int moveDir;

		Germ(int x, int y, int num, int dir) {
			this.germX = x;
			this.germY = y;
			this.germNum = num;
			this.moveDir = dir;
		}
	}

	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String args[]) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken()); // 변의 길이 n
			int m = Integer.parseInt(st.nextToken()); // 실행시간 m
			int k = Integer.parseInt(st.nextToken()); // 군집 수 k

			ArrayList<Germ> germList = new ArrayList<>();

			for (int i = 0; i < k; i++) {
				germList.add(new Germ(0, 0, 0, 0));
			}

			for (int i = 0; i < k; i++) {
				s = bf.readLine();
				st = new StringTokenizer(s);
				germList.get(i).germX = Integer.parseInt(st.nextToken());
				germList.get(i).germY = Integer.parseInt(st.nextToken());
				germList.get(i).germNum = Integer.parseInt(st.nextToken());
				germList.get(i).moveDir = Integer.parseInt(st.nextToken()) - 1; // 0상 1하 2좌 3우
			}

			for (int i = 0; i < m; i++) {
				// m번 실행할게요

				// germList 싸그리 다 1칸씩옮기기
				for (int j = 0; j < germList.size(); j++) {
					germList.get(j).germX += dir[germList.get(j).moveDir][0];
					germList.get(j).germY += dir[germList.get(j).moveDir][1];
				}

				// mergeCheck. 같은 좌표 위에 있으면 값을 더하기. 방향은 젤루다가 큰놈으로
				// hashmap을 사용하자
				HashMap<Integer, int[]> mergeCheck = new HashMap<>(); // <x * n + y, [더한값,지금까지최대값,방향]

				for (int j = 0; j < germList.size(); j++) {
					int germPos = germList.get(j).germX * n + germList.get(j).germY;
					int[] germInfo = new int[3];

					if (mergeCheck.get(germPos) == null) { //
						germInfo[0] = germList.get(j).germNum;
						germInfo[1] = germList.get(j).germNum;
						germInfo[2] = germList.get(j).moveDir;

					} else {
						germInfo[0] = germList.get(j).germNum + mergeCheck.get(germPos)[0];
						if (germList.get(j).germNum > mergeCheck.get(germPos)[1]) { // 내가 더 크면
							germInfo[1] = germList.get(j).germNum;
							germInfo[2] = germList.get(j).moveDir;
						} else {
							germInfo[1] = mergeCheck.get(germPos)[1];
							germInfo[2] = mergeCheck.get(germPos)[2];
						}
					}
					mergeCheck.put(germPos, germInfo);
				}

				// hashmap을 다시 ArrayList로.
				germList = new ArrayList<>();
				ArrayList<Integer> posList = new ArrayList<>(mergeCheck.keySet());

				for (int j = 0; j < mergeCheck.size(); j++) {
					Germ newGerm = new Germ(0, 0, 0, 0);
					newGerm.germX = posList.get(j) / n;
					newGerm.germY = posList.get(j) % n;

					newGerm.germNum = mergeCheck.get(posList.get(j))[0];
					newGerm.moveDir = mergeCheck.get(posList.get(j))[2];

					germList.add(newGerm);
				}

				// medCheck. germX == 0 or germY == 0 or germX == n-1 or germY == n-1일 경우
				// 방향바꾸기 + 절반으로 나누기
				for (int j = 0; j < germList.size(); j++) {
					int nowX = germList.get(j).germX;
					int nowY = germList.get(j).germY;
					int nowDir = germList.get(j).moveDir;

					if (nowX == 0 || nowY == 0 || nowX == n - 1 || nowY == n - 1) {
						germList.get(j).germNum /= 2;
						if (nowDir == 0)
							germList.get(j).moveDir = 1;
						if (nowDir == 1)
							germList.get(j).moveDir = 0;
						if (nowDir == 2)
							germList.get(j).moveDir = 3;
						if (nowDir == 3)
							germList.get(j).moveDir = 2;
					}
				}
			}

			int answer = 0;

			for (int i = 0; i < germList.size(); i++) {
				answer += germList.get(i).germNum;
			}

			System.out.println("#" + test_case + " " + answer);
		}
	}
}
