from typing import List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        # Словарь: значение -> индекс
        num_to_index = {}

        for i, num in enumerate(nums):
            complement = target - num

            # Проверяем, есть ли уже в словаре нужное число
            if complement in num_to_index:
                return [num_to_index[complement], i]

            # Если нет, добавляем текущее число в словарь
            num_to_index[num] = i

        # Согласно условию, решение всегда существует
        return []


# Решение внутренней модели компании Контур
# Время O(n), память O(n)
print(Solution().twoSum([2, 7, 11, 15], 9))
