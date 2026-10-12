import java.util.Scanner;

public class Solution_막대_유혜진 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] f = new int[n+1];
		
		f[0]=1;
		f[1]=2;
		f[2]=5;
		
		for(int i=3; i<=n; i++) {
			int stickBlue=f[i-1];
			int stickYellow=f[i-1];
			int stickRed=f[i-2];
			
			f[i]=stickBlue+stickYellow+stickRed;
		}
		System.out.println(f[n]);
	}
}
