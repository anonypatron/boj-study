import sys
input = sys.stdin.readline

text = input().strip()
target = input().strip()
target_len = len(target)
cnt = 0
i = 0

while i <= len(text) - target_len:
    if text[i:i + target_len] == target:
        i += target_len
        cnt += 1
    else:
        i += 1
    

print(cnt, end='')