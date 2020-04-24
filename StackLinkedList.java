
public class StackLinkedList {
  static LinkedListNode top = null;

  static void push(int data) {
    LinkedListNode node = new LinkedListNode(data);
    if (top == null) {
      top = node;
    } else {
      node.next = top;
      top = node;
    }
  }

  static void pop() {
    if (top == null) {
      System.out.println("Stack is empty.");
    } else {
      top = top.next;
    }
  }

  static void peek() {
    if (top == null) {
      System.out.println("Stack is empty.");
    } else {
      System.out.println("Top element is " + top.data);
    }
  }

  static void traverse() {
    System.out.println("Elements in stack are: ");
    print(top);
    System.out.println();
  }

  static void print(LinkedListNode top) {
    if (top == null) {
      return;
    }
    print(top.next);
    System.out.print("\t" + top.data);
  }

  public static void main(String args[]) {
    push(5);
    push(4);
    peek();
    push(3);
    traverse();
    System.out.println("After pop:");
    pop();
    traverse();
    System.out.println("After pop:");
    pop();
    traverse();
    push(6);
    traverse();
  }

}