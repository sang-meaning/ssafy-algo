import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};
    static int[][] arr;

    public static void main(String[] args) throws IOException {

        for (int t=1;t<=10;t++){


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());

            int startX=0;
            int startY=0;
            int endX=0;
            int endY=0;
            arr = new int[16][16];
            for (int i=0;i<16;i++){
                String s = br.readLine();

                for (int j=0;j<16;j++){
                    arr[i][j]=s.charAt(j)-'0';
                    if(arr[i][j]==2){
                        startX=i;
                        startY=j;
                    }

                    if (arr[i][j]==3){
                        endX=i;
                        endY=j;
                    }


                }
            }

            dfs(startX,startY);

        }
    }

    static void dfs(int x,int y){

        int curx=x;
        int cury=y;

        while (true){

            for (int a=0;a<4;a++){
                int nx= curx+ dx[a];
                int ny = cury +dy[a];

                if (nx<0||ny<0||nx>=16||ny>=16)
                    continue;

                if (arr[nx][ny]!=0)
                    continue;




            }
        }
    }
}
