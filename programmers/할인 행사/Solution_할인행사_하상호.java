package al_prac.pgms;
import java.util.*;

public class Solution_할인행사_하상호 {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> required = new HashMap<>();
        Map<String, Integer> window = new HashMap<>();

        for (int i = 0; i < want.length; i++) {
            required.put(want[i], number[i]);
        }

        // 처음 10일의 할인 제품 저장
        for (int i = 0; i < 10; i++) {
            window.put(
                discount[i],
                window.getOrDefault(discount[i], 0) + 1
            );
        }

        int answer = 0;

        if (isPossible(required, window)) {
            answer++;
        }

        // 10일짜리 구간을 한 칸씩 이동
        for (int start = 1; start <= discount.length - 10; start++) {
            String removedProduct = discount[start - 1];
            String addedProduct = discount[start + 9];

            // 이전 구간의 첫 번째 제품 제거
            window.put(
                removedProduct,
                window.get(removedProduct) - 1
            );

            // 새로운 제품 추가
            window.put(
                addedProduct,
                window.getOrDefault(addedProduct, 0) + 1
            );

            if (isPossible(required, window)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isPossible(
        Map<String, Integer> required,
        Map<String, Integer> window
    ) {
        for (String product : required.keySet()) {
            int requiredCount = required.get(product);
            int discountCount = window.getOrDefault(product, 0);

            if (requiredCount != discountCount) {
                return false;
            }
        }

        return true;
    }
}
