import java.util.*;

class Solution {
    int[] num_list;
    int count = 0;
    int tar;
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        num_list = numbers;
        tar = target;
        
        dfs(0,0);
        answer = count;
        return answer;
    }
    
    public void dfs(int index, int sum){     
        if(index == num_list.length){
            if(sum == tar){
                count++;
            }
            return;
        }
        dfs(index +1,sum + num_list[index]);
        dfs(index +1,sum - num_list[index]);
    }  
}