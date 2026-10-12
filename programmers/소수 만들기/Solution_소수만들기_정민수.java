
import java.util.*;

public class Solution2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int count = 0;
		for(int i=0; i<arr.length-2; i++) {
			for(int j=i+1; j<n-1; j++) {
				for(int k=j+1; k<n; k++) {
					int sum = arr[i] + arr[j] + arr[k];
					if(result(sum)) {
						count++;
					}
				}
			}
			
			
		}
		System.out.println(count);

	}
	
	public static boolean result(int sum) {
		
		boolean bl = true;
		for(int i=2; i<sum/2; i++) {
			if(sum%i == 0) {
				bl = false;
				break;
			}
		}
		
		
		return bl;
	}

}
