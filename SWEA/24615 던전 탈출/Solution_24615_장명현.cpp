// main.cpp
#ifndef _CRT_SECURE_NO_WARNINGS
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <stdio.h>

#define MAX_MAP_SIZE 350

extern void init(int N, int mMaxStamina, int mMap[MAX_MAP_SIZE][MAX_MAP_SIZE]);
extern void addGate(int mGateID, int mRow, int mCol);
extern void removeGate(int mGateID);
extern int getMinTime(int mStartGateID, int mEndGateID);

/////////////////////////////////////////////////////////////////////////

#define CMD_INIT			0
#define CMD_ADD_GATE		1
#define CMD_REMOVE_GATE		2
#define CMD_GET_MIN_TIME	3

static int gMap[MAX_MAP_SIZE][MAX_MAP_SIZE];

static bool run()
{
	int cmd, ans, ret;
	int N, maxStamina, gateID1, gateID2, row, col;
	int Q = 0;
	bool okay = false;

	scanf("%d", &Q);

	for (int q = 0; q < Q; ++q)
	{
		scanf("%d", &cmd);

		switch (cmd)
		{
		case CMD_INIT:
			scanf("%d %d", &N, &maxStamina);
			for (int i = 0; i <= N - 1; i++) {
				for (int j = 0; j <= N - 1; j++) {
					scanf("%d", &gMap[i][j]);
				}
			}
			init(N, maxStamina, gMap);
			okay = true;
			break;

		case CMD_ADD_GATE:
			scanf("%d %d %d", &gateID1, &row, &col);
			addGate(gateID1, row, col);
			break;

		case CMD_REMOVE_GATE:
			scanf("%d", &gateID1);
			removeGate(gateID1);
			break;

		case CMD_GET_MIN_TIME:
			scanf("%d %d", &gateID1, &gateID2);
			ret = getMinTime(gateID1, gateID2);
			scanf("%d", &ans);
			if (ret != ans)
				okay = false;
			break;

		default:
			okay = false;
		}
	}

	return okay;
}

int main()
{
	setbuf(stdout, NULL);
	freopen("sample_input.txt", "r", stdin);

	int T, MARK;
	scanf("%d %d", &T, &MARK);

	for (int tc = 1; tc <= T; tc++)
	{
		int score = run() ? MARK : 0;
		printf("#%d %d\n", tc, score);
	}

	return 0;
}

// solution.cpp
#include <iostream>
#include <vector>
#include <algorithm>
#include <array>
#include <queue>
#include <utility>
#include <cstring>
using namespace std;

#define pii pair<int, int>
#define MAP_SIZE_MAX	350
int maxGateId = 201;
int INF = 1e9;

// 각 Gate 별로 mMaxStamina 내로 도달 가능한 {게이트 번호, 거리, 존재 여부} 를 저장
// 추가 되면 간선 갱신

int N, maxStamina;
int field[MAP_SIZE_MAX][MAP_SIZE_MAX];

int edgeCnt, gateCnt;
int edgeIds[201][201]; // {startNode, endNode} -> edgeId
pii gatePos[201];
vector<vector<int>> g; // startNode -> edgeId
vector<array<int, 3>> edges; // edgeId -> {endNode, dist, alive}

int dx[4] = { 0, -1, 0, 1 };
int dy[4] = { 1, 0, -1, 0 };
bool visited[350][350];

int dist[201];

void init(int n, int mMaxStamina, int mMap[MAP_SIZE_MAX][MAP_SIZE_MAX]) {
	N = n;
	maxStamina = mMaxStamina;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			field[i][j] = mMap[i][j];
		}
	}

	edgeCnt = 0;
	gateCnt = 0;
	memset(edgeIds, 0, sizeof(edgeIds));

	g.clear();
	g.resize(maxGateId);
	edges.clear();

	edgeCnt = 1;
	edges.push_back({0, 0, 0}); // 쓰레기 값

	return;
}

void addGate(int mGateID, int mRow, int mCol) {
	// 필드에 게이트 생성
	field[mRow][mCol] = -mGateID;
	gatePos[mGateID] = { mRow, mCol };
	gateCnt++;

	// 간선 생성
	queue <array<int,3>> q;
	memset(visited, 0, sizeof(visited));
	
	q.push({ mRow, mCol, 0 });
	visited[mRow][mCol] = 1;

	while (!q.empty()) {
		auto f = q.front(); q.pop();
		int x = f[0];
		int y = f[1];
		int dist = f[2];

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if (visited[nx][ny]) continue;
			if (field[nx][ny] == 1) continue;
			if (dist == maxStamina) continue;

			// 양방향 간선 생성
			if (field[nx][ny] < 0) {
				int eGateId = -field[nx][ny];

				if (!edgeIds[mGateID][eGateId]) {
					edges.push_back({ eGateId, dist + 1, 1 });
					edgeIds[mGateID][eGateId] = edgeCnt;
					g[mGateID].push_back(edgeCnt++);
				}

				if (!edgeIds[eGateId][mGateID]) {
					edges.push_back({ mGateID, dist + 1, 1 });
					edgeIds[eGateId][mGateID] = edgeCnt;
					g[eGateId].push_back(edgeCnt++);
				}
			}

			q.push({ nx, ny, dist + 1 });
			visited[nx][ny] = 1;
		}
	}

	return;
}

void removeGate(int mGateID) {
	// 필드에 게이트 제거
	pii gate = gatePos[mGateID];
	field[gate.first][gate.second] = 0;

	// 간선 제거
	g[mGateID].clear();
	for (int i = 1; i <= gateCnt; i++) {
		if (edgeIds[mGateID][i]) {
			int idx = edgeIds[mGateID][i];
			edges[idx][2] = 0;
			edgeIds[mGateID][i] = 0;
		}
	}

	for (int i = 1; i <= gateCnt; i++) {
		if (edgeIds[i][mGateID]) {
			int idx = edgeIds[i][mGateID];
			edges[idx][2] = 0;
			edgeIds[i][mGateID] = 0;
		}
	}

	return;
}

int getMinTime(int mStartGateID, int mEndGateID) {
	for (int i = 1; i <= gateCnt; i++) {
		dist[i] = INF;
	}

	priority_queue<pii, vector<pii>, greater<pii>> pq;

	dist[mStartGateID] = 0;
	pq.push({ 0, mStartGateID });

	while (!pq.empty()) {
		auto f = pq.top(); pq.pop();

		int now_dist = f.first;
		int now_node = f.second;

		if (now_dist != dist[now_node]) continue;
		if (now_node == mEndGateID) break;

		for (int edgeId : g[now_node]) {
			auto edge = edges[edgeId];
			if (edge[2] == 0) continue;

			int next_node = edge[0];
			int this_dist = edge[1];

			if (dist[now_node] + this_dist < dist[next_node]) {
				dist[next_node] = dist[now_node] + this_dist;
				pq.push({dist[next_node], next_node});
			}
		}
	}

	int ans = dist[mEndGateID] == INF ? -1 : dist[mEndGateID];
	return ans;
}