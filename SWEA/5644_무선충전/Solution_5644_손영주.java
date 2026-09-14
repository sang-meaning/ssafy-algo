package submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class User {
	int[] seqX;
	int[] seqY;

	User(int initX, int initY, int step) {
		seqX = new int[step];
		seqY = new int[step];
		seqX[0] = initX;
		seqY[0] = initY;
	}
}

class BC {
	int x;
	int y;
	int coverage;
	int cap;

	public BC(int x, int y, int coverage, int cap) {
		this.x = x;
		this.y = y;
		this.coverage = coverage;
		this.cap = cap;
	}
}

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line, " ");

			int N = 10; // 지도 크기.

			int maxStep = Integer.parseInt(st.nextToken()); // 사용자 스탭 수
			int BCcount = Integer.parseInt(st.nextToken()); // BC 개수

			// 한 사용자의 이동 정보는 M개의 숫자로 구성되며, 각각의 숫자는 다음과 같이 매초마다 이동 방향을 의미한다.
			// 이동안함, 상, 우, 하, 좌

			int[] dx = { 0, 0, 1, 0, -1 };
			int[] dy = { 0, -1, 0, 1, 0 };

			User A = new User(1, 1, maxStep + 1);
			User B = new User(10, 10, maxStep + 1);

			st = new StringTokenizer(br.readLine(), " ");
			for (int stepIdx = 1; stepIdx <= maxStep; stepIdx++) {
				int dir = Integer.parseInt(st.nextToken());
				int x = A.seqX[stepIdx - 1]; // 이전 위치
				int y = A.seqY[stepIdx - 1]; // 이전 위치

				A.seqX[stepIdx] = x + dx[dir];
				A.seqY[stepIdx] = y + dy[dir]; // 이동한 위치 추가
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int stepIdx = 1; stepIdx <= maxStep; stepIdx++) {
				int dir = Integer.parseInt(st.nextToken());
				int x = B.seqX[stepIdx - 1]; // 이전 위치
				int y = B.seqY[stepIdx - 1]; // 이전 위치

				B.seqX[stepIdx] = x + dx[dir];
				B.seqY[stepIdx] = y + dy[dir]; // 이동한 위치 추가
			}

			BC[] BCArr = new BC[BCcount];

			for (int BCIdx = 0; BCIdx < BCcount; BCIdx++) {
				st = new StringTokenizer(br.readLine(), " ");

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				int coverage = Integer.parseInt(st.nextToken());
				int cap = Integer.parseInt(st.nextToken());

				BCArr[BCIdx] = new BC(x, y, coverage, cap);
			}

			int total = 0;
			for (int sec = 0; sec <= maxStep; sec++) {
				// 유저가 가능한 BC list 구하기
				List<BC> BCofA = new ArrayList<>();
				List<BC> BCofB = new ArrayList<>();

				for (BC bc : BCArr) {
					int distanceA = Math.abs(bc.x - A.seqX[sec]) + Math.abs(bc.y - A.seqY[sec]);
					if (distanceA <= bc.coverage) {
						BCofA.add(bc);
					}
					int distanceB = Math.abs(bc.x - B.seqX[sec]) + Math.abs(bc.y - B.seqY[sec]);
					if (distanceB <= bc.coverage) {
						BCofB.add(bc);
					}
				}

				// 모든 배당 해서 최대값 구하기
				int max = selectBC(BCofA, BCofB);
				total += max;
			}
			System.out.println("#" + test_case_num + " " + total);
		}
	}

	// 해당 초에 user가 접근 가능한 BC list -> A에서 하나 고르고, B에서 하나 고르는 모든 경우의 수 중 가장 큰 것
	public static int selectBC(List<BC> BCofA, List<BC> BCofB) {

		int max = 0;

		if (BCofA.isEmpty()) {
			for (BC bcB : BCofB) {
				max = Math.max(max, bcB.cap);
			}
		}

		if (BCofB.isEmpty()) {
			for (BC bcA : BCofA) {
				max = Math.max(max, bcA.cap);
			}
		}

		for (BC bcA : BCofA) {
			for (BC bcB : BCofB) {
				if (bcA.equals(bcB)) {
					max = Math.max(max, bcA.cap);
				} else {
					max = Math.max(max, bcA.cap + bcB.cap);
				}
			}
		}
		return max;
	}
}
