import java.util.Scanner;

public class Solution {

	static String[] array;
	static int n;
	static int answer;

	static void dfs(int cur) {
		if(cur > n) {
			return;
		}
		dfs(cur*2);
		char temp = array[cur].charAt(0);

		if(cur*2 <= n) {
			if(!(temp=='+' || temp=='-' || temp=='*' || temp=='/')) {
				answer = 0;
			}
		}else {
			if(temp=='+' || temp=='-' || temp=='*' || temp=='/') {
				answer = 0;
			}
		}
        
		dfs(cur*2+1);
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		for(int tc=1; tc<=10; tc++) {
			n = Integer.parseInt(sc.nextLine());
			array = new String[n+1];
			answer = 1;

			for(int i=0; i<n; i++) {
				String line = sc.nextLine();
				String[] temp = line.split(" ");
				int num = Integer.parseInt(temp[0]);
				array[num] = temp[1];
			}
			dfs(1);
			System.out.println("#"+tc+" "+answer);
		}
	}
}
