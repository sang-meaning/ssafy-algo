package _submission;

import java.util.HashMap;
import java.util.Map;

public class Solution {

	// 투포인터랑 map?
	// for 10 : e ++ , discount[] +1
	// if possible thing want[] == number cnt++

	// 오답 이유 : 조건 체크 타이밍이 잘못되어 첫날을 확인하지 않았었음.

	static public int solution(String[] want, int[] number, String[] discount) {

		Map<String, Integer> cart = new HashMap<>();

		int s = 0, e = 0;

		for (int i = 0; i < 10; i++) { // 초기 10일

			if (cart.get(discount[e]) != null) {
				cart.put(discount[e], cart.get(discount[e]) + 1);
			} else {
				cart.put(discount[e], 1);
			}
			e++;
		}

		int cnt = 0;

		while (e != discount.length) {

			boolean satisfied = true;
			for (int i = 0; i < want.length; i++) {
				if (cart.get(want[i]) == null || cart.get(want[i]) < number[i]) {
					satisfied = false;
					break;
				}
			}
			if (satisfied) {
				cnt++;
			}

			if (cart.get(discount[e]) != null) {
				cart.put(discount[e], cart.get(discount[e]) + 1);
			} else {
				cart.put(discount[e], 1);
			}
			e++;

			cart.put(discount[s], cart.get(discount[s]) - 1);
			s++;

		}

		for (int i = 0; i < 10; i++) { // 마지막 체크

			boolean satisfied = true;
			for (int j = 0; j < want.length; j++) {
				if (cart.get(want[j]) == null || cart.get(want[j]) < number[j]) {
					satisfied = false;
					break;
				}
			}
			if (satisfied) {
				cnt++;
			}
			
			cart.put(discount[s], cart.get(discount[s]) - 1);
			s++;
		}

		return cnt;
	}
}
