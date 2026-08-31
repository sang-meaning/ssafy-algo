import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution_3260_정영훈 {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            Stack<Integer> stack=new Stack<>();
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            String a=st.nextToken();
            String b=st.nextToken();

            int i=a.length()-1;
            int j=b.length()-1;

            int carry=0;
           while (i>=0 || j>=0 || carry >0 ) {
                int aInt=i>=0?a.charAt(i--)-'0':0;
                int bInt=j>=0?b.charAt(j--)-'0':0;

                int sum=aInt+bInt+carry;
                carry=sum/10;
                stack.push(sum%10);

            
           }
           sb.append("#"+test_case+" ");
           while (!stack.isEmpty()) {
                sb.append(stack.pop());
           }
           sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}