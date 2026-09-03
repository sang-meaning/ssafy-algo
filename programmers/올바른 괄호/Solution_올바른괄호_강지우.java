class Solution
{
	boolean solution(String s) {
        boolean answer = true;
        
        int count = 0;
        for (int i=0; i<s.length(); i++) {
        	char c = s.charAt(i);
        	
        	if (c == '(') {
        		count++;
        	} else {
        		count--;
        	}
        	
        	if (count < 0) return !answer;
        }
        
        if (count == 0) return answer;
        return !answer;
    }
}