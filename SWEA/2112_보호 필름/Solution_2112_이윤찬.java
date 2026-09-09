import java.util.*;
import java.io.*;

public class Solution{
    static int T;
    static int D,W,K;
    static int[][] film;
    static int answer;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t= 1; t<=T; t++){
            st = new StringTokenizer(br.readLine());

            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            film = new int[D][W];
            for(int y= 0; y<D; y++){
                st = new StringTokenizer(br.readLine());
                for(int x = 0; x<W; x++){
                    film[y][x] = Integer.parseInt(st.nextToken());
                }
            }
            answer = K;
            
            dfs(0,0);
			System.out.println("#" + t + " " + answer);
        }


    }

    public static void dfs(int depth, int count){
        if(count>=answer){ // 약품을 추가 worst 체크
            return;
        }
        if(check()){ // 조건에 만족했을때 최소값 체크
            answer= Math.min(answer,count);
            return;
        }
        if(depth ==D){ // 모든층을 돌았을 때
            return;
        }
        
        // 돌아오는 걸 저장
        int[] bufferFilm = film[depth].clone();
        
        //약품을 추가 안하고 넘김

        dfs(depth+1,count);

        Arrays.fill(film[depth],0);
        //약품을 추가  A

        dfs(depth+1,count+1);

        Arrays.fill(film[depth],1);
        //약품을 추가 B

        dfs(depth+1, count+1); 


        //원복...
        film[depth] = bufferFilm;
    }

    public static boolean check(){
        for(int x = 0 ; x < W; x++ ){
            int count =1;
            boolean pos = false;
            for(int y = 1; y < D; y++){

                if(film[y][x]== film[y-1][x]){
                    count++;
                }
                else{
                    count=1;
                }
                if(count>=K){
                    pos = true;
                }
            }
            if(K==1){
                pos = true;
            }
            if(!pos){
                return false;
            }
        }
        return true;
    }
}