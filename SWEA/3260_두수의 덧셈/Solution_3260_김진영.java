import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T;
	
	static int[] result; 
	public static void main(String[] args) throws Exception{
		
		T = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=T;test_case++) {
			
			String s = br.readLine();
			
			String arr[] = s.split(" ");
			
			String num1 = arr[0];
			String num2 = arr[1];
			
			int num1_len = num1.length()-1;
			int num2_len = num2.length()-1;
			
			result = new int[101];
			
			if(num2_len > num1_len) {
				int temp = num2_len;
				num2_len = num1_len;
				num1_len = temp;
				
				String ttemp = num2;
				num2 = num1;
				num1 = ttemp;
			}
			
			int add_flag = 0;
			
			while(num1_len >= 0) {

			    result[num1_len] += num1.charAt(num1_len) - '0';

			    if(num2_len >= 0) {
			        result[num1_len] += num2.charAt(num2_len) - '0';
			    }

			    if(result[num1_len] >= 10) {

			        result[num1_len] -= 10;

			        if(num1_len == 0) {

			            for(int i = num1.length(); i > 0; i--) {
			                result[i] = result[i - 1];
			            }

			            result[0] = 1;
			            add_flag = 1;

			        } else {

			            result[num1_len - 1] += 1;
			        }
			    }

			    num1_len--;
			    num2_len--;
			}
			
			sb.append("#")
			.append(test_case)
			.append(" ");
			
			for(int i=0;i<num1.length();i++) {
				sb.append(result[i]);
				//sb.append(" ");
			}
			if(add_flag == 1) {
				//sb.append("asd");
				sb.append(result[num1.length()]);
			}
			sb.append("\n");
		
			
		}
		System.out.print(sb);
	}
	
	
}