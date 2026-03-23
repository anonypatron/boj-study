import sys
input = sys.stdin.readline

class Node:
    def __init__(self):
        self.children = dict()

class Trie:
    def __init__(self):
        self.head = Node()
        
    def insertNode(self, children):
        cur = self.head
        for child in children:
            if child not in cur.children:
                cur.children[child] = Node()
            cur = cur.children[child]
    
    def traversal(self, cur, layer): # 정렬해서 출력하기
        sorted_children = sorted(cur.children)
        for child in sorted_children:
            result.append("--" * layer + child)
            self.traversal(cur.children[child], layer + 1)
        
n = int(input())
trie = Trie()
result = []

for _ in range(n):
    tmp = input().split() # [3, A, B, C]
    trie.insertNode(tmp[1:])

trie.traversal(trie.head, 0)
print("\n".join(result), end='')
