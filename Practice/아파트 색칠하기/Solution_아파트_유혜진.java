import java.util.Scanner;

public class Solution_아파트_유혜진 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] f=new int[n+1];
		
		f[1]=2;
		f[2]=3;
		
		for(int i=3; i<=n; i++) {
			int topYellow = f[i-1];
			int topBlue = f[i-2];
			
			f[i] = topYellow+topBlue;
		}
		
		System.out.println(f[n]);
	}
}
