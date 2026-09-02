import java.util.Scanner;
import java.io.FileInputStream;

public class Solution{
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		
		for(int T = 1; T <= 10; T++)
		{
			int N = Integer.parseInt(sc.nextLine());
			int answer = 1;
			
			for(int i = 0; i < N; i++) {
				String line = sc.nextLine();
			    String[] data = line.split(" ");

			    String value = data[1];

				if(data.length > 2) {
					 if (!(value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/"))) {
						 answer = 0;
					 }
				}
				else {
					if (value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/")) {
						 answer = 0;
					 }
				}
			}
			System.out.println("#" + T + " " + answer);
			
		}
	
	}
}