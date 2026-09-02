import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
class Solution1225{
  public static void main(String[] args){
    Scanner sc= new Scanner(System.in);
    StringBuilder sb = new StringBuilder();
    int T = 10;
    for (int tc = 1; tc<=T; tc++){
      int t = sc.nextInt(); // tc 제거 겸 표시
      sb.append("#").append(t).append(" ");
      Deque<Integer> que1 = new ArrayDeque<>();
      for (int i =0; i<8;i++){ // 8개 숫자 삽입
        que1.offer(sc.nextInt());
      }

      int exit = 1;
      while (exit == 1){ // 0일때까지 반복
        for (int i =1; i<=5;i++){ // 1~5를 빼는걸 반복문으로 표현
          int k = que1.poll();
          if (k>i){
            que1.offer(k-i);
          }else{
            que1.offer(0);
            exit = 0;
            break;
          }
        }

      }
      while(true){ //출력용 BoilerPlate?
        sb.append(que1.poll());
        if (!que1.isEmpty()){
          sb.append(" ");
        }else{
          sb.append("\n");
          break;
        }
      }
    }
    System.out.print(sb);
    sc.close();
  }
}