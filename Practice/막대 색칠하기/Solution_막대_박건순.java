public class Solution {
	public static void main(String[] args) {
				
		int n = 6;
		int[] yellow = new int[n+1];
		int[] blue = new int[n+1];
		int[] red = new int[n+1];
		
		yellow[1] = 1;
		blue[1] = 1;
		red[1] = 0;
		
		yellow[2] = 2;
		blue[2] = 2;
		red[2] = 1;
		
		for(int i = 3; i<=n;i++) {
			yellow[i] = yellow[i-1] + blue[i-1] + red[i-1];
			blue[i] = yellow[i-1] + blue[i-1] + red[i-1];
			red[i] = yellow[i-2] + blue[i-2] + red[i-2];
		}
		int answer = yellow[n] + blue[n] + red[n];
		System.out.println(answer);
		
	}
}