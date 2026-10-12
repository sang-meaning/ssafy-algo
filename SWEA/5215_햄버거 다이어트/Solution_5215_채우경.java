import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int arr[][];
    static int kal;
    static int flavor;
    static int L;
    static int max;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {


            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            arr= new int[N][2];
            for (int i=0;i<N;i++){
                st=new StringTokenizer(br.readLine());

                arr[i][0]=Integer.parseInt(st.nextToken());//맛
                arr[i][1]=Integer.parseInt(st.nextToken());//칼로리

            }
            max=0;

            flavor=0;
            kal=0;
            int idx=0;
            dfs(arr,idx,flavor,kal);


            System.out.println("#"+t+" "+max);
        }

    }
    static void dfs(int[][] arr,int idx,int flavor,int kal){//L:칼로리 제한

        if(kal>L)
            return;

        if (idx==arr.length){
            max=Math.max(max,flavor);
            return;
        }



        dfs(arr,idx+1,flavor+arr[idx][0],kal+arr[idx][1]);
        dfs(arr,idx+1,flavor,kal);

    }
}
