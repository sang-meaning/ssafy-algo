class Solution {
    static int length;
    static int[] weakList;
    static int[] workerList;
    static int minWorker;
    static boolean[] isWorking;
    static int[] nowWork;
    
    public int solution(int n, int[] weak, int[] dist) {
        length = n;
        weakList = weak;
        workerList = dist;
        
        int startLoc = weak[0];
        
        minWorker = Integer.MAX_VALUE;
        isWorking = new boolean[dist.length];
        nowWork = new int[dist.length];
        
        dfs(0);
        
        if(minWorker == Integer.MAX_VALUE)
            return -1;
        return minWorker;
    }
    
    public void dfs(int depth) {
        if(depth >= minWorker) { // pruning
            return;
        }
        
        //탈출조건
        if(depth > 0 && check(depth)) {
            minWorker = depth;
            return;
        }
        
        for(int i = 0; i < workerList.length; i++) {
            if(!isWorking[i]) {
                isWorking[i] = true;
                nowWork[depth] = workerList[i];
                dfs(depth+1);
                isWorking[i] = false;
            }           
        }
    }
    
    public boolean check(int workerCnt) {
        int weakCnt = weakList.length;
        for(int i = 0; i < weakCnt; i++) {
            int friend = 0;
            int covered = weakList[i] + nowWork[friend];
            
            boolean isDone = true;
            
            for(int j = 0; j < weakCnt; j++) {
                int weakIndex = (i+j) % weakCnt;
                int weakPosition = weakList[weakIndex];
                if(weakIndex < i)
                    weakPosition += length;
                
                if(weakPosition > covered) {
                    friend++;
                    if(friend == workerCnt) {
                        isDone = false;
                        break;
                    }
                    covered = weakPosition + nowWork[friend];
                }
            }
            if(isDone)
                return true;    
        }
        return false;
    }
    
}