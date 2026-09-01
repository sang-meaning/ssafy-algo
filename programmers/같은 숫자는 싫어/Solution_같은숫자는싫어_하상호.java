package al_prac.pgms;
import java.util.*;

public class Solution_같은숫자는싫어_하상호 {

    public int[] solution(int[] arr) {
        List<Integer> answerList = new ArrayList<>();

        // 첫 번째 숫자는 무조건 추가
        answerList.add(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            // 바로 앞 숫자와 다를 때만 추가
            if (arr[i] != arr[i - 1]) {
                answerList.add(arr[i]);
            }
        }

        int[] answer = new int[answerList.size()];

        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}
