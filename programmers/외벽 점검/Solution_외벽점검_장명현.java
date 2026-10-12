class Solution {
	public static int D, W, answer;
	public static int[] weak, dist, parr;
	public int solution(int N, int[] weakness, int[] distance) {
		
		W = weakness.length;
		weak = new int[2*W];
		for (int i=0; i<W; i++) weak[i] = weakness[i];
		for (int i=W; i<2*W; i++) weak[i] = weakness[i-W] + N;
        
		D = distance.length;
		dist = new int[D];
		for (int i=0; i<D; i++) dist[i] = distance[i];
		
		for (int x:dist) {
			if (x >= N) {
				return 1;
			}
		}
		
		parr = new int[D];
		for (int i=0; i<D; i++) parr[i] = i;
		
		answer = -1;
		perm(0);
		
		return answer;
	}
	
	public void perm(int size) {
		if (size == D) {
			for (int i=0; i<W; i++) {
				int pi = 0;
				int cover = weak[i] + dist[parr[pi]];
				for (int j=i; j<i+W; j++) {
					if (weak[j] > cover) {
						pi++;
						if (pi == D) break;
						
						cover = weak[j] + dist[parr[pi]];
					}
					
					if (j == i+W-1) {
                        if (answer == -1) answer = pi + 1;
                        else answer = Math.min(answer, pi + 1);
                    }
				}
			}
			
			return;
		}
		
		for (int i=size; i<D; i++) {
			int temp = parr[size];
			parr[size] = parr[i];
			parr[i] = temp;
			
			perm(size+1);
			
			temp = parr[size];
			parr[size] = parr[i];
			parr[i] = temp;
		}
	}
}