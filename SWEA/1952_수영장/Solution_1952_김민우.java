import java.util.*;
import java.io.*;

public class Solution_1952_김민우 {
	
	static int T, answer;
	static int[] fee;
	static int[] plans;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			fee = new int[4];
			plans = new int[13];
			
			st = new StringTokenizer(br.readLine());
			for(int i =0; i < 4; i++)
				fee[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i =1; i <= 12; i++)
				plans[i] = Integer.parseInt(st.nextToken());
			
			//임시 tmp값으로 1년 이용권 할당
			//탐색할 때는 해당 경우 제외,,
			answer = fee[3];
			dfs(1, 0);
			
			System.out.printf("#%d %d\n", test_case, answer);
		}//test_case 끝
	}//solution 끝

	public static void dfs(int cnt, int sum) {
		if(sum >= answer)
			return;
		if(cnt > 12) {
			answer = sum;
			return;
		}
		
		if(plans[cnt] == 0)
			dfs(cnt+1, sum);
		
		else {
			for(int i = 0; i <= 2; i++) {
				switch(i) {
				case 0:
					int cur0 = plans[cnt] * fee[0];
					dfs(cnt+1, sum+cur0);
					break;
				case 1:
					int cur1 = fee[1];
					dfs(cnt+1, sum+cur1);
					break;
				case 2:
					int cur2 = fee[2];
					dfs(cnt+3, sum+cur2);
					break;
				}
			}
		}
	}//dfs 끝
}
