#include <iostream>
#include <algorithm>
#include <vector>
#include <cstring>
using namespace std;

int N;
int arr[101][101];

void floyd(){
  for(int k = 1; k <= N; k++){
    for(int i = 1; i <= N; i++){
      for(int j = 1; j <= N; j++){
        if (arr[i][k] && arr[k][j]) {
          arr[i][j] = 1;
        }
      }
    }
  }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> N;
    for(int i = 1; i <= N; i++){
      for(int j = 1; j <= N; j++){
        cin >> arr[i][j];
      }
    }
    floyd();
    for(int i = 1; i <= N; i++){
      for(int j = 1; j <= N; j++){
        cout << arr[i][j] << " ";
      }
      cout << "\n";
    }

    return 0;
}
