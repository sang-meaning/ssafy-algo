class findsosu {
    public int solution(int n) {
        int answer = 0;
        
        // 소수 기본 값: 2, 3, 5, 7, 11 
        boolean [] check_sosu = new boolean[n + 1];
        
        for (int i=2; i<(n+1); i++)
        {
            check_sosu[i] = true;
        }
        
        
        for (int i = 2; i * i <= n; i++)
        {
            if (check_sosu[i])
            {
                for(int j=i*i; j<=n; j+=i)
                {
                    check_sosu[j] = false;
                }
            }
            
        }
        
        for(int i=2;i<=n;i++)
        {
            if(check_sosu[i])
            {
                answer += 1;
            }
        }
        
        return answer;
    }
}