class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int n = friends.length;
        
        int [][]arr = new int[n][n];
        int []give = new int[n];
        int []receive = new int[n];
        int []gs= new int[n];
        
        for(int i=0;i<gifts.length;i++){
            String[] part=gifts[i].split(" ");
            
            String giver = part[0];
            String receiver = part[1];
            
            int g=0;
            int r=0;
            for(int j=0;j<n;j++){
                if(friends[j].equals(giver)){
                    g=j;
                }
                if(friends[j].equals(receiver)){
                    r=j;
                }
                if(g!=0&&r!=0)
                    break;
            }
            arr[g][r]++;
            give[g]++;
            receive[r]++;
        }
        
        for(int i=0;i<n;i++){
            gs[i]=give[i]-receive[i];
        }
        
        int[] nextm=new int[n];
        
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                int gitj=arr[i][j];
                int gjti=arr[j][i];
                
                if(gitj>gjti){
                    nextm[i]++;
                }else if(gitj<gjti){
                    nextm[j]++;
                }else{
                    if(gs[i]>gs[j]){
                        nextm[i]++;
                    }else if(gs[i]<gs[j]){
                        nextm[j]++;
                    }
                    
                }

            }
        }
        int max=0;
        for(int i=0;i<n;i++){
            max = Math.max(max,nextm[i]);
        }
        
        
        return max;
    }
}