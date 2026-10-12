#include <iostream>
using namespace std;

int main() {
	int f[100];
	f[1] = 2;
	f[2] = 3;
	for (int i = 1; i <=97; i++) {
		f[i + 2] = f[i] + f[i + 1];
	}
	cout << f[8];
}