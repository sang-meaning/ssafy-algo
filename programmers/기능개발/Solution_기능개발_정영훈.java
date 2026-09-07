import java.util.*;
class Solution_기능개발_정영훈 {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0; i<progresses.length; i++){
            int remainPercent=100-progresses[i];
            int remainDay=remainPercent/speeds[i]+(remainPercent%speeds[i]==0?0:1);
            queue.offer(remainDay);
            
        }
        int last=queue.peek();
        int count=0;
        List<Integer> deployList=new ArrayList<>();
        while(!queue.isEmpty()){
            int current=queue.poll();
            if(last<current){
                last=current;
                deployList.add(count);
                count=1;
            }else{
                count++;
            }
        }

        deployList.add(count);
        int[] answer = new int[deployList.size()];

        for(int i=0; i<deployList.size(); i++){
            answer[i]=deployList.get(i);
        }
        
        return answer;
    }
}