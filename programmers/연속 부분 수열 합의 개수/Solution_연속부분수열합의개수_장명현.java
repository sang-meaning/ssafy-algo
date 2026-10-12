class Solution {
    public int solution(int[] elements) {
    	int N = elements.length;
    	
    	int[] arr = new int[N+N];
    	for (int i=1; i<N+1; i++) 	arr[i] = elements[i-1];
    	for (int i=N+1; i<N+N; i++) arr[i] = elements[i-(N+1)];
    	
    	int[] prefix = new int[N+N];
    	for (int i=1; i<N+N; i++) {
    		prefix[i] = prefix[i-1] + arr[i];
    		System.out.println(prefix[i]);
    	}
    	
    	boolean[] exist = new boolean[1000000+1];
    	for (int i=1; i<=N; i++) {
    		for (int j=0; j<N; j++) {
    			exist[prefix[i+j] - prefix[i-1]] = true;
    		}
    	}
    	
        int answer = 0;
        for (int i=1; i<=1000000; i++) {
        	if (exist[i]) answer++;
        }
        
        return answer;
    }
}