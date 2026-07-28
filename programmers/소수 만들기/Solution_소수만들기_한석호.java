class makesosu {
    public int solution(int[] nums) {
        int answer = 0;
        
        int max = 3000;
        
        boolean [] check_sosu = new boolean[max+1];
        
        for (int i=2; i<=max; i++)
        {
            check_sosu[i] = true;
        }
        
        for (int i=2; i*i<=max; i++)
        {
            if(check_sosu[i])
            {
                for(int j=i*i; j<=max; j+=i)
                {
                    check_sosu[j] = false;
                }
                
            }
        }
        
        int n = nums.length;
        
        for (int i=0; i<n; i++)
        {
            
            for (int j = i + 1; j < n; j++)
            {
                for (int z = j+1; z<n; z++)
                {
                    int sum = nums[i] + nums[j] + nums[z];
                    if(check_sosu[sum])
                    {
                        answer += 1;
                    }

                }
            }
            
        }

        return answer;
    }
}