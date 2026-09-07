#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int time = 0;
	int current_weight = 0;
	int truck_idx = 0;
	queue<int> bridge;

	for (int i = 0; i < bridge_length; ++i) {
		bridge.push(0);
	}

	while (truck_idx < (int)truck_weights.size()) {
		time++;

		current_weight -= bridge.front();
		bridge.pop();

		if (current_weight + truck_weights[truck_idx] <= weight) {
			current_weight += truck_weights[truck_idx];
			bridge.push(truck_weights[truck_idx]);
			truck_idx++;
		}
		else {
			bridge.push(0);
		}
	}
    int answer = time + bridge_length;
	return answer;
}