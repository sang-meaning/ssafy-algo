
import java.util.*;
import java.io.*;

public class Solution_14510_정영훈
{
    //문제
    //1. 초기 나무키 주어짐
    //2. 하루에 한 나무씩 물을 줄 수 있음
    //3. 홀수날은 1, 짝수날은 2씩 나무가 자람
    //4. 첫째나무보다 다른 나무들이 커지면 안됨
    //End. 모든 나무의 키가 처음에 가장 키가 컸던 나무와 같아지도록하는 최소 날짜수

    //풀이
    //1. maxhigh값 구하기
    //2. max값 - 현재 나무값 의 2로 나누었을때의 몫은 짝수날, 나머지가 있다면 홀수날+?
	public static void main(String args[]) throws Exception
	{
        StringBuilder sb=new StringBuilder();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
           int n=Integer.parseInt(br.readLine());
           int maxTree=0;
           int[] trees=new int[n];
           StringTokenizer st=new StringTokenizer(br.readLine());
           for (int i = 0; i < n; i++) {
                int tree=Integer.parseInt(st.nextToken());
                trees[i]=tree;
                maxTree=Math.max(maxTree, tree);


            
           }
           int odd=0;
           int fair=0;
           for (int i = 0; i < n; i++) {
                int diff=maxTree-trees[i];
                fair+=diff/2;
                odd+=diff%2;
            
           }
           while(fair-odd>=2){
                fair-=1;
                odd+=2;
                

           }
            int sum=Math.min(fair, odd);
            sum*=2;

           if(odd>fair){
            int temp=odd-fair;
            if(odd-fair==1){
                sum+=temp;
            }else if(odd-fair>1){
                sum+=1;
                sum+=(temp-1)*2;
            }
           }else if(fair>odd){
                sum+=(fair-odd)*2;
           }
           
           
           sb.append("#"+test_case+" "+sum+"\n");




        }
        System.out.println(sb.toString());
        
    }
   

    


    


}