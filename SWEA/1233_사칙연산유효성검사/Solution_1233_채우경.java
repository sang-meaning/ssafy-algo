import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        for (int tc = 1; tc <= 10; tc++) {

            int N = Integer.parseInt(br.readLine());//횟수 지정
            boolean isvalid=true;//0 1 
            
            for(int i=0;i<N;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                st.nextToken();
                String value = st.nextToken();
                int cnt= st.countTokens();
                
                boolean operator=value.equals("+")||value.equals("-")||value.equals("*")||value.equals("/");
                
                if(operator) {
                	if(cnt!=2)
                		isvalid=false;
                }else {
                	if(cnt!=0)
                		isvalid=false;
                }          
            }
               System.out.println("#"+tc+" "+(isvalid?1:0));

        }
    }
}