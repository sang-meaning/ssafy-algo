import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			double N;
			int answer=0;
			N = sc.nextDouble();
			while(N != 2.0) {
				if(Math.sqrt(N) % 1 == 0) {
					N = Math.sqrt(N);
                    answer++;
				}else {
					long temp = (long)Math.sqrt(N) + 1;
					answer += (temp * temp) - N;
					N = temp * temp;
				}
				
			}
		
			System.out.println("#"+tc+" "+answer);
		}
		
	}
}