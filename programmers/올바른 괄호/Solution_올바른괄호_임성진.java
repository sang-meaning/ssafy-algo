import java.util.*;
import java.io.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Queue<Character> q = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
        	answer = false;
        	
        	if(s.charAt(i) == ')' && !q.contains('(')) break;
        	if(s.charAt(i) == '(') q.add(s.charAt(i));
        	else q.poll();

        	answer = true;
		}
        
        if(!q.isEmpty()) answer = false;
        
        return answer;
    }
}