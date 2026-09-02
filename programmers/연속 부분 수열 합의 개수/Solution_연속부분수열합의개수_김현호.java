import java.util.*;
class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        int size = elements.length;
        Set<Integer> setsum = new HashSet<>();
        for(int len = 1; len <= size; len++){
            int sum = 0;
            for(int i = 0; i < len; i++){
                sum += elements[i];
            }
            setsum.add(sum);
            for(int n = 1; n < size; n++){
                int out = n - 1;
                int in = (len + n - 1) % size;
                
                sum -= elements[out];
                sum += elements[in];
                setsum.add(sum);
            }
        }
        return setsum.size();
    }
}