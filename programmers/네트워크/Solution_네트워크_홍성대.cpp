#include <string>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

int solution(int n, vector<vector<int>> computers) {
    vector<int> a[205];
    int cnt[205];
    bool visited[205];
    queue<int> q;
    int answer = 0;
    memset(cnt, 0, sizeof(cnt));
    memset(visited, false, sizeof(visited));
    for(int i = 0; i < n; i++){
        for(int j=0; j<n; j++){
            if(i != j){
                if(computers[i][j] == 1){
                    a[i].push_back(j);
                    cnt[i]++;
                }
            }
        }
    }
    for(int i =0; i<n; i++){
        if(!visited[i]){
            visited[i] = true;
            answer++;
            q.push(i);
            
            while(!q.empty()){
                int curr = q.front();
                q.pop();
                for(int next : a[curr]){
                    if(cnt[next] != 0 && !visited[next]){
                        q.push(next);
                        visited[next] = true;
                    }
                }
            }
        }
    }
    
    return answer;
}