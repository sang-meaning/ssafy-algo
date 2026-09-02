class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        // 큰쪽에서 빼서 -> 적은쪽으로 넣는다?
        
        // 3 2 7 2 4 6 5 1
        // 1 2 1 2 1 10 1 2 -> 10
        // 2pointer -> 끝까지 없으면 -1?
        int totalLen = queue1.length + queue2.length;
        int[] realList = new int[totalLen];
        long totalSum = 0;
        long partSum = 0; // 한쪽 크기
        
        for(int i = 0; i < totalLen / 2; i++) {
            realList[i] = queue1[i];
            realList[i+totalLen/2] = queue2[i];
            partSum += queue1[i];
            totalSum += queue1[i];
            totalSum += queue2[i];
        }
        
        if(totalSum % 2 == 1)
            return -1;
        
        long targetSum = totalSum / 2;
        int pointerA = 0;
        int pointerB = queue1.length;
        
        long cnt = 0;
        long finalCnt = (long) 2 * totalLen;
        
        while(cnt < finalCnt) { 
            // 몇번이나 해야하는가? 모든 경우의수? 전체길이가 n이라 하면, n^2 -1가 모든 경우의 수?
            // 시간초과 ㅋㅋ
            // 모든 경우의 수는 저게 아니라. (n+1)개의 분할선중 서로다른 2개를 고르는 경우의 수. n(n+1)/2 시간복잡도 똑같은디
            // 시간초과 ㅋㅋ
            // 어차피 슬라이딩 윈도우니까 n^2가 나올 껀수가 없지 않나
            // A포인터 B포인터 n번까지 이동하는거니까 2n이면 되나
            // 홀리몰리~
            
            if(partSum == targetSum) {
                return (int) cnt;
            }
            
            if(partSum > targetSum) {
                partSum -= realList[pointerA];
                pointerA++;
                if(pointerA >= totalLen)
                    pointerA -= totalLen;
            } else {
                partSum += realList[pointerB];
                pointerB++;
                if(pointerB >= totalLen)
                    pointerB -= totalLen;
            }
            
            cnt++;
        }
        
        
        return answer;
    }
}