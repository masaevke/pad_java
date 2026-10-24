# Структура данных: стек (stack).
# Почему стек? Порядок закрытия обратный порядку открытия.
# Последняя открытая скобка должна быть закрыта первой — это принцип LIFO
# (Last In, First Out), который реализует стек через операции push / pop.


class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        matching = {")": "(", "]": "[", "}": "{"}

        for c in s:
            if c in "([{":
                stack.append(c)
            else:  # закрывающая скобка
                if not stack or stack[-1] != matching[c]:
                    return False
                stack.pop()

        return not stack


# Сложность:
# Время: O(n) — каждый символ обрабатывается один раз.
# Память: O(n) в худшем случае (строка из одних открывающих скобок).

#оценка Deepseek соответствует моей 
