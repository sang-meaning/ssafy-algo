import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution{
    static int answer;
    static int[] map;
    static int N;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t= 1; t<=T; t++){
            N = Integer.parseInt(br.readLine());
            answer=0;
            map = new int[N];

            dfs(0);

            System.out.println("#"+t+" "+ answer);
        }
    }
    static void dfs(int Row){
        if(N==Row){
            answer++;
            return;
        }
        for(int i = 0 ; i< N; i++){
            map[Row] = i;
            if(possible(Row)){
                dfs(Row+1);
            }
        }
    }
    static boolean possible(int row){
        for(int i= 0; i<row; i++){
            if(map[row] == map [i]){
                return false;
            }
            if(Math.abs(row-i)== Math.abs(map[row]-map[i])){
                return false;
            }
        }
        return true;
    }
}