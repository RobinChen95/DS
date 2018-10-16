package LeetCode.primary_level.sortAndOther;

import edu.princeton.cs.algs4.Stack;

class MinStack {

    Stack<Integer> stack1 = new Stack();
    Stack<Integer> stack2 = new Stack();

    /**
     * initialize your data structure here.
     */
    public MinStack() {
    }

    public void push(int x) {
        stack1.push(x);
        if (stack2.isEmpty() || x <= stack2.peek()) stack2.push(x);
    }

    public void pop() {
        int x = stack1.pop();
        if (x == stack2.peek()) stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return stack2.peek();
    }
}
