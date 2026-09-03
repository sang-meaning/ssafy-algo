import java.util.ArrayList;
import java.util.List;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        
        //93 30 55
        // 1 30 5
        //7 3 9
        // 2 1 출력
        
        // 100-progresses . 45/speeds,if 45%sppeds!=0 speedcnt +1
        int size=progresses.length;
        int[] remain=new int[size];
        int[] last=new int[size];
      

        for(int i=0;i<size;i++){
            remain[i]=100-progresses[i];
            last[i]=remain[i]/speeds[i];
            if(remain[i]%speeds[i]!=0)
                last[i]++;
        }
        int idx=0;
        
        List<Integer> answer = new ArrayList<>();
        int maxDay = last[0]; // 7 3 9
        int count = 1;
        for (int i = 1; i < size; i++) {
            if (last[i] <= maxDay) {
                count++;
            } else {
                answer.add(count);
                maxDay = last[i];
                count = 1;
            }
        }
        answer.add(count); 
        
        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
             result[i] = answer.get(i);
        }

        return result;
    }
}