import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int len = friends.length;
        
        int[][] pre = new int[len][len];
        int x=0 ,y=0;        
        String[] buffer = new String[2];
        for(String s : gifts){
            buffer =s.split(" ");
            for(int i = 0 ; i< len; i++ ){
                y = Arrays.asList(friends).indexOf(buffer[0]);
                x = Arrays.asList(friends).indexOf(buffer[1]);;    
            }
            pre[y][x] +=1;
        }
        int[][] index = new int[len][2];
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0 ; i< len ; i++){
            for (int j=0; j<len; j++){
                if(pre[i][j]== pre[j][i]){
                    map.put(i,j);
                }
                index[j][0] += pre[i][j];
                index[j][1] += pre[j][i];
            }
        }
        
        
        Map<Integer, Integer> map2 = new HashMap<>();
        for(int i = 0 ; i < len; i++ ){
            for(int j =0; j< len; j++){
                if(i==j) continue;
                if(pre[i][j]>pre[j][i]){
                    map2.put(i,map2.getOrDefault(i,0)+1);
                }
                else if(pre[i][j]==pre[j][i]){
                    if(index[i][1]-index[i][0] > index[j][1]-index[j][0]){
                        map2.put(i,map2.getOrDefault(i,0)+1);
                    }
                }
            }
        }
        
        int[] answer = new int[len];
        for(int i = 0 ; i<len; i++){
            answer[i]= map2.getOrDefault(i,0);
        }
        Arrays.sort(answer);

        return answer[len-1];
        
    }
}