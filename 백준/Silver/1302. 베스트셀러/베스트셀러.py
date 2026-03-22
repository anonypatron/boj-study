import sys
input = sys.stdin.readline

n = int(input())

sell = dict()
max_value = 0

for _ in range(n):
    book = input()
    if book in sell:
        sell[book] += 1
    else:
        sell[book] = 1
    max_value = max(max_value, sell[book])

result = None
for key in sell:
    if sell[key] == max_value:
        if result is None or key < result: # 현재 키가 사전 순 보다 앞서면
            result = key

print(result, end='')