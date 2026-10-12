package al_prac.pgms;
import java.util.*;

public class Solution_올바른괄호_하상호 {
    public boolean solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (current == '(') {
                stack.push(current);
            } else {
                // 짝을 맞출 '('가 없는 경우
                if (stack.isEmpty()) {
                    return false;
                }

                stack.pop();
            }
        }

        // '('가 남아 있으면 짝이 맞지 않음
        return stack.isEmpty();
    }
}

