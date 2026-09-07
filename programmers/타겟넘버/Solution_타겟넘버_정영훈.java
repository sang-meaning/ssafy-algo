class Solution_타겟넘버_정영훈 {
    static int count=0;
    public int solution(int[] numbers, int target) {
        int answer = 0;
        int[][] pmNumbers=new int[numbers.length][2];
        for(int i=0; i<numbers.length; i++){
            pmNumbers[i][0]=numbers[i]*(-1);
            pmNumbers[i][1]=numbers[i];
        }
        bt(0,pmNumbers,target,0);
        answer=count;
        
        return answer;
    }
    
    static public void bt(int depth, int[][] pmNumbers, int target, int value){
        if(depth==pmNumbers.length){
            if(value==target) count++;
            return;
        }
        
        for(int i=0; i<2; i++){
            bt(depth+1, pmNumbers, target, value+pmNumbers[depth][i]);
        }
        
        
    }
}