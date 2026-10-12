package programers;

import java.util.*;
import java.io.*;




class Solution_1 {
	int result = 0;
	
	public int check(int n) {

		if (n < 2) {
			return 0;
		}

		for (int i = 2; i * i < n + 1; i++) {
			if (n % i == 0) {
				return 0;
			}
		}

		return 1;
	}
	
	public void combi(int[] nums, int start, int depth, int sum) {
		
		if (depth == 3) {
			result += check(sum);
			return;
		}
		
		for (int i = start; i < nums.length; i++) {
			combi(nums, i + 1, depth + 1, sum + nums[i]);
		}
	}
	
    public int solution(int[] nums) {
    
    	combi(nums, 0, 0, 0);
    	return result;
    	
    }
}


public class Solution_소수만들기_하상호 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
		
		int[] arr = new int[st.countTokens()];
		
		for (int i = 0; i < arr.length; i++) {
		    arr[i] = Integer.parseInt(st.nextToken().trim());
		}
		
		Solution_1 sl = new Solution_1();
		int result = sl.solution(arr);
		
		System.out.println(result);
		
	}
}