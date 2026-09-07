#include <vector>
#include <unordered_set>
using namespace std;

int solution(vector<int> elements) {
	int n = (int)elements.size();
	unordered_set<int> unique_sums;

	for (int start = 0; start < n; ++start) {
		int sum = 0;
		int idx = start;

		for (int count = 0; count < n; ++count) {
			sum += elements[idx];
			unique_sums.insert(sum);

			idx++;
			if (idx == n) idx = 0;
		}
	}

	return (int)unique_sums.size();
}