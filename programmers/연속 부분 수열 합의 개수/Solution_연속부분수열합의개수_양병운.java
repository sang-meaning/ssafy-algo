import java.util.*;
class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int[] circle = new int[elements.length * 2];
        for(int i=0; i<elements.length; i++){
            circle[i] = elements[i];
            circle[i+elements.length] = elements[i];
        }
        
        for(int group=1; group<=elements.length; group++){
            for(int start = 0; start <= elements.length-1; start++){
                set.add(sum(start, group, circle));
            }
        }
        return set.size();
    }
    public int sum(int start, int group, int[] circle){
        int temp = 0;
        for(int n=start; n<start+group; n++){
            temp += circle[n];
        }
        return temp;
    }
}