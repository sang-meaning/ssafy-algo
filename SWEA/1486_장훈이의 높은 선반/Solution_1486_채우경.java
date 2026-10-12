import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int[] arr;
    static int N;
    static int B;
    static int min;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<N;i++){

                arr[i]=Integer.parseInt(st.nextToken());
            }

            /*
            * 선반 높이 B
            * N명의 점원
            * 각 점원의 키 H i
            * 탑은 1명이상이고 점원들의 키가 탑의 높이가 됨
            * 탑의 높이가 B이상이면 스탑,B이상인 것중 최저 탑*/

            min=Integer.MAX_VALUE;
            dfs(0,0);

            System.out.println("#"+t+" "+(min-B));

        }
    }
    static void dfs(int sum,int idx){

        if(sum>=B){
            min=Math.min(min,sum);
            return;
        }


        if (idx==N)
            return;


        dfs(sum+arr[idx],idx+1);
        dfs(sum,idx+1);
    }
}