import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int win=0;
    static int lose=0;
    static int[] a= new int[9];
    static int[] b= new int[9];
    static boolean[] visited = new boolean[9];

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
/*
* 1~18 카드 9장씩 나눠가짐 9라운드
* 1라운드에 1장씩 내서 점수 비교
* 높은 카드낸사람은 두 카드의 합만큼 점수 획득
* 낮은 카드낸사람은 점수 x
* 9라운드 후 총점 이 더 높은사람 승리
* 같으면 무승부
*규영이 카드는 미리 제공
* 이때 규영친구가 이길 경우의 수와 지는 경우의 수
* */
            win = 0;
            lose = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());

            boolean[] cardCheck = new boolean[19];
            for (int i=0;i<9;i++){
                a[i]=Integer.parseInt(st.nextToken());
                cardCheck[a[i]]=true;
            }

            int idx=0;
            for (int i=1;i<=18;i++){
                if (!cardCheck[i])
                    b[idx++]=i;
            }

            dfs(0,0,0);

            System.out.println("#"+t+" "+win+" "+lose);
        }

    }
    static void dfs(int ascore,int bscore,int depth){

        if(depth==9){
            if (ascore>bscore)
                win++;
            else if (ascore<bscore)
                lose++;
            return;
        }

        for (int i=0;i<9;i++){

            if (!visited[i]){
                visited[i]=true;

                int gc=a[depth];
                int ic=b[i];

                if (gc>ic){
                    dfs(ascore+gc+ic,bscore,depth+1);
                }else if(gc<ic)
                    dfs(ascore,bscore+gc+ic,depth+1);
                else
                    dfs(ascore,bscore,depth+1);
                visited[i] = false; 
            }

        }
    }
}
