#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

// 세 수의 합이 가장 커지는 경우

int N;
int U[1001]; 

vector<int> sum;

// num값이 sum에 존재하는지
bool binary_search(int num) {
	int left = 0;
	int right = sum.size() - 1;

	while (left <= right) {
		int mid = (left + right) / 2;

		if (sum[mid] == num) return true;
		if (sum[mid] < num) left = mid + 1;
		else right = mid - 1;
	}
	return false;
}


int main() {
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> U[i];
	}
	// 가능한 두 수의 합
	for (int i = 0; i < N; i++) {
		for (int j = i; j < N; j++) {
			sum.push_back(U[i] + U[j]);
		}
	}
	sort(U, U + N);
	sort(sum.begin(), sum.end());

	// sum + U[z] = U[k]
	// sum = U[k] - U[z] 두 수의 차가 있는지 확인
	for (int i = N - 1; i >= 0; i--) {
		for (int j = i; j >= 0; j--) {
			int num = U[i] - U[j];
			if (binary_search(num)) {
				cout << U[i] << "\n";
				return 0;
			}
		}
	}


	return 0;
}
