package algorithm_solution;

public class PROG_12977 {
	public static int solution(int[] nums) {
        int answer = 0;
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");
        for(int i = 0; i < nums.length; i++) {
        	for(int j = i + 1; j < nums.length; j++) {
        		for(int k = j + 1; k < nums.length; k++) {
        			int num = nums[i] + nums[j] + nums[k];
        			boolean isPrime = true;
        			for(int l = 2; l * l <= num; l++) {
        				if(num % l == 0) {
        					isPrime = false;
        				}
        			}
        			if(isPrime) {
        				answer++;
        			}
        		}
        	}
        }
        return answer;
    }
	public static void main(String[] args) {
		int[] nums = {1,2,3,4};
		System.out.println(solution(nums));
	}
}
