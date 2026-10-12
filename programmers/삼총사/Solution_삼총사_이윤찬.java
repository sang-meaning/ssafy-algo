class Solution {
    static int[] output;
    static int n;
    static final int r=3;
    static int answer=0;
    public int solution(int[] number) {
        output = new int[3];
        n = number.length;
        combination(number,n,r,0,0,output);
        
        return answer;
    }
    
    public void combination(int[] number, int n , int r, int depth, int target,int[] output){
        if(depth==r){
            if(check()){
                answer++;
                return;
            }
            return;
        }
        if(target==n){
            return;
        }
        
        output[depth] =number[target];
        
        combination(number,n,r,depth+1,target+1,output);
        
        combination(number,n,r,depth,target+1,output);
    }
    
    public  boolean check(){
        int sum = 0;
        for(int i : output){
            sum+=i;
        }
        if(sum ==0){
            return true;
        }
        return false;
    }
    
}