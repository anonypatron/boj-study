import sys
input = sys.stdin.readline

n, m = map(int, input().strip().split())

arr = list(map(int, input().split()))
cnt = total = lIdx = rIdx = 0

while True:
    if total >= m:
        if total == m:
            cnt += 1
        total -= arr[lIdx]
        lIdx += 1
    elif rIdx == n:
        break
    else:
        total += arr[rIdx]
        rIdx += 1
        
print(cnt, end='')
