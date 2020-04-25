class PriorityQueueNode {
  char data;
  int priority;
  PriorityQueueNode next;

  PriorityQueueNode(char data, int priority) {
    this.data = data;
    this.priority = priority;
  }
}

public class PriorityQueue {
  static PriorityQueueNode front = null, rear = null;

  static void enqueue(char data, int priority) {
    PriorityQueueNode node = new PriorityQueueNode(data, priority);
    if (front == null && rear == null) {
      front = node;
      rear = node;
    } else if (front.priority > node.priority) {
      node.next = front;
      front = node;
    } else if (rear.priority < node.priority) {
      rear.next = node;
      rear = node;
    } else {
      PriorityQueueNode current = front, prev = null;
      while (current != null && current.priority <= node.priority) {
        prev = current;
        current = current.next;
      }
      prev.next = node;
      node.next = current;
    }
  }

  static void dequeue() {
    if (front == null) {
      System.out.println("Empty Queue.");
    } else if (front == rear) {
      front = null;
      rear = null;
    } else {
      front = front.next;
    }
  }

  static void traverse() {
    if (front != null) {
      PriorityQueueNode current = front;
      System.out.println("Elements:");
      while (current != null) {
        System.out.print(current.data + "\t");
        current = current.next;
      }
      System.out.println();
    }
  }

  public static void main(String args[]) {
    dequeue();
    traverse();
    enqueue('C', 2);
    enqueue('A', 1);
    enqueue('D', 3);
    enqueue('F', 4);
    enqueue('E', 3);
    enqueue('B', 1);
    enqueue('G', 4);
    traverse();
    dequeue();
    traverse();
    dequeue();
    traverse();
  }
}