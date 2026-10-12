import java.util.Scanner;

public class Solution {
	static int day, month, threeMonth, year;
	static int[] plan = new int[13];
	static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			day = sc.nextInt();
			month = sc.nextInt();
			threeMonth = sc.nextInt();
			year = sc.nextInt();
			
			for(int i=1; i<=12; i++) {
				plan[i]=sc.nextInt();
			}
			
			answer = year; // 일단 year을 정답 후보로 둠
			dfs(1, 0); // 1월부터 0원으로 시작
			
			System.out.println("#"+tc+" "+answer);
		}
	}

	// m: 이번에 결정할 달, cost: 지금까지 쓴 돈
	private static void dfs(int m, int cost) {
		if(m>12) { // 12월까지 다 결정했으면
			answer=Math.min(cost, answer); // 최솟값 갱신
			return;
		}
		
		dfs(m+1, cost+plan[m]*day); // 1. 이번 달을 1일권으로
		dfs(m+1, cost+month); // 2. 이번 달을 1달권으로
		dfs(m+3, cost+threeMonth); // 3. 이번 달부터 3달을 3달권으로
		
	}
}
