import java.io.*;
import java.util.*;

class Solution{
    public int solution(int n){
        boolean[] map = new boolean[n+1];
        Arrays.fill(map,true);
        map[0] = false;
        map[1] = false;
        for (int i= 2; i*i <= n; i++){
            if (map[i]){
                for(int j = i*2; j<=n; j+=i){
                    map[j] = false;
                }
            }
        }
        int result = 0;
        for (boolean item : map){
            if(item == true){
                result++;
            }
        }
        return result;
    }


    public static void main(String[] args) {
		
	}
}