import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        // A -> B 선물줌
        
        
        HashMap<String, Integer> map = new HashMap<>();
        int idx=0;
        for (String name : friends) {
            map.put(name, idx);
            idx++;
        }
        
        int N = friends.length;
        int[][] gave = new int[N][N]; // gave[i][j] : i가 j에게 준 개수
        
        int[] jisu = new int[N]; // jisu[i] : i의 선물 지수
        
        for (int i=0; i<gifts.length; i++) {
            String[] line = gifts[i].split(" ");
            String g = line[0]; // 준사람
            String r = line[1]; // 받은사람
            
            int g_idx = map.get(g);
            int r_idx = map.get(r);
            
            gave[g_idx][r_idx]++;
            jisu[g_idx]++; // 준사람은 선물지수 +1
            jisu[r_idx]--;
        }
        

        
        // 다음달에 받을 선물 개수 구하기
        int[] will = new int[N];
        
        for (int i=0; i<N-1; i++) {
            for (int j=i+1; j<N; j++) {
                int a_idx = map.get(friends[i]);
                int b_idx = map.get(friends[j]);
                
                // a,b 중 더 많이 준사람 찾기
                int aa = gave[a_idx][b_idx]; // a가 b에게 준 선물
                int bb = gave[b_idx][a_idx];
                
                if (aa == bb) {
                    // 같으면 선물 지수 비교
                    int aa_jisu = jisu[a_idx];
                    int bb_jisu = jisu[b_idx];
                    
                    if (aa_jisu > bb_jisu) {
                        will[a_idx]++;
                    } else if (aa_jisu < bb_jisu) {
                        will[b_idx]++;
                    }
                    // 지수 같으면 pass
                    
                } else if (aa > bb) {
                    // a가 더 많이 줌
                    will[a_idx]++;
                } else if (aa < bb) {
                    will[b_idx]++;
                }
                
                
            }
        }
   
        
        // will 갱신 완료
        
        for (int k : will) {
            answer = Math.max(answer, k);
        }
        

        
        
        
        return answer;
    }
}