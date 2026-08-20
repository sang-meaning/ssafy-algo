class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
        }
        
        int[] arr = new int[sum+1]; // 0 = 소수
        int[] arr2 = new int[sum+1]; // 3개 합친 경우의 수 = 1
        
        for(int i=2;i<=sum;i++){
            for(int j=i*2;j<=sum;j+=i){
                arr[j] = 1;
            }
        }

        int min = 1000000;
        
        for(int i=0;i<nums.length-2;i++){
            for(int j=i+1;j<nums.length-1;j++){
                for(int k=j+1;k<nums.length;k++){
                    int total = nums[i]+nums[j]+nums[k];
                    min = Math.min(min,total);
                    arr2[total]++;
                }
            }
        }

        for(int i=min;i<=sum;i++){
            if(arr[i] == 0 && arr2[i] > 0){
                answer+= arr2[i];
            }
        }
        
        return answer;
    }
}