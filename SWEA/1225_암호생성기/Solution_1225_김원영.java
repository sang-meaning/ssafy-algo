import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = 10;
		
		for(int i = 0; i < T ; i++) {
			int tc = sc.nextInt();
			Queue<Integer> queue = new ArrayDeque<>();
			
			for(int j=0; j<8;j++) {
				queue.offer(sc.nextInt());
			}
			while(true) {
				boolean end = false;
				for(int k=1; k<=5; k++) {
					if((queue.peek()-k) <= 0) {
						queue.offer(0);
						queue.poll();
						end = true;
						break;
					}else {
						queue.offer(queue.peek()-k);
						queue.poll();
					}
				}
				if(end) {
					break;
				}
			}
			System.out.print("#"+tc+" ");
			for(int nums : queue) {
				System.out.print(nums+" ");
			}
			System.out.println();
		}
		
	}
}
