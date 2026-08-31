import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution_1225_이윤찬 {
    static final int TC= 10;
    static  Queue<Integer> que ;
    static int i;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int t= 1; t<=TC; t++){
            int T = Integer.parseInt(br.readLine());
            i = 1;
            st= new StringTokenizer(br.readLine());
            que = new LinkedList<>();
            for(int i= 0; i<8; i++){
                que.offer(Integer.parseInt(st.nextToken()));
            }
            Make();
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t).append(" ");
            while(!que.isEmpty()){
                sb.append(que.poll()).append(" ");
            }
            System.out.println(sb);
        }
    }
    static void Make(){
        while(true){
            int v1 = que.poll();
            if(i>5){
                i=1;
            }
            if(v1-i<=0){
                que.offer(0);
                break;
            }
            que.offer(v1-i);
            i++;
        }
    }
}
