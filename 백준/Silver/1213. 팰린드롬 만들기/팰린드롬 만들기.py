import sys
input = sys.stdin.readline

text = input().strip()
kind = dict()
result = []

for char in text:
    if char not in kind:
        kind[char] = 1
    else:
        kind[char] += 1

if len(text) % 2 == 0: # 짝수 체크
    flag = False
    for v in kind.values():
        if v % 2 == 1:
            flag = True
            break
    if flag:
        print("I'm Sorry Hansoo")
        exit()
    else:
        left = []
        for key in sorted(kind):
            left.append(key * (kind[key] // 2))
        left_str = "".join(left)
        result.append(left_str + left_str[::-1])

else: # 홀수 체크
    cnt = 0
    for v in kind.values():
        if v % 2 == 1:
            cnt += 1
            if cnt == 2:
                break
    if cnt >= 2:
        print("I'm Sorry Hansoo")
        exit()
    else:
        left = []
        mid = ""
        for key in sorted(kind):
            left.append(key * (kind[key] // 2))
            if kind[key] % 2 == 1:
                mid = key
        left_str = "".join(left)
        result.append(left_str + mid + left_str[::-1])

result.sort()
print(result[0], end='')
