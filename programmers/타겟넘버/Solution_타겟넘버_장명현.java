class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        int N = numbers.length;
        for (int i=0; i<Math.pow(2, N); i++) {
        	int suma = 0;
        	for (int j=0; j<N; j++) {
        		if (((i >> j) & 1) == 1) suma += numbers[j];
        		else suma -= numbers[j];
        	}
        	if (suma == target) answer++;
        }
        
        return answer;
    }
}