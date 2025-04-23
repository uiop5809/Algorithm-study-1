#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <cmath>
#include <unordered_map>
#include <cstring>
using namespace std;

struct Trie{
  bool isEnd; // 끝나는 지점
  Trie* children[26]; // 알파벳
  
  Trie() {
    isEnd = false;
    for(int i = 0; i < 26; i++){
      children[i] = NULL;
    }
  }
  
  string insert(const string& word){
    Trie* node = this; // 현재 Trie 노드를 시작점
    int uniquePos = word.size();
    bool isNew = false;

    for(int i = 0; i < word.size(); i++){
      int idx = word[i] - 'a';
      if (!node->children[idx]) {
        node->children[idx] = new Trie();
        if (!isNew) {
          uniquePos = i + 1;
          isNew = true;
        }
      }
      node = node->children[idx];
    }
    node->isEnd = true;
    return word.substr(0, uniquePos);
  }
};

int N;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    Trie trie;
    unordered_map<string, int> nameCount;

    cin >> N;
    while(N--) {
      string name;
      cin >> name;
      
      string uniquePrefix = trie.insert(name);
      if (nameCount[name] == 0) {
        nameCount[name] = 1;
        cout << uniquePrefix << "\n";
      } else {
        nameCount[name]++;
        cout << name << nameCount[name] << "\n";
      }
    }

    return 0;
}
