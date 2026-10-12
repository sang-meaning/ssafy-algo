import java.util.ArrayList;
import java.util.Arrays;
class Solution {
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    ArrayList<Integer> answerList;
    int y;
    int x;
    public int[] solution(String[] maps) {
        answerList = new ArrayList<>();
        y = maps.length;
        x = maps[0].length();
        
        //x 면 -> 넘어가기
        // 숫자면 -> dfs
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                if(maps[j].charAt(i) == 'X') {
                    continue;
                }
                int sum = dfs(i,j,maps);
                answerList.add(sum);
            }
        } 
        
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        if(answer.length == 0){
            answer = new int[1];
            answer[0] = -1;
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
    
    public int dfs(int i, int j, String[] maps) {
        int sum = maps[j].charAt(i) - '0';
        // 본인 있는곳 X로 바까버리기
        String temp = maps[j].substring(0,i) + 'X' + maps[j].substring(i+1,x);
        maps[j] = temp;
        
        for(int k = 0; k < 4; k++) {
            if(i + dir[k][0] < 0 ||i + dir[k][0] >= x ||j + dir[k][1] < 0 ||j + dir[k][1] >= y)
                continue;
            if(maps[j+dir[k][1]].charAt(i+dir[k][0]) != 'X')
                sum += dfs(i+dir[k][0], j+dir[k][1], maps);
        }
        return sum;
    }
  
}