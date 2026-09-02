import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> answer = new LinkedList<>();
        Queue<Integer> pro_q = new LinkedList<>();
        Queue<Integer> pro_s = new LinkedList<>();
        int count = 0;
        for(int i = 0; i < progresses.length; i++){
            pro_q.offer(progresses[i]);
        }
        for(int i = 0; i < speeds.length; i++){
            pro_s.offer(speeds[i]);
        }
        while(!pro_q.isEmpty()){
            count = 0;
            for(int i = 0; i < pro_q.size(); i++){
                int q = pro_q.poll();
                int s = pro_s.poll();
                pro_q.offer(q+s);
                pro_s.offer(s);
            }
            while(!pro_q.isEmpty()){
                if(pro_q.peek() >= 100){
                    System.out.println(pro_q.peek());
                    count++;
                    pro_q.poll();
                    pro_s.poll();
                }else{
                    break;
                }
            }
            if(count > 0){
                answer.offer(count);   
            }
        }
        int[] answer1 = new int[answer.size()];
        for(int i = 0; i < answer1.length; i++){
            answer1[i] = answer.poll();
        }
        return answer1;
    }
}