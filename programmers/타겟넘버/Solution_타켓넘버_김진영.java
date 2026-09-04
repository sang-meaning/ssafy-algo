import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int[] arr;
    static int[] visited;
    static int answer;
    static int tar;
    public int solution(int[] numbers, int target) {
        answer = 0;
        N = numbers.length;
        
        tar = target;
        arr = new int[N];
        visited = new int[N];
        
        for(int i=0;i<N;i++){
            arr[i] = numbers[i];
        }
        
        int[] visited = new int[N];
        dfs(0);
        return answer;
    }
    
    private static void dfs(int depth){
        if(depth == N){
            
            int sum = 0;
            for(int i=0;i<N;i++){
                if(visited[i] == 0){
                    sum += arr[i];
                }else{
                    sum -= arr[i];
                }
            }
            
            if(sum == tar){
                answer++;
            }
            return;
        }
        
        for(int i=0;i<2;i++){
            visited[depth] = i;
            dfs(depth+1);
        }
    }
}
