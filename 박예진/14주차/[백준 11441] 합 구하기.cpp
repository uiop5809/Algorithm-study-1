#include <iostream>
#include <algorithm>
#include <vector>
#include <cstring>
using namespace std;

int N, A[100001], M, preSum[100001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> N;
    for(int i = 1; i <= N; i++){
      cin >> A[i];
      preSum[i] = preSum[i - 1] + A[i];
    }

    cin >> M;
    while(M--) {
      int i, j;
      cin >> i >> j;
      cout << preSum[j] - preSum[i - 1] << "\n";
    }

    return 0;
}
