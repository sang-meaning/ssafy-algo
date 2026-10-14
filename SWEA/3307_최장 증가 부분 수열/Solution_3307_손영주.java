package _submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			// 수열의 길이 N
			int N = Integer.parseInt(br.readLine());
			// 숫자 배열
			int[] nums = new int[N];

			int[] dp = new int[N];
			int length = 0;

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			// input

			// lst 의 기본 아이디어
			// dp[i] = i길이의 단조 증가 순열들의 마지막 주자 중 최소값
			// 바이너리 서치. 어레이는 메서드가 있으나, 어퍼바운드 / 로워바운드 위치를 보장하지 않으므로 구현하기

			for (int i = 0; i < N; i++) {// 모든 원소 들에 대해
				int idx = binarySearch(nums[i], length, dp);
				if (idx == length)
					length++;
				dp[idx] = nums[i];
			}

			System.out.println("#" + test_case + " " + length);
		}
	}

	// 바이너리 서치 너무 어려움
	// 로워 바운드 arr[i] >= target (값 이상이 처음 나오는 곳)
	// 어퍼 바운드 arr[i] > target (값 초과가 처음 나오는 곳)
	static int binarySearch(int target, int length, int[] arr) {
		// 타겟를 초과하는 숫자가 처음 나오는 곳 돌려주기.
		int high = length;
		int low = 0;

		while (low < high) { // 같거나 커지면 끝
			int mid = (high + low) / 2; // 내림이 됨 -> high와 low 가 1 차이면 mid는 low
			if (arr[mid] > target) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

}
