import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
        
    for (int i = 1; i <= 10; i ++){
        String L = br.readLine();
    	if (L == null || L.trim().isEmpty()) break; // 없어도 될거 같음
        
        int n = Integer.parseInt(L.trim());
        int answer = 1; // 일단 정상이 기본
        
        for (int j = 0; j < n; j++){
        	StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            char val = st.nextToken().charAt(0);
            
            if (st.hasMoreTokens()){
                if(val >= '0' && val <= '9'){
                	answer = 0; // 계산 불가
                }
            }
            else {
             	if (val == '+' || val == '-' || val == '*' || val == '/'){
                    answer = 0;
                }
            }
        }
        sb.append("#").append(i).append(" ").append(answer).append("\n");
    }
        System.out.print(sb);
    }
}