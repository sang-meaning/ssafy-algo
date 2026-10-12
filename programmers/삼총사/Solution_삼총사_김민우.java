import java.util.*;

class Solution {
    static int result;
    static int[] copy;
    public int solution(int[] number) {
        result = 0;
        copy = new int[number.length];
        copy = Arrays.copyOf(number, number.length);
        dfs(-1, 0, 0);
        return result;
    }
    
    public void dfs(int idx, int cnt, int sum){
        if(cnt == 3){
            if(sum == 0)
                result++;
            return;
        }
        if(idx == copy.length-1 && cnt != 3)
            return;
        
        dfs(idx+1, cnt+1, sum+copy[idx+1]);
        dfs(idx+1, cnt, sum);
    }
}