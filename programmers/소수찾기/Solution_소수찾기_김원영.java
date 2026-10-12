class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] array = new int[n+1];
        //0과1은 음수 처리
        array[0] = -1;
        array[1] = -1;
        //0으로 초기화된 배열을 소수 아닌 수만 1로 처리해서 남은 0들을 소수로 생각
        for(int i=2; i * i <= n; i++){
            //기존에 체크된 소수 아닌 수 판별해서 패스
            if (array[i]==1){
                continue;
            }
            for(int j = 2*i; j <= n; j+=i){
                array[j] = 1;
            }
        }
        //원소값이 0인 인덱스가 있으면 answer +1해줌
        for(int i = 0; i<= n; i++){
            if (array[i] ==0){
                answer+=1;
            }
        }
        return answer;
    }
}