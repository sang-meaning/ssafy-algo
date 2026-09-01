package al_prac.pgms;
import java.util.*;

public class Solution_크레인인형뽑기게임_하상호 {
    public int solution(int[][] board, int[] moves) {
        Deque<Integer> basket = new ArrayDeque<>();

        int answer = 0;
        int n = board.length;

        for (int move : moves) {
            // moves의 위치는 1부터 시작하므로 1을 뺌
            int column = move - 1;

            // 해당 열의 위쪽부터 탐색
            for (int row = 0; row < n; row++) {
                if (board[row][column] == 0) {
                    continue;
                }

                int doll = board[row][column];

                // 뽑은 위치를 빈칸으로 변경
                board[row][column] = 0;

                // 바구니 위의 인형과 같으면 제거
                if (!basket.isEmpty() && basket.peek() == doll) {
                    basket.pop();
                    answer += 2;
                } else {
                    basket.push(doll);
                }

                // 한 번 뽑았으면 다음 move로 이동
                break;
            }
        }

        return answer;
    }
}
