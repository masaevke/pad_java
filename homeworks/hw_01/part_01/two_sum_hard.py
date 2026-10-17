from typing import List

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        for i in range(len(nums)):
            #здесь цикл и далее внутри него еще один цикл с проходом по всем элементам
            #значит алгоритм имеет сложность O(n^2)
            for j in range(i + 1, len(nums)):
                if nums[i] + nums[j] == target:
                    return [i, j]

nums = [2,7,11,15]
target = 9

print(Solution().twoSum(nums,target))
#на leetcode - это решение имеет более высокую сложность по сравнению с использованием хэш-таблицы, 
# и может занимать значительно больше времени на больших входных данных
# Пример: 1771 ms

# мое решение на LeetCode
# https://leetcode.com/problems/two-sum/submissions/2174383002

