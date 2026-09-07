import java.util.*;

public class Solution {

    static int[] gyuyoung;
    static int[] inyoung;
    static int win,lose;

    static boolean[] visited = new boolean[9];
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int test_case = sc.nextInt();

        for(int t=1; t<=test_case; t++){

            gyuyoung = new int[9];
            inyoung = new int[9];

            win = 0;
            lose = 0;

            for(int i=0; i<9; i++){
                gyuyoung[i] = sc.nextInt();
            }


            int cnt = 0;
            for(int i=1; i<=18; i++){
                boolean bl = false;

                for(int j=0; j<9; j++){
                    if(gyuyoung[j] == i) {
                        bl = true;
                        break;
                    }
                }

                if(bl) continue;

                inyoung[cnt] = i;
                cnt++;
            }


            dfs(0, 0,0);

            System.out.println("#"+t+" " +win+" "+lose);

        }


    }

    public static void dfs(int n, int winScore, int loseScore){

        if(n==9){
            if(winScore>loseScore) win++;
            else if(winScore<loseScore) lose++;
            return;
        }

        for(int i=0; i<9; i++){

            if(!visited[i]){
                visited[i] = true;

                int gyuCard = gyuyoung[n];
                int inCard = inyoung[i];

                int sum = gyuCard + inCard;

                if(gyuCard>inCard){
                    dfs(n+1, winScore+sum, loseScore);
                }else if(inCard>gyuCard){
                    dfs(n+1, winScore, loseScore+sum);
                }

                visited[i] = false;
            }
        }
    }
}

/*
1~18 숫자가 적힌 카드를 9장씩 나눠가짐
9라운드 한장씩 비교해서 점수계산
높은수의 카드를 가진사람이 두 카드의 합만큼 점수 획득
두 사람이 총점이 같으면 무승부
이기는 경우와 지는경우가 몇가지인지 구해라

 */