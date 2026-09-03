class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int sum = 0;
        for(int num : number){
            sum += num;
        }
        for(int i = 0; i < discount.length - sum + 1;i++){
            int set = 0;
            for(int j = 0; j < number.length; j++){
                int count = 0;
                for(int k = i; k < i + sum; k++){
                    if(want[j].equals(discount[k])){
                        count++;
                    }
                }
                if(number[j] == count){
                    set++;
                }
            }
            if(set == number.length){
                answer++;
            }
        }
        return answer;
    }
}