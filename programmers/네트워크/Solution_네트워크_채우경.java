class Solution {
    int[] p;
    public int solution(int n, int[][] computers) {
        
        p = new int[n];
        for(int i=0;i<n;i++){
            p[i]=i;
        }
        
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                
                if(computers[i][j]==1)
                    union(i,j);
            }
        }
        
        int cnt=0;
        for(int i=0;i<n;i++){
            if(p[i]==i)
                cnt++;
            
        }
        return cnt;
    }
    
    private int find(int x){
        
        if(p[x]==x){
            return x;
        }
        return p[x]=find(p[x]);//부모의 왕 찾아서 현 부모에 연결 
    }
    
    private void union(int x,int y){
        
        int rx=find(x);//왕 데려와서 
        int ry=find(y);
        
        if(rx!=ry){//연결
            p[ry]=rx;
        }
    }
        
}