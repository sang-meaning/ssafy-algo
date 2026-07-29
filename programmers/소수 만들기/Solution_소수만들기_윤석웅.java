package com.ssafy.swea;

import java.util.*;

public class Solution12977 {
	public int[] isPrime;

	public static int solution(int[] nums) {
		boolean[] isPrime = new boolean[3001];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		for (int i = 2; i * i <= 3000; i++) {
			if (isPrime[i]) {
				for (int j = 2 * i; j <= 3001; j += i) {
					isPrime[j] = false;
				}
			}
		}

		int answer = 0;
		int m = nums.length;

		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				for (int k = j + 1; k < m; k++) {
					if (isPrime[nums[i] + nums[j] + nums[k]]) {
						System.out.println(nums[i] + nums[j] + nums[k]);
						answer++;
					}
				}
			}
		}

		return answer;
	}
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4};
		int result = solution(nums);
		System.out.println(result);
	}
}
