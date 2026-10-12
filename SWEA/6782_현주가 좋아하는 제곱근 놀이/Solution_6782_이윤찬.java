import java.io.*;
import java.util.*;
public class Solution_6782_이윤찬{

    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc= 1; tc<=T; tc++){
            long N = Long.parseLong(br.readLine());
            long answer= 0;

            while(N>2){
                long root  = (long) Math.sqrt(N);

                if(Math.pow(root,2) ==N){
                    N= root;
                    answer++;
                }
                else{
                    long nextRoot =root+1;
                    long nextSquare = Math.pow(nextRoot,2);

                    answer+=nextSquare-N;

                    answer++;

                    N = nextRoot;
                }

            }
            System.out.println("#" + tc + " " + answer);

        }
    }

}