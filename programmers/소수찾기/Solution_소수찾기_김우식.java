
class Solution {
	public int solution(int n) {
        int answer = 0;
        // 에라토스테네의 체 NloglogN
        boolean[] isPrime = new boolean[n+1];
        isPrime[0] = isPrime[1] = true;
        for(int i = 2; i * i <= n; i++){
            if(isPrime[i] == true){
                continue;
            }
            // i*i를 하는건 i보다 작은 애가 이미 처리했으니까
            for(int j = i * i; j <= n; j += i){
                isPrime[j] = true;
            }
        }
        for(boolean k : isPrime){
            if(k!=true) answer++;
        }
        
         // #1. 기본 빵 O(n^2) 시간초과
//         for (int i = 2; i <= n; i++) {
//             boolean isPrime = true;  
//             for (int j = 2; j <= i - 1; j++) {
//                 if (i % j == 0) {    
//                     isPrime = false; 
//                     break;           
//                     }
//                 }
//             if (isPrime) {
//             answer++;            
//             }
//         }
        
         // #2. 절반으로 해보기 이것도 시간초과 남 N^2/2
        //  for (int i = 2; i <= n; i++) {
        //     boolean isPrime = true;  
        //     for (int j = 2; j <= i/2; j++) {
        //         if (i % j == 0) {    
        //             isPrime = false; 
        //             break;           
        //             }
        //         }
        //     if (isPrime) {
        //     answer++;            
        //     }
        // }
        
         // #3. 루트N은 됨 시간초과 안남 N루트N
         // for (int i = 2; i <= n; i++) {
         //    boolean isPrime = true;  
         //    for (int j = 2; j*j <= i; j++) {
         //        if (i % j == 0) {    
         //            isPrime = false; 
         //            break;           
         //            }
         //        }
         //    if (isPrime) {
         //    answer++;            
         //    }
        // }
        
        return answer;
    }
}
