import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answerList = new ArrayList<>();
        //while(끝날때까지) 
        // progresses에 갖다 더한다
        // 100이 넘으면 -> speed를 0으로. counter++; 
        // pointer에 있는 친구가 100이 넘으면 -> counter를 정답에. 들어간놈은 -1로
        // pointer 이동. -1이 아니면 다음으로
        int cntCheck = 0;
        int pointer = 0;
        int counter = 0;
        
        while(pointer < progresses.length) {
            for(int i = pointer; i < progresses.length; i++) {
                progresses[i] += speeds[i];
                if(progresses[i] >= 100) {
                    speeds[i] = 0;
                    progresses[i] = -1;
                }
            } 
            if(progresses[pointer] == -1) {
                counter = 0;
                
                while (pointer < progresses.length && progresses[pointer] == -1) {
                    counter++;
                    pointer++;
                }
                
                answerList.add(counter);
            }
        }
        
        int[] answer = new int[answerList.size()];

        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}