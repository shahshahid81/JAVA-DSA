
public class StackArray {

  static final int stackSize = 10;
  static int top = -1;
  static int[] stack = new int[stackSize];

  static void push(int data) {
    if (top == stackSize - 1) {
      System.out.println("\nStack is Full");
    } else {
      stack[++top] = data;
    }
  }

  static void pop() {
    if (top == -1) {
      System.out.println("Stack is Empty");
    } else {
      top--;
    }
  }

  static int peek() {
    if (top == -1) {
      System.out.println("Stack is Empty");
      return -1;
    } else {
      return stack[top];
    }
  }

  static void traverse() {
    if (top == -1) {
      System.out.println("Stack is Empty");
    } else {
      for (int i = top; i >= 0; i--) {
        System.out.print("\t" + stack[i]);
      }
      System.out.println();
    }
  }

  public static void main(String args[]) {
    push(8);
    push(5);
    push(4);
    push(3);
    push(2);
    System.out.println("Initial Elements:");
    traverse();
    pop();
    System.out.println("Elements after pop:");
    traverse();
    System.out.println("Top element is " + peek());
    pop();
    System.out.println("Elements after pop:");
    traverse();
  }
}