import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] array = new int[N];
			int[] a = new int[N];

			for (int i = 0; i < N; i++) {
				array[i] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				a[i] = 1;
				for(int j=0;j<i;j++) {
					if(array[j]<array[i]) {
						a[i] = Math.max(a[i], a[j]+1);
					}
				}
			}
			Arrays.sort(a);
			int answer = a[N-1];
			System.out.println("#" + tc + " " + answer);
		}
	}
}
