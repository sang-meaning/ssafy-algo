package swea;
import java.util.*;
public class Solution_1952 {
	static int dayprice;
	static int monthprice;
	static int threemonthprice;
	static int yearprice;
	static int[] plan;
	static int[] memo;
	static int minCost(int m) {
		if(m<= 0) {
			return 0;
		}
		
		if(memo[m] != -1) {
			return memo[m];
		}
		
		int monthCost = Math.min(plan[m] * dayprice, monthprice);
		int cost1 = minCost(m - 1) + monthCost;
		
		int cost3 = minCost(m - 3) + threemonthprice;
		
		return memo[m] = Math.min(cost1, cost3);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			dayprice = sc.nextInt();
			monthprice = sc.nextInt();
			threemonthprice = sc.nextInt();
			yearprice = sc.nextInt();
			plan = new int[13];
			memo = new int[13];
			Arrays.fill(memo, -1);
			
			for(int i = 1;i <= 12; i++) {
				plan[i] = sc.nextInt();
			}
			
			int answer = Math.min(minCost(12), yearprice);
			System.out.println("#" + tc + " " + answer);
		}
	}
}
