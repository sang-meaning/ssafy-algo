class Solution_소수만들기_정영훈 {
    static int[] nums;
    static int answer;
    static boolean[] visited;
    public int solution(int[] nums) {
        this.nums=nums;
        answer=0;
        visited=new boolean[nums.length+1];
        bt(0,0,0);
        return answer;
    }
    
    public void bt(int depth, int sum, int start){
        if(depth==3){
            if(isPrime(sum))
                answer++;
            return;
        }
        
        for(int i=start; i<nums.length; i++){
            bt(depth+1, sum+nums[i],i+1);
        }
        
        
        
    }
     public boolean isPrime(int n) {
        boolean[] check=new boolean[n+1];
        check[0]=check[1]=true;
        for(int i=2; i*i<=n; i++){
            if(check[i]==true)continue;
            for(int j=i+i; j<=n; j+=i)
                check[j]=true;
                
        }
        if(!check[n])
            return true;
         return false;
    }
}