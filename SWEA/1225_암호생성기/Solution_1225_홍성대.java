import java.util.*;

class Solution {
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = 10;
		
		
		for (int tc = 1; tc <= T; tc++) {
			int n;
			n = sc.nextInt();
			int [] a = new int[8];
			for (int i = 0; i < 8; i++) {
				a[i] = sc.nextInt();
			}
			int count = 0;
			while (a[7] > 0) {
				int temp = a[0] - (count %5 + 1);
				a[0] = a[1];
				a[1] = a[2];
				a[2] = a[3];
				a[3] = a[4];
				a[4] = a[5];
				a[5] = a[6];
				a[6] = a[7];
				a[7] = temp;
				count++;
				if (a[7] < 0) {
					a[7] = 0;
				}
			}

			System.out.print("#" + tc);
			for (int i = 0; i < 8; i++) {
				System.out.print(" " + a[i]);
			}
			System.out.println();
		}
	}
}
