import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for(int test_case = 1; test_case <= 10; test_case++)
        {
        	int N = Integer.parseInt(br.readLine());
            
        	int result = 1;
            
            for (int i=0; i<N; i++) {
            	
            	StringTokenizer st = new StringTokenizer(br.readLine());
            	
            	int tokenCount = st.countTokens();
            	
            	st.nextToken();
            	String value = st.nextToken();
            	
            	boolean isOperator =
            			value.equals("+") ||
            			value.equals("-") ||
            			value.equals("*") ||
            			value.equals("/");
            	
            	if (isOperator) {
            		if (tokenCount != 4) {
            			result = 0;
            		}
            	} else {
            		if (tokenCount != 2) {
            			result = 0;
            		}
            	}
            	
            }
            
            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        
        System.out.print(sb);
    }
}