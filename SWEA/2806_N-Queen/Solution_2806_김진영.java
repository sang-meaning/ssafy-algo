import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".

class Solution
{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T,N;
    static int result;
    static int[] dist1;
    static int[] dist2;
    static int[] dist3;
    
    public static void main(String[] args) throws Exception{

        T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++){

            N = Integer.parseInt(br.readLine());

            dist1 = new int[N*2+1];
            dist2 = new int[N*2+1];
            dist3 = new int[N+1];
            result = 0;
            nqueen(0);
            
            sb.append("#"+tc+" "+result+"\n");
        }
        
        System.out.print(sb);
    }

    private static void nqueen(int depth){
        if(depth == N){
            result++;
            return;
        }

        for(int i=0;i<N;i++){

            if(dist1[depth+i] == 1){
                continue;
            }
            if(dist2[depth-i+N] == 1){
                continue;
            }
            if(dist3[i] == 1){
                continue;
            }

            dist1[depth+i] = 1;
            dist2[depth-i+N] = 1;
            dist3[i] = 1;

            nqueen(depth+1);

            dist1[depth+i] = 0;
            dist2[depth-i+N] = 0;
            dist3[i] = 0;
        }
    }

    
}
