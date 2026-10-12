package algorithm_solution;

public class PROG_12921 {
	public static int solution(int n) {
        int answer = 0;
        boolean[] check = new boolean[n+1];
        check[0] = check[1] = true;
        for(int i = 2; i * i < n; i++) {
        	if(check[i]) {
        		continue;
        	}
        	for(int j = i + i; j <= n; j+=i) {
        		check[j] = true;
        	}
        }
        for(boolean ch : check) {
        	if(!ch) {
        		answer++;
        	}
        }
        return answer;
    }
	public static void main(String[] args) { 
		System.out.println(solution(1));
	}
}
