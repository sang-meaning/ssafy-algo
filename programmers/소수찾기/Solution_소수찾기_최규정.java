class Solution {
    public int solution(int n) {
        int answer = 0;
        
        //에라토스테네스의 체 문제풀이
        boolean isNotPrime[] = new boolean[n+1]; 
        //소수가 아닌 수에 true를 저장하는 배열, 인덱스 번호와 확인할 숫자를 동일하게 만들었다.
        
        isNotPrime[0] = true; //0은 소수가 아니다.
        isNotPrime[1] = true; //1도 소수가 아니다.
        
        for(int i = 2; i * i <= n; i++){//2부터 시작 root n까지 
            
            if(isNotPrime[i]){ //이미 그 수가 합성수라 표시되었다면 패스
                continue;
            }
            
            for(int j = i * i; j <= n; j = j + i){//i의 배수들을 합성수로 표기
            	//i가 2라면 2*2인 4부터 6,8,10을 확인해 나간다.
            	//i*i부터 시작하는 이유는 i와 i이하의 숫자의 곱은 이미 처리되었기 때문이다.
            	//참고용 코드에서는 i + i부터 시작한다고 되어있다.
            	//i*i를 하면 동일한 작업을 하면서도 중복을 줄일 수 있다.
                isNotPrime[j] = true;
            }
        }
        
        for(int i = 0; i <= n; i++){ //isNotPrime에 남아있는 false값의 수가 소수의 숫자이다.
            if(isNotPrime[i] == false){
                answer++;
            }
        }
        
        return answer;
    }
}