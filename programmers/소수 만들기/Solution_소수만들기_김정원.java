package programmers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_소수만들기_김정원 {
	static int MAX = 3001;
	static boolean[] primes = primes();
	static Map<Integer, Set<Integer>> visited = new HashMap<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		int[] arr = new int[st.countTokens()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solution(arr));
	}
	
    public static int solution(int[] nums) {
        // 먼저 소수는 어떻게 만들어지는지 규칙을 정리해보자
    	// 1. 소수는 일의 자리가 짝수이면 무조건 합성수 이다
    	// 2. 그러면 우리는 세개의 숫자가 더해진 결과가 짝수인가?
    	// 3. 미리 3000 까지 구해둔 소수들로 판별한다
    	int answer = 0;
    	// 숫자 배열의 길이
    	int n = nums.length;
    	int sum = 0;
    	
        for (int n1 = 0; n1 < n; n1++) {
        	for (int n2 = n1 + 1; n2 < n; n2++) {
        		for (int n3 = n2 + 1; n3 < n; n3++) {
        			sum = nums[n1] + nums[n2] + nums[n3];
        			if (sum % 2 == 0) continue;
        			if (primes[sum]) answer++;
        		}
        	}
        }
        return answer;
    }
    
    public static boolean[] primes() {
        boolean[] numbers = new boolean[MAX + 1];
        Arrays.fill(numbers, true);
        numbers[0] = false;
        numbers[1] = false;
        int prime = 2;
        boolean flag = false;
        while (!flag) {
        	flag = true;
        	for (int num = prime * 2; num <= MAX; num += prime) {
        		if (numbers[num]) numbers[num] = false;
        	}
        	for (int num = prime + 1; num < MAX; num++) {
        		if (numbers[num]) {
        			prime = num;
        			flag = false;
        			break;
        		}
        	}
        }
        return numbers;
    }
/*

*/
}
