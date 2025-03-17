#include <iostream>
#include <algorithm>
#include <unordered_set>
#include <vector>
using namespace std;

/*
	N개의 기차
	20개의 일렬로 된 좌석, 1좌석 1사람
	어떠한 기차에 대해 M개의 명령
	은하수를 건널 수 있는 기차의 수

	1 i x: i번째 기차에 x번째 좌석에 사람을 태워라
	2 i x: i번째 기차에 x번째 좌석에 앉은 사람 하차
	3 i: i번째 앉아있는 승객들 모두 한칸씩 뒤로
		20번째자리에 앉아있으면 이 명령 후에
	4 i: i번째 앉아있는 승객들 모두 한칸씩 앞으로
		1번째자리에 앉아있으면 이 명령 후에
*/

int N, M, answer;
int train[100001]; // i번째 기차, x번째 사람

// 비트마스킹 겹치는 승객 기차 확인
void solution() {
	unordered_set<int> unique_trains;
	for (int i = 1; i <= N; i++) {
		unique_trains.insert(train[i]);
	}
	answer = unique_trains.size();
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	// 기차, 명령
	cin >> N >> M;
	while (M-- > 0) {
		int cmd, i, x;
		cin >> cmd;
		switch (cmd) {
			case 1:  // 좌석 x에 사람 태우기
				cin >> i >> x;
				train[i] |= (1 << (x - 1)); 
				break;
			case 2: // 좌석 x에 사람 내리기
				cin >> i >> x;
				train[i] &= ~(1 << (x - 1)); 
				break;
			case 3: // 한칸씩 뒤로
				cin >> i;
				train[i] = (train[i] << 1) & ((1 << 20) - 1); // 21번째 비트제거, 하위 20비트만 1
				break;
			case 4: // 한칸씩 앞으로
				cin >> i;
				train[i] = (train[i] >> 1);
				break;
		}
	}

	solution();
	cout << answer;

	return 0;
}
