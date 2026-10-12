import java.util.*;
import java.io.*;

public class Solution_5644_김민우 {
  static BufferedReader br;
  static StringTokenizer st;
  
  static int T, M, A;
  static int[] routeA;
  static int[] routeB;
  static List<AP> apList;
  static int totalC;

  static int[][] delta = {{0,0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

  public static void main(String[] args) throws IOException{
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());

    for(int test_case = 1; test_case <= T; test_case++){
      st = new StringTokenizer(br.readLine());
      M = Integer.parseInt(st.nextToken());
      A = Integer.parseInt(st.nextToken());

      routeA = new int[M];
      routeB = new int[M];
      apList = new ArrayList<>();

      st = new StringTokenizer(br.readLine());
      for(int i = 0; i < M; i++){
        routeA[i] = Integer.parseInt(st.nextToken()); 
      }
      
      st = new StringTokenizer(br.readLine());
      for(int i = 0; i < M; i++){
        routeB[i] = Integer.parseInt(st.nextToken()); 
      }

      
      for(int i = 0; i < A; i++){
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        apList.add(new AP(y, x, C, P));
      }

      totalC = 0;

      int rA, cA;
      rA = cA = 1;
      int rB, cB;
      rB = cB = 10;
      
      charge(rA, cA, rB, cB);

      for(int i = 0; i < M; i++){
        rA += delta[routeA[i]][0];
        cA += delta[routeA[i]][1];
        
        rB += delta[routeB[i]][0];
        cB += delta[routeB[i]][1];

        charge(rA, cA, rB, cB);
      }
      
      System.out.printf("#%d %d\n", test_case, totalC);
    }//test_case 끝

  }//main 끝

  public static void charge(int rA, int cA, int rB, int cB){
    int max = Integer.MIN_VALUE;

    for(int i = 0; i < A; i++){
      int tmp = 0;
      AP batA = apList.get(i);
      int distA = Math.abs(batA.y - rA) + Math.abs(batA.x - cA);
      boolean flagA = false;
      if(distA <= batA.C){
        tmp = batA.P;
        flagA = true;
      }
      for(int j = 0; j < A; j++){
        AP batB = apList.get(j);
        int distB = Math.abs(batB.y - rB) + Math.abs(batB.x - cB);
        
        if(distB <= batB.C){
          /*
          1. 둘 서로 다른 배터리 위에 있을 때
          2. 현재 배터리 위에 B만 올라가 있는 경우
          => 이 두 경우에만 A의 P값 + B의 P값 연산 실행
          <=> B가 배터리 위에 있는데도 P값을 더하지 않는 경우는, 둘이 같은 배터리 위에 올라가 있을 때만
              이때는 이미 A의 P값이 A+B를 내포하고 있기 때문
          */
          if(i != j || (i == j && tmp == 0)){
            tmp += batB.P;
          }
        }

        max = (tmp > max)?tmp:max;
        tmp = (flagA)?batA.P:0;
      }
    }

    totalC += max;
  }//charge 끝

}

class AP{
  int y ,x , C, P;
    
  AP(int y, int x, int C, int P){
    this.y = y;
    this.x = x;
    this.C = C;
    this.P = P;
  }
}