import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	static int max_sum = -1;
	//answer 다시 돌려놓기
	static List<Integer> array = new ArrayList<>();
	static void backtracking(int start,int n, int limit_weight,int[] nums) {
		if(array.size() == 2) {
			int combination_sum=0;
			for(int i=0; i<2; i++) {
				combination_sum += array.get(i);
			}
			if ((combination_sum <= limit_weight) && (combination_sum > max_sum)) {
				max_sum = combination_sum;
			}
			return;
		}
					
		for (int i=start;i < n; i++ ) {
			array.add(nums[i]);
		backtracking(i+1, n ,limit_weight,nums);
			array.remove(array.size()-1);
		}
					
	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<= T; tc++) {
			int snack_num = sc.nextInt();
			int limiit_weight = sc.nextInt();
			int[] snack = new int[snack_num];
			for(int i=0; i<snack_num;i++) {
				snack[i]=sc.nextInt();
			}
			backtracking(0, snack_num, limiit_weight, snack);
			System.out.println("#"+tc+" "+max_sum);
			max_sum =-1;
		}
		
	}
}
