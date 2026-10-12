#include <iostream>
#include <vector>
#include <queue>
#define ff first
#define ss second

using namespace std;

// for item:adj[N] --> indegree[item] ? --> if indegree == 0 => push that.
void solve(int N, vector<vector<int>>& adjacent, vector<int>& indegree, vector<int>& answer){
    priority_queue<int> pq;
    for (int i = 0; i< indegree.size(); i++){
        if(indegree[i] == 0){
            pq.push(i);
        }
    }
    while(!pq.empty()){
        int cursor = pq.top();
        pq.pop();
        answer.push_back(cursor+1);

        
        for (auto& edge: adjacent[cursor]){
            indegree[edge]--;
            if (!indegree[edge]){
                pq.push(edge);
            }
        }
    }
}

int main(){
    int N, T, E;
    cin.tie(0);

    for (int tc =1; tc<=10; tc++){
        cin >> N >> E;
        vector<int> indegree(N,0);
        vector<vector<int>> adj(N,vector<int>()); 
        vector<int> answer;
        for (int i = 0; i < E; i++){
            int t1, t2;
            cin >> t1 >> t2;
            adj[t1-1].push_back(t2-1);
            indegree[t2-1]++;
        }
        

        solve(N, adj, indegree, answer);
        cout << "#" << tc;
        for (auto & item : answer){
            cout << " " << item;
        }
        cout << "\n";
    }

    return 0;
}