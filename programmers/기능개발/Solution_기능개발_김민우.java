import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        Queue<Integer> devList = new LinkedList<>();
        List<Integer> doneNum = new ArrayList<>();
        
        for(int i = 0; i < progresses.length; i++){
            devList.offer(i);
        }
        
        while(!devList.isEmpty()){
            for(int i= 0; i < devList.size(); i++){
                int idx = devList.poll();
                progresses[idx] += speeds[idx];
                devList.offer(idx);
            }
            
            int cnt = 0;
            int idx = devList.peek();
            
            if(progresses[idx] >= 100){
                while(progresses[idx] >= 100){
                    devList.poll();
                    cnt++;
                    if(!devList.isEmpty())
                        idx = devList.peek();
                    else
                        break;
                }
            }
            
            if(cnt != 0)
                doneNum.add(cnt);
        }
        
        int[] answer = new int[doneNum.size()];
        for(int i = 0; i < doneNum.size(); i++){
            answer[i] = doneNum.get(i);
        }
        return answer;
    }
}