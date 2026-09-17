// solutioln.cpp
#include <vector>
#include <algorithm>
#include <array>
#include <utility>
#include <unordered_map>
#include <queue>
using namespace std;

int city_cnt, edge_cnt, INF = 1e9;
int dist[1000], parent_city[1000], parent_edge[1000];

unordered_map <int, int> mid_map; // mid -> edge_id
vector <vector<int>> g; // start -> edge_id 삽입
vector <array<int, 3>> edges; // edge_id -> next, time, alive

void dijkstra(int sCity, int eCity, int passed, int save) {
	priority_queue <pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

	for (int i = 0; i < city_cnt; i++) {
		dist[i] = INF;
	}

	pq.push({ 0, sCity });
	dist[sCity] = 0;

	while (!pq.empty()) {
		auto f = pq.top();
		pq.pop();

		int now_time = f.first;
		int now_city = f.second;

		if (now_time != dist[now_city]) continue;
		if (now_city == eCity) return;

		for (int eid : g[now_city]) {
			auto next_edge = edges[eid];

			if (eid == passed || !next_edge[2]) continue;

			int next_city = next_edge[0];
			int plus_time = next_edge[1];
			if (dist[next_city] > dist[now_city] + plus_time) {
				dist[next_city] = dist[now_city] + plus_time;
				pq.push({ dist[next_city], next_city });

				if (save) {
					parent_city[next_city] = now_city;
					parent_edge[next_city] = eid;
				}
			}
		}
	}
}

void init(int N, int K, int mId[], int sCity[], int eCity[], int mTime[]) {
	g.clear();
	g.resize(N);
	edges.clear();
	mid_map.clear();

	city_cnt = N;
	edge_cnt = K;
	
	for (int i = 0; i < K; i++) {
		g[sCity[i]].push_back(i);
		mid_map[mId[i]] = i;
		edges.push_back({ eCity[i], mTime[i], 1 });
	}

	return;
}

void add(int mId, int sCity, int eCity, int mTime) {
	int i = edges.size();
	g[sCity].push_back(i);
	mid_map[mId] = i;
	edges.push_back({ eCity, mTime, 1 });
	return;
}

void remove(int mId) {
	int eidx = mid_map[mId];
	edges[eidx][2] = 0;
	return;
}

int calculate(int sCity, int eCity) {
	dijkstra(sCity, eCity, -1, true);

	int shortest_dist = dist[eCity];
	if (shortest_dist == INF) return -1;

	vector <int> path;
	int now = eCity;
	while (now != sCity) {
		path.push_back(parent_edge[now]);
		now = parent_city[now];
	}

	int ans = 0;
	for (auto expire_edge : path) {
		dijkstra(sCity, eCity, expire_edge, false);

		if (dist[eCity] == INF) return -1;

		ans = max(ans, dist[eCity] - shortest_dist);
	}

	return ans;
}