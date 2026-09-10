import java.io.*;
import java.util.*;

class Solution {
    static int M,N;
    static int[] arr2;
    static int[] used;
    static int[] arr;
    
    static int start;
    static int start_index;
    static int answer;
    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;

        N = weak.length;
        M = dist.length;
        arr = new int[N*2];
        arr2 = new int[M];
        used = new int[M];
        
        for(int i=0;i<N;i++){
            arr[i] = weak[i];
            arr[i+N] = weak[i]+n;
        }
        for(int i=0;i<N;i++){
            start = weak[i];
            start_index = i;

            dfs(0,dist);    
        }
        
        if(answer == Integer.MAX_VALUE){
            return -1;
        }
        return answer;
    }
    
    private static void dfs(int depth,int[] dist){
        
        if(depth == M){
            check();
            return;
        }
        
        for(int i=0;i<M;i++){
            
            if(used[i] == 1){
                continue;
            }
            used[i] = 1;
            arr2[depth] = dist[i];
            dfs(depth+1,dist);
            used[i] = 0;
        }
    }
    
    private static void check(){
        int index = start_index;
        int end = start_index + N;
        for(int i=0;i<M;i++){
            
            int next = arr[index] + arr2[i];
            
            
            while (index < end &&
                arr[index] <= next) {

                index++;
            }
            if (index >= end) {
                answer = Math.min(
                        answer,
                        i + 1
                );

                return;
            }
        }
        
    }
}