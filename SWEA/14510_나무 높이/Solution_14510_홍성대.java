import java.util.Scanner;

public class Solution_14510_홍성대 {
	public static void main(String[] args) {
		//간단한 문제라고 생각하고 아무생각 없이 시작했는데 생각보다 변수가 많은 문제라 예외처리 하느라 코드가 난잡해졌습니다.
		//추후에 다시 풀어보도록 하겠습니다.
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++){
			int daycount = 1;
			boolean same = false;
			int n = sc.nextInt();
			int [] a = new int[n];
			
			for(int i = 0; i < n; i++) {
				int h = sc.nextInt();
				a[i] = h;
			}
			if (isAllEqual(a) == true) {
				System.out.println("#" + test_case + " " + 0);
				continue;
			}
			while(same == false){
				int lowval = 130;
				int maxval = 0;
				int total = 0;
				int loc = 0;
				for(int i = 0; i < n; i++) {
					if(a[i] >= maxval) {
						maxval = a[i];
					}
					if(a[i] <= lowval) {
						lowval = a[i];
						loc = i;
					}
				}
				for(int i = 0; i < n; i++) {
					if(maxval - a[i] == 1 && daycount % 2 == 1) {
						loc = i;
					}
					else if(maxval - a[i] == 2 && daycount % 2 == 0) {
						loc = i;
					}
					if(a[i] < maxval) {
						total += maxval - a[i];
					}
				}
				
				if((daycount % 2 == 1 && (maxval - a[loc]) %2 == 1) || (daycount % 2 == 1 && total >= 4)) {
					a[loc] += 1;
				}
				else if((daycount % 2 == 0 && (maxval - a[loc]) %2 == 0) || (daycount % 2 == 0 && total >= 3)
						&& (maxval - a[loc] >= 2 || maxval <= total)){
					a[loc] += 2;
				}
				if (isAllEqual(a) == true) {
					break;
				}
				daycount++;
				
			}
			System.out.println("#" + test_case + " " + daycount);
		}
	}
	public static boolean isAllEqual(int[] arr) {
	    int first = arr[0];
	    for (int i = 1; i < arr.length; i++) {
	        if (arr[i] != first) {
	            return false;
	        }
	    }
	    return true;
	}
}
