import java.util.*;
import java.io.*;

public class Solution_1233_김민우 {

  static BufferedReader br;
  static StringTokenizer st;

  static char[] tree;
  static int N;
  public static void main(String[] args) throws Exception{
    
    br = new BufferedReader(new InputStreamReader(System.in));
    for(int t = 0; t < 10; t++){

      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      
      tree = new char[N+1];
      int leafNum = (N+1)/2;

      for(int i = 1; i <= N; i++){
        st = new StringTokenizer(br.readLine());
        if(i < leafNum){
          st.nextToken();
          tree[i] = st.nextToken().charAt(0);
          continue;
        }
        st.nextToken();
        tree[i] = st.nextToken().charAt(0);
      }

      if(N%2 == 0){
        System.out.printf("#%d %d\n",t+1, 0);
        continue;
      }

      int answer = 1;

      for(int i = N; i >= leafNum; i -= 2){
        char leaf1 = tree[i];
        char leaf2 = tree[i-1];
        
        if(i == leafNum){
          if(!isInt(leaf1) || isInt(leaf2)){
            answer = 0;
            break;
          }
          if(!isInt(leaf2)){
            int ch1 = (i-1)*2;
            int ch2 = (i-1)*2+1;
            if(!isInt(tree[ch1]) || !isInt(tree[ch2])){
              answer = 0;
              break;
            }
          }
          continue;
        }

        if(!isInt(leaf1) || !isInt(leaf2)){
          answer = 0;
          break;
        }
        else{
          if(isInt(tree[i/2])){
            answer = 0;
            break;
          }
        }
      }

      System.out.printf("#%d %d\n",t+1, answer);
    }//test_case 끝
  }//main 끝

  public static boolean isInt(int node){
    return node >= '0' && node <= '9';
  }
}
