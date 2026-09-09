class Solution {
    static int cnt=0;
    public int solution(int[] number) {
        
        /*
        -2 3 0 2 -5
        3명의 학생을 더할때 0이여야 삼총사
        134,245
        dfs를 써야함
        depth=3이고, 0이 될때만 cnt++;
        */
        dfs(number,0,0,0);
        
        
        return cnt;
    }
    
    static void dfs(int[] number,int depth,int limit,int sum){
        
        if(depth==3){
            if(sum==0)
                cnt++;
            
            return;
        }
        
        if(number.length==limit){
            return;
        }
        
        dfs(number,depth+1,limit+1,sum+number[limit]);
        dfs(number,depth,limit+1,sum);
        
    }
}