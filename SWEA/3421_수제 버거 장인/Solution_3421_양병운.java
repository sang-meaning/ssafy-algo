import java.util.*;
import java.io.*;
class Solution {
    static int N, M;
    static List<Integer>[] exceptTwin;
    static int cnt;
    static Set<String> set;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //1~N
            M = Integer.parseInt(st.nextToken()); 
            exceptTwin = new List[N+1];
            for(int i=1; i<=N; i++) exceptTwin[i] = new ArrayList<>();
            for(int i=0; i<M; i++){ //M은 0~400
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                exceptTwin[a].add(b);
                exceptTwin[b].add(a);
            }
            set = new HashSet<>();
            dfs(1, new ArrayList<>());
            System.out.println("#"+test_case+" "+set.size());
		}
	}
    public static void dfs(int depth,  List<Integer> list){
        if(depth == N+1) {
            Collections.sort(list);
            set.add(list.toString());
            return;
        }
        List<Integer> exceptions = exceptTwin[depth]; //어울리지 않는 숫자들
        dfs(depth+1, new ArrayList<>(list));
        if(!exceptionsIsContains(list, depth)) {
            list.add(depth);
            dfs(depth+1, new ArrayList<>(list));
        } 
    }
    public static boolean exceptionsIsContains(List<Integer> list, int depth){
        List<Integer> exceptions = exceptTwin[depth];
        for(int i=0; i<list.size(); i++){
            if(exceptions.contains(list.get(i))) return true;
        }
        return false;
    }
}