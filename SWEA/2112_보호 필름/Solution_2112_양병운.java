import java.util.*;
import java.io.*;
 
class Solution {
    static int D, W, K, min;
    static int[][] matrix;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            min = Integer.MAX_VALUE;
            matrix = new int[D][W];
            for(int i = 0; i < D; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++){
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            if(check(matrix)) {
                System.out.println("#" + test_case + " " + 0);
                continue;
            }
            dfs();
            System.out.println("#" + test_case + " " + min);
        }
    }
 
    public static boolean check(int[][] m){
        for(int i = 0; i < W; i++){
            int max = 0;
            int before = m[0][i];
            int temp = 1;
            for(int j = 1; j < D; j++){
                if(m[j][i] == before) temp++;
                else {
                    before = m[j][i];
                    max = Math.max(max, temp);
                    temp = 1;
                }
            }
            max = Math.max(max, temp);
            if(max < K) return false;
        }
        return true;
    }
    public static int[][] copyArray(){
        int[][] newMatrix = new int[D][W];
        for(int i = 0; i < D; i++) {
            for(int j = 0; j < W; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }
 
    public static void dfs(){
        int high = 1 << D;
        for(int mask = 1; mask < high; mask++){
            int size = Integer.bitCount(mask);
            if(size >= min) continue;
            int[][] copy = copyArray();
            int drugHigh = 1 << size;
 
            for(int drugMask = 0; drugMask < drugHigh; drugMask++){
                int idx = 0;
                for(int p = 0; p < D; p++){
                    if((mask & (1 << p)) != 0){
                        if((drugMask & (1 << idx)) != 0) Arrays.fill(copy[p], 1);
                        else Arrays.fill(copy[p], 0);
                        idx++;
                    }
                }
                if(check(copy)){
                    min = Math.min(min, size);
                    break;
                }
            }
        }
    }
}