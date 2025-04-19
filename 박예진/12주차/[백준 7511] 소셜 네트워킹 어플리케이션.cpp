#include <iostream>
#include <algorithm>
#include <cstring>
using namespace std;

/*
	두 사람의 경로 그려주기
	두 사람의 경로가 존재하는지 안 하는지
*/

int parent[100001];

int getParent(int x) {
	if (parent[x] == x) return x;
	else return parent[x] = getParent(parent[x]);
}

void unionParent(int a, int b) {
	int pa = getParent(a);
	int pb = getParent(b);

	if (pa < pb) parent[pb] = pa;
	else parent[pb] = pa;
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		int n, k; // 유저수, 친구관계수
		cin >> n >> k;

		// 부모 초기화
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}

		while (k-- > 0) {
			int a, b;
			cin >> a >> b; // a, b 친구
			unionParent(a, b);
		}
		int m; // 친구인지
		cin >> m;
		cout << "Scenario " << tc << ":\n";
		while (m-- > 0) {
			int a, b;
			cin >> a >> b;
			// 부모 확인
			if (getParent(a) == getParent(b)) cout << 1 << "\n";
			else cout << 0 << "\n";
		}
		cout << "\n";

		memset(parent, 0, sizeof(parent));
	}


}
