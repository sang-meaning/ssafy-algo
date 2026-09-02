package al_prac.pgms;
import java.util.*;

public class Solution_구명보트_하상호 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;
        int boatCount = 0;

        while (left <= right) {
            // 가장 가벼운 사람과 가장 무거운 사람이 함께 탈 수 있음
            if (people[left] + people[right] <= limit) {
                left++;
            }

            // 가장 무거운 사람은 항상 이번 보트에 탑승
            right--;
            boatCount++;
        }

        return boatCount;
    }
}
