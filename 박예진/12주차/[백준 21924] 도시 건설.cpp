#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

int N, M, cnt; // 건물 개수, 도로 개수 
int parent[100001];

struct Edge {
	int a, b, cost;
};

struct cmp {
	bool operator()(const Edge &e1, const Edge &e2) {
		return e1.cost > e2.cost;
	}
};

priority_queue <Edge, vector<Edge>, cmp> edges;

int getParent(int x) {
	if (parent[x] == x) return x;
	else return parent[x] = getParent(parent[x]);
}

bool unionParent(int a, int b) {
	int pa = getParent(a);
	int pb = getParent(b);

	if (pa == pb) return false;
	
	if (pa < pb) parent[pb] = pa;
	else parent[pa] = pb;
	return true;
}

// 가중치 적은 간선부터
long long kruskcal() {
	long long cost = 0;
	while (!edges.empty()) {
		Edge edge = edges.top();
		edges.pop();

		// 연결했을 때만
		if (unionParent(edge.a, edge.b)) {
			cnt++;
			cost += edge.cost;
		}
		if (cnt == N - 1) return cost;
	}
	return -1;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	cin >> N >> M;

	// 부모 초기화
	for (int i = 1; i <= N; i++) {
		parent[i] = i;
	}

	long long sum = 0;
	while (M-- > 0) {
		int a, b, c;
		cin >> a >> b >> c;
		edges.push({ a, b, c });
		sum += c;
	}

	long long answer = kruskcal();
	if (answer == -1) cout << -1;
	else cout << sum - answer;
}
