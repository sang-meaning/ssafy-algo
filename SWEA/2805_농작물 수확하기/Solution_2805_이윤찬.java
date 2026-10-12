import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args)throws  IOException{
        int[][] map ;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=1 ; t<=T; t++){
            int N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for(int y = 0; y<N; y++){
                String s = br.readLine();
                for(int x = 0; x<N; x++){
                    map[y][x] = s.charAt(x)-'0';
                }
            }
            int half = N/2;
            int sum1 = 0;
            for(int y1 = 0 ; y1<=half; y1++) {
                for(int x1 = 0; x1<=y1; x1++){
                    if(x1==0){
                        sum1+=map[y1][half];
                    }
                    else{
                        sum1+=map[y1][half+x1]+map[y1][half-x1];
                    }
                }
            }
            for(int y2 = half+1; y2<N; y2++){ // 3->1처럼  4일떄 0처럼 4- 3
                for(int x2= N-1-y2; x2>=0; x2-- ){
                    if(x2==0){
                        sum1+=map[y2][half];
                    }
                    else{
                        sum1+=map[y2][half+x2]+map[y2][half-x2];
                    }
                }
            }
            System.out.println("#"+t+" "+ sum1);
        }
    }
}
