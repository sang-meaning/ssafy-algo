class Solution {
    public int solution(String[] friends, String[] gifts) {
        
        int answer = 0;
        
        String[] giver = new String[gifts.length];
        String[] receiver = new String[gifts.length];
        int[] giftScore = new int[friends.length];
        int[][] giftCount = new int[friends.length][friends.length];
        int[] nextMonthGift = new int[friends.length];
        
        for(int i = 0; i < gifts.length;i++){
            String gift = gifts[i];
            String[] names = gift.split(" ");
                
            giver[i] = names[0];
            receiver[i] = names[1];
            
            int giverIndex = 0;
            int receiverIndex = 0;
            
            for(int j = 0; j < friends.length; j++){
                if(friends[j].equals(giver[i])){
                    giverIndex = j;
                }
                
                if(friends[j].equals(receiver[i])){
                    receiverIndex = j;
                }
            }
            giftCount[giverIndex][receiverIndex]++;
        }
        
        
        
        for(int i = 0; i < friends.length; i++){
            for(int j = 0; j < gifts.length; j++){
                if(friends[i].equals(giver[j])){
                    giftScore[i]++;
                }
                if(friends[i].equals(receiver[j])){
                    giftScore[i]--;
                }
                
            }
        }
        
        for(int i = 0; i < friends.length; i++){
            for(int j = i + 1; j < friends.length; j++){
                if(giftCount[i][j] > giftCount[j][i]){
                    nextMonthGift[i]++;
                }
                else if(giftCount[i][j] < giftCount[j][i]){
                    nextMonthGift[j]++;
                }
                else{
                    if(giftScore[i] > giftScore[j]){
                        nextMonthGift[i]++;
                    }
                    else if(giftScore[i] < giftScore[j]){
                        nextMonthGift[j]++;
                    }
                }
                
            }
        }
        
        for(int i = 0; i < friends.length;i++){
            if (nextMonthGift[i] > answer) {
                answer = nextMonthGift[i];
            }
        }
        
        return answer;
    }
}