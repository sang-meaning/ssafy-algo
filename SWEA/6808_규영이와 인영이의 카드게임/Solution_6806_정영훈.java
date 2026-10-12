import java.util.*;
import java.io.*;

class Solution_6806_정영훈{   
    static Integer[] gueyeong, inyeong;
    static boolean[] visited;
    static int win,lose;
	public static void main(String args[]) throws Exception
	{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            Set<Integer> set=new HashSet<>();
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            win=0;
            lose=0;
            gueyeong=new Integer[9];
            visited=new boolean[9];
            for (int i = 1; i <= 18; i++) {
                set.add(i);
            }
            for (int i = 0; i < 9; i++) {
                int temp=Integer.parseInt(st.nextToken());
                gueyeong[i]=temp;
                set.remove(temp);
            }
            
            inyeong=set.toArray(new Integer[0]);
            bt(0,0,0);
            sb.append("#"+test_case+" "+win+" "+lose+"\n");
        }
        System.out.println(sb.toString());
    }
    static void bt(int depth, int gScore, int iScore){
        if (depth==9) {
            if (gScore>iScore) {
                win++;
            }else if (gScore<iScore) {
                lose++;
            }
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                visited[i]=true;
                int sum=gueyeong[depth]+inyeong[i];
                if (gueyeong[depth]>inyeong[i]) {
                    bt(depth+1, gScore+sum, iScore);

                }else if (gueyeong[depth]<inyeong[i]) {
                    bt(depth+1, gScore, iScore+sum);
                    
                }
                visited[i]=false;
            }
            
        }


    }


    
}
    