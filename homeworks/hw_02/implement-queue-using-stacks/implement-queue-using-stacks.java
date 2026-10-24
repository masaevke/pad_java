import java.util.ArrayDeque;
import java.util.Deque;

public class MyQueue {
    private final Deque<Integer> incoming = new ArrayDeque<>(); // входящий стек
    private final Deque<Integer> outgoing = new ArrayDeque<>(); // выходящий стек

    public void push(int x) {
        // добавляем элемент во входящий стек
        incoming.push(x);
    }

    public int pop() {
        // если выходящий стек пуст,
        // переносим все элементы из входящего стека в выходящий,
        // чтобы первый добавленный элемент оказался на вершине выходящего стека
        peek();
        return outgoing.pop();
    }

    public int peek() {
        // если выходящий стек пуст,
        // переносим все элементы из входящего стека в выходящий
        if (outgoing.isEmpty()) {
            while (!incoming.isEmpty()) {
                outgoing.push(incoming.pop());
            }
        }
        return outgoing.peek();
    }

    public boolean empty() {
        // проверка на пустоту очереди: если оба стека пусты, то очередь пуста
        return incoming.isEmpty() && outgoing.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1); // добавляем элемент 1
        queue.push(2); // добавляем элемент 2
        System.out.println(queue.peek()); // выводим первый элемент
        System.out.println(queue.pop()); // удаляем и выводим первый элемент
        System.out.println(queue.empty()); // очередь не пуста: в ней остался элемент 2
    }
}
