import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> basket = new Stack<>();
        
        // 크레인 이동 명령을 순서대로 처리
        for (int move : moves) {
            int col = move - 1; // moves는 1부터 시작하므로 인덱스(0부터)에 맞게 -1
            
            // 해당 열의 위쪽(0)부터 아래쪽으로 내려가며 인형 탐색
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] != 0) { // 인형이 있는 경우
                    int doll = board[row][col];
                    board[row][col] = 0; // 인형을 집었으므로 빈 칸(0)으로 변경
                    
                    // 바구니 상단에 있는 인형과 방금 집은 인형이 같으면 터뜨림
                    if (!basket.isEmpty() && basket.peek() == doll) {
                        basket.pop();
                        answer += 2; // 사라진 인형 2개 추가
                    } else {
                        basket.push(doll); // 다른 인형이면 바구니에 담음
                    }
                    
                    break; // 인형을 하나 집었으므로 해당 move 종료
                }
            }
        }
        
        return answer;
    }
}