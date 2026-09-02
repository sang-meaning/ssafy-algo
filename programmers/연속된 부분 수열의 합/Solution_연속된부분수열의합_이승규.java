class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        answer[0] = 0;
        answer[1] = 0;
        int answerLen = Integer.MAX_VALUE;
        
        int pointerA = 0;
        int pointerB = 0;
        
        while(pointerA < sequence.length) {
            int temp = calculate(pointerA, pointerB, sequence);
            if(temp > k) {
                pointerA++;
            } else if(temp < k) {
                pointerB++;
                if(pointerB == sequence.length)
                    break;
                
            } else {
                if(answerLen > pointerB - pointerA + 1) {
                    answer[0] = pointerA;
                    answer[1] = pointerB;
                    answerLen = pointerB - pointerA + 1;
                }
                pointerB++;
                if(pointerB == sequence.length)
                    break;
            }
        }
        
        return answer;
    }
    
    static int calculate(int pointerA, int pointerB, int[] sequence) {
        int sum = 0;
        for(int i = pointerA; i <= pointerB; i++) {
            sum+= sequence[i];
        }
        return sum;
    }
}