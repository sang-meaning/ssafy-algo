import java.util.*;

/*
8명에 대한 순열 만들고 모든 data에 대해 만족하는지 검사
*/

class Solution {
    static int[] arr = {0,1,2,3,4,5,6,7}; 
    static boolean[] vis;
    static int[] now;
    static int count;
    
    public int solution(int n, String[] data) {
        
        count = 0;
        vis = new boolean[8];
        now = new int[8];
        
        dfs(0,data,n);
         
        return count;
    }
    
    static void dfs(int depth, String[] data, int n) {
        if (depth == 8) {
            // now 완성
            
            // 모든 data에 대해 검사
            boolean flag = true;
            
            for (int a=0; a<n; a++) {
                if (ok(data[a]) == false) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) count++;
            return;
        }
        
        for (int i=0; i<8; i++) {
            if (vis[i]) continue;
            
            now[depth] = i;
            vis[i] = true;
            
            dfs(depth+1, data, n);
            
            vis[i] = false;
        }
        
    }
    
    static boolean ok(String s) {
        int l = convert(s.charAt(0));   // arr의 index, 0이면 어피치
        int r = convert(s.charAt(2));
        
        char giho = s.charAt(3);
        int num = s.charAt(4) - '0'; 
        
        // now 배열에 대해 검사
        // l 과 r 사이의 원소의 개수 구하기
        
        int idx1 = -1; // l의 index
        int idx2 = -1;
        
        for (int i=0; i<8; i++) {
            if (now[i] == l) {
                idx1 = i;
            }
            
            if (now[i] == r) {
                idx2 = i;
            }
        }
                
        int diff = Math.abs(idx1-idx2) - 1;  // 사이의 사람 수
        
        if (giho == '=') {
            if (diff == num) return true;
        } else if (giho == '>') {
            if (diff > num) return true;
        } else {
            if (diff < num) return true;
        }
        
        return false;
        
    }
    
    static int convert(char c) {
        if (c=='A')return 0;
        if (c=='C')return 1;
        if (c=='F')return 2;
        if (c=='J')return 3;
        if (c=='M')return 4;
        if (c=='N')return 5;
        if (c=='R')return 6;
        if (c=='T')return 7;
        
        return 0;
    }
    
    
}