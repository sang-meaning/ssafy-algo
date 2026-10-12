class Solution {
    boolean solution(String s) {
        boolean answer = true;

        int size = s.length();
        int cnt=0;

        for(int i=0;i<size;i++){
            if(s.charAt(i)=='(')
                cnt++;
            else
                cnt--;
            
            if(cnt<0)
                return false;
        }
        
        if(cnt!=0)
            answer=false;
       
        return answer;
    }
}