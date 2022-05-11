package my.code.problem2;

import java.util.Deque;
import java.util.LinkedList;

public class MyQueue {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(11);
        myQueue.push(12);
        myQueue.push(13);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
    }

    private Deque<Integer> xStack;
    private Deque<Integer> yStack;

    public MyQueue() {
        xStack = new LinkedList<>();
        yStack = new LinkedList<>();
    }

    //直接再xStack中执行push
    public void push(int x) {
        xStack.push(x);
    }

    //转移x中元素到y中，对y操作
    public int pop() {
        while (yStack.isEmpty()) {
            while (!xStack.isEmpty()) {
                yStack.push(xStack.pop());
            }
        }
        return yStack.pop();
    }

    //转移x中元素到y中，对y操作
    public int peek() {
        while (yStack.isEmpty()) {
            while (!xStack.isEmpty()) {
                yStack.push(xStack.pop());
            }
        }
        return yStack.peek();
    }

    //检测xStack的empty
    public boolean empty() {
        return xStack.isEmpty() && yStack.isEmpty();
    }
}
