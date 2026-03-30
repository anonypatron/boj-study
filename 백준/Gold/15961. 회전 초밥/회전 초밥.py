import sys
input = sys.stdin.readline

n, d, k, c = map(int, input().strip().split())

arr = [int(input().strip()) for _ in range(n)]

ans = left = 0
right = k
freq = dict()

for i in range(k):
    freq[arr[i]] = freq.get(arr[i], 0) + 1

for i in range(n):
    cur_len = len(freq)
    if c not in freq:
        cur_len += 1
    ans = max(ans, cur_len)

    # 한 칸 밀기
    freq[arr[right]] = freq.get(arr[right], 0) + 1
    right = (right + 1) % n

    freq[arr[left]] = freq.get(arr[left], 1) - 1
    if freq[arr[left]] == 0:
        del freq[arr[left]]
    left = (left + 1) % n

print(ans, end='')
