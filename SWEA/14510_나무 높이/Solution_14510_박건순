import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

class Solution_14510_박건순 {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			List<Integer> arr = new ArrayList<>();
			int day = 0;
			int max = 0;
			int avg = 0;
			int mod = 0;
			int odd = 0;
			int even = 0;
			int numCount = sc.nextInt();

			for (int i = 0; i < numCount; i++) {
				int h = sc.nextInt();
				arr.add(h);
				if (max < h) {
					max = h;
				}
			}

			for (int i = 0; i < arr.size(); i++) {
				int sub = max - arr.get(i);
				odd += sub % 2;
				even += sub / 2;

			}

			if (odd > even) {
				day = odd * 2 - 1;
			} else {
				int total = odd + (even * 2);
				avg = total / 3;
				mod = total % 3;
				day = avg * 2;

				if (mod == 1) {
					day += 1;
				} else if (mod == 2) {
					day += 2;
				}
			}

			System.out.println("#" + test_case + " " + day);

		}
	}
}