import java.util.*;
import java.io.*;

public class Solution_2112_정영훈 {


    static int[][] map;
    static int[][] fillMap;
    final static int FILL_A=0, FILL_B=1, FILL_NONE=2;
    final static int A=0, B=1;
    static int minFillCount;
    static int D,W;
    static int[] aArray, bArray;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            D=Integer.parseInt(st.nextToken());
            W=Integer.parseInt(st.nextToken());
            int k=Integer.parseInt(st.nextToken());
            map=new int[D][W];
            fillMap=new int[D][W];
            aArray=new int[W];
            bArray=new int[W];
            Arrays.fill(aArray, A);
            Arrays.fill(bArray, B);
            minFillCount=Integer.MAX_VALUE;
            for (int i = 0; i < D; i++) {
                st=new StringTokenizer(br.readLine()," ");
                for (int j = 0; j < W; j++) {
                    map[i][j]=Integer.parseInt(st.nextToken());
                    
                }
                
            }
            if(k==1){
                sb.append("#"+test_case+" "+0+"\n");
                continue;
            }
            for (int i = 0; i < D; i++) {
                fillMap[i]=map[i].clone();
                
            }
            dfs(0, D, k, 0);
            sb.append("#"+test_case+" "+minFillCount+"\n");
        }
        System.out.println(sb.toString());
    }

    public static boolean check(int k){
        for (int col = 0; col < W; col++) {
            int maxCount=0;
            int count=0;
            int value=fillMap[0][col];
            for (int row = 0; row < D; row++) {
                if(count>k){
                    maxCount=count;
                    break;
                }
                if(fillMap[row][col]==value){
                    count++;
                    maxCount=Math.max(maxCount, count);

                }
                    
                else{
                    value=fillMap[row][col];
                    maxCount=Math.max(maxCount, count);
                    count=1;
                }
            }
            if(maxCount<k)
                return false;
            
        }
        return true;
        

    }
    // 3*13*
    public static void dfs(int depth, int D, int k, int count){
       
            
        if(depth==D){
            if(check(k)){
                minFillCount=Math.min(minFillCount, count);
            }
            return;
        }
        int[] fillNone=map[depth].clone();
        for (int i = 2; i >= 0; i--) {
            fill(depth, i, fillNone);
            dfs(depth+1, D, k, count+(i==FILL_NONE?0:1));

            
        }
    }

    public static void fill(int depth, int i, int[] fillNone){
        fillMap[depth]=i==FILL_A?aArray:i==FILL_B?bArray:fillNone;
    }

}
