package programers;

import java.util.*;
import java.io.*;

class Solution {

	public int check(int n) {

		if (n < 2) {
			return 0;
		}

		for (int i = 2; i * i < n + 1; i++) {
			if (n % i == 0) {
				return 0;
			}
		}

		return 1;
	}

	public int solution(int n) {

		int count = 0;

		for (int i = 1; i < n + 1; i++) {
			count += check(i);
		}

		return count;
	}
}

public class Solution_소수찾기_하상호 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());

		Solution st = new Solution();
		int result = st.solution(num);

		System.out.println(result);
	}

}
