class MyQueue:
    def __init__(self):
        self.incoming = [] #входящий стек
        self.outgoing = [] #выходящий стек

    def push(self, x: int) -> None:
        #добавляем элемент в входящий стек
        self.incoming.append(x)

    def pop(self) -> int:
        #если выходящий стек пуст, 
        # переносим все элементы из входящего стека в выходящий
        # для того, чтобы первый добавленный элемент оказался на вершине выходящего стека
        self.peek()
        return self.outgoing.pop()

    def peek(self) -> int:
        #если выходящий стек пуст,
        # переносим все элементы из входящего стека в выходящий
        if not self.outgoing:
            while self.incoming:
                self.outgoing.append(self.incoming.pop())
        return self.outgoing[-1]

    def empty(self) -> bool:
        #првоерка на пустоту очереди: если оба стека пусты, то очередь пуста
        return not self.incoming and not self.outgoing


if __name__ == "__main__":
    queue = MyQueue()
    queue.push(1) # Добавляем элемент 1
    queue.push(2) # Добавляем элемент 2
    print(queue.peek()) # Выводим первый элемент
    print(queue.pop()) # Удаляем и выводим первый элемент
    print(queue.empty()) # Проверяем, пуста ли очередь, она не пуста, так как в ней остался элемент 2
