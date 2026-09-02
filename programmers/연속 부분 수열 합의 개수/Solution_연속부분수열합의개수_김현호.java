import java.util.*;
class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        int size = elements.length;
        int[] double_elem = new int[size*2];
        Set<Integer> setsum = new HashSet<>();
        for(int i = 0; i < size*2; i++){
            double_elem[i] = elements[i%size];
        }
        System.out.println(double_elem.length);
        for(int i = 1; i <= size; i++){
            for(int j = 0; j < size; j++){
                int sum = 0;
                for(int k = j; k < j + i; k++){
                    sum += double_elem[k];
                }
                setsum.add(sum);
            }
        }
        return setsum.size();
    }
}