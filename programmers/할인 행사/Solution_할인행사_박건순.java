import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(
            String[] want,
            int[] number,
            String[] discount
    ) {
        int answer = 0;
        int[] windowCount = new int[want.length];

        Map<String, Integer> wantIndex = new HashMap<>();

        for (int i = 0; i < want.length; i++) {
            wantIndex.put(want[i], i);
        }

        for (int i = 0; i < 10; i++) {
            addProduct(discount[i], wantIndex, windowCount);
        }

        if (Arrays.equals(number, windowCount)) {
            answer++;
        }

        for (int right = 10; right < discount.length; right++) {
            int left = right - 10;

            removeProduct(
                    discount[left],
                    wantIndex,
                    windowCount
            );

            addProduct(
                    discount[right],
                    wantIndex,
                    windowCount
            );

            if (Arrays.equals(number, windowCount)) {
                answer++;
            }
        }

        return answer;
    }

    private void addProduct(
            String product,
            Map<String, Integer> wantIndex,
            int[] windowCount
    ) {
        Integer index = wantIndex.get(product);

        if (index != null) {
            windowCount[index]++;
        }
    }

    private void removeProduct(
            String product,
            Map<String, Integer> wantIndex,
            int[] windowCount
    ) {
        Integer index = wantIndex.get(product);

        if (index != null) {
            windowCount[index]--;
        }
    }
}