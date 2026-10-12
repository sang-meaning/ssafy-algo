import java.util.*;
import java.io.*;
 
class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
 
    static int T,N,M,result;
    static ArrayList<int[]> houses;
    public static void main(String[] args) throws Exception{
        T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
 
            houses = new ArrayList<>();
            result = Integer.MIN_VALUE;
             
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    int temp = Integer.parseInt(st.nextToken());
                    if(temp == 1){
                        houses.add(new int[] {i,j});
                    }
                }
            }
 
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
 
                    int cx = i;
                    int cy = j;
                     
                    for(int k=0;k<N*2;k++){
 
                        int count = 0;
                         
                        for(int[] t : houses){
 
                            int nx = t[0];
                            int ny = t[1];
 
                            int dist = Math.abs(cx-nx) + Math.abs(cy-ny);
                            if(dist < k){
                                count++;
                            }
                             
                        }
                        int cost = k*k+(k-1)*(k-1);
                        int sum = count*M-cost;
                        if(sum >= 0){
                            result = Math.max(result, count);
                        }
                         
                    }
                     
                     
                }
            }
            sb.append("#"+tc+" "+result+"\n");
             
        }
        System.out.print(sb);
    }
}