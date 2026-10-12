class Solution {
    static char[] friends = {'A', 'C','F','J','M','N','R','T'};
    static boolean[] isPlaced;
    static char[] friendList;
    static int answer;
    static char[] friendA;
    static char[] friendB;
    static char[] condition;
    static int[] conditionCnt;
    
    public int solution(int n, String[] data) {
        friendA = new char[n];
        friendB = new char[n];
        condition = new char[n];
        conditionCnt = new int[n];
        
        for(int i = 0;i < n; i++) {
            friendA[i] = data[i].charAt(0);
            friendB[i] = data[i].charAt(2);
            condition[i] = data[i].charAt(3);
            conditionCnt[i] = data[i].charAt(4) - '0';            
        }
        
        answer = 0;
        friendList = new char[8];
        isPlaced = new boolean[8];
        dfs(0, n);    
        return answer;
    }
    
    public void dfs(int depth, int n) {
        if(depth == 8) {
            if(check(n)) 
                answer++;
            return;
        }
        
        for(int i = 0; i < 8; i++) {
            if(!isPlaced[i]){
                isPlaced[i] = true;
                friendList[depth] = friends[i];
                dfs(depth+1, n);
                isPlaced[i] = false;
            }
        }
    }
    
    public boolean check(int n) {
        for(int i = 0; i < n; i++) {
            int friendAIdx = 0;
            int friendBIdx = 0;
            for(int j =0; j < 8; j++) {
                if(friendList[j] == friendA[i])
                    friendAIdx = j;
                if(friendList[j] == friendB[i])
                    friendBIdx = j;
            }
            int diff = Math.abs(friendAIdx - friendBIdx) - 1;
            
            if(condition[i] == '=') {
                if(conditionCnt[i] != diff)
                    return false;
            } else if (condition[i] == '>') {
                if(conditionCnt[i] >= diff) 
                    return false;
            } else {
                if(conditionCnt[i] <= diff)
                    return false;
            }
        }
        return true;
    }
}

//사람배치하기
// 햄스터같이?
// dfs. 전부 넣어보고 -> 결과가 맞는지 체크하기 