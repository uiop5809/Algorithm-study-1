#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

/*
	1번칸 올리는 위치, N번칸 내리는 위치

	로봇 올리는 위치에만 올릴 수 있음
	올리거나, 이동 => 내구도 - 1

	종료)
	내구도 0 칸개수 >= k
	몇번째 단계가 진행중일 때 종료되었는지?
*/

int N, K, level;
int A[201]; // 칸 내구도

vector <int> robots; // idx

bool solution() {
	// 1. 벨트 회전
	int temp = A[2 * N - 1];
	for (int i = 2 * N - 1; i > 0; i--) {
		A[i] = A[i - 1];
	}
	A[0] = temp;

	// 로봇 회전
	for (int &idx : robots) {
		idx += 1;
	}
	robots.erase(remove(robots.begin(), robots.end(), N - 1), robots.end());

	// 2. 로봇 있으면 이동
	for (auto it = robots.begin(); it != robots.end();) {
		int next = *it + 1;

    // 다음칸 로봇 x, 내구성 > 0
    if (find(robots.begin(), robots.end(), next) == robots.end() && A[next] > 0) {
			*it = next;
			A[next] -= 1;

      // 도착하자마자 바로 내림
			if (next == N - 1) {
				it = robots.erase(it);
				continue; 
			}
		}
		it++; 
	}

	// 3. 올리는 위치 내구도 0아니면 로봇 올리기
	if (A[0] > 0) { 
		A[0] -= 1;
		robots.push_back(0);
	}

	// 4. 내구도 0칸 개수 세기
	int cnt = count(A, A + 2 * N, 0);
	return cnt >= K;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> K;
	for (int i = 0; i < 2 * N; i++) {
		cin >> A[i];
	}

	while (true) {
		level++;
		if (solution()) break;
	}
	cout << level;

	return 0;
}
