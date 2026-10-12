import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
  static int[] cards;
  static boolean[] isSelected;
  static int T, ans = 0;
  public static void main(String[] args) throws IOException{
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());
    for (int tc =1; tc<=T; tc++){
      cards = new int[9];
      isSelected = new boolean[19];
      st = new StringTokenizer(br.readLine());
      for (int i =0; i<9; i++){
        int card = Integer.parseInt(st.nextToken());
        isSelected[card] = true;
        cards[i] = card;
      }

      predict(0, 0,0);
      sb.append("#").append(tc).append(" ").append(2*3*4*5*6*7*8*9-ans).append(" ").append(ans).append("\n");
      ans = 0;
    }
    System.out.print(sb);
  }
  static void predict(int sum1, int sum2, int cnt){
    if (sum2 >= 86){
      return;
    } else if (sum1 >= 86){
      int temp = 1;
      for (int i = 1; i<=9-cnt; i++){
        temp *= i;
      }
      ans += temp;
      return;
    }
    if (cnt == 9){
      if (sum1 > sum2){
        ans++;
      }
      return;
    }

    int pick = 1;
    while(pick < isSelected.length){ // 길이 미만일때, 
      if (isSelected[pick] == true){ // 고른 카드가 이미 선택됐다면
        pick++;
        continue;
      }
      isSelected[pick] = true;
      if (cards[cnt] < pick){ // 이긴 경우
        predict(sum1 + pick + cards[cnt], sum2, cnt+1);
      }else{ // 진 경우
        predict(sum1, sum2 + pick + cards[cnt], cnt +1);
      }
      isSelected[pick] = false;
      pick++;
    }
  }
}