import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int count = 0;
		
		int n = sc.nextInt();
		
		
		
		for(int i=2; i<=n; i++) {
			boolean bl = true;
		
			for(int j=2; j<=i/2; j++) {
				if(i%j==0) {
					bl = false;
					break;
				}
			}
			
			if(bl) {
				count++;
			}
		}
		
		System.out.println(count);
		

	}

}
