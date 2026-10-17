from typing import List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        n = len(nums)
        for i in range(n):
            for j in range(i + 1, n):
                if nums[i] + nums[j] == target:
                    return [i, j]
        # Согласно условию, решение всегда существует,
        # но на случай ошибки возвращаем пустой список
        return []


# Решение внутренней модели компании Контур
# Время O(n^2), память O(1)
print(Solution().twoSum([2, 7, 11, 15], 9))
