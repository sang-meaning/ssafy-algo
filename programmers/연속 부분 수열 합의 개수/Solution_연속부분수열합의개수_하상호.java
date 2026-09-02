package al_prac.pgms;
import java.util.*;

public class Solution_연속부분수열합의개수_하상호 {
    public int solution(int[] elements) {
        Set<Integer> sums = new HashSet<>();
        int n = elements.length;

        // 시작 위치
        for (int start = 0; start < n; start++) {
            int sum = 0;

            // 부분 수열의 길이
            for (int length = 1; length <= n; length++) {
                int index = (start + length - 1) % n;
                sum += elements[index];
                sums.add(sum);
            }
        }

        return sums.size();
    }
}
