import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = new int[progresses.length];
        
        Queue<Integer> que = new ArrayDeque<>();
        for(int i = 0; i < progresses.length; i++){
            // 남은 작업일 수 계산
            que.offer(counting(progresses[i], speeds[i]));
        }

        int size = 0;
        for(size = 0; que.isEmpty() == false ; size++){
            int cnt = 1;
            int current = que.poll();
            while(!que.isEmpty() && current >= que.peek()){
                que.poll();
                cnt++;
            }
            answer[size] = cnt;
        }
        
        
        return Arrays.copyOf(answer, size);
    }
    private int counting(int progress, int speed){
        int c = 0;
        while(progress<100){
            progress += speed;
            c++;
        }
        return c;
    }
}